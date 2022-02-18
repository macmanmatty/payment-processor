package com.example.paymentprocessor.Setup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.beans.BeanProperty;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class JDBCSetup {

   private  Connection connection;
   private DataSource dataSource;
    @Value("JDBCConectionUrl")
   private String jdbcConnectionUrl;
    @Value("jdbcUser")
    private String jdbcUser;
    @Value("jdbcPass")
    private String jdbcPass;
    @Value("jdbcDriverClass")
    private String jdbcDriverClass;

    public JDBCSetup() {
    }

    @Bean
     Connection getConnection() throws PaymentProcessorDatabaseException {
        try {
            return mysqlDataSource().getConnection();
        } catch (SQLException e) {
            throw new PaymentProcessorDatabaseException(e);
        }
    }

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbcDriverClass);
        dataSource.setUrl(jdbcConnectionUrl);
        dataSource.setUsername(jdbcUser);
        dataSource.setPassword(jdbcPass);
        return dataSource;
    }





}
