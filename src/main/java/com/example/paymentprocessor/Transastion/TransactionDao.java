package com.example.paymentprocessor.Transastion;

import com.example.paymentprocessor.Setup.PaymentProcessorDatabaseException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDao {

    private final  String GET_USER_TRANSACTIONS_SQL="SELECT *  FROM transactions   where u.id= ? ";
    // id created by insert trigger in the database
    private final String ADD_TRANSACTION_SQL ="INSERT INTO  transactions  (amount, name, merchant,  customer_id, date, kind)  values( ? ,  ? , ? , ? , ?, ? ) ";
    private final String UPDATE_TRANSACTION_SQL ="UPDATE  transactions  SET  amount= ? name= ? merchant = ?  customer_id =?  date = ?  kind= ?) WHERE  id= ? ";
    private final String DELETE_TRANSACTION_SQL ="DELETE FROM transactions where id = ?  ";
    private final Connection dbConnection;
    public TransactionDao( @Autowired Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void newTransaction(TransactionDto transactionDto) throws PaymentProcessorDatabaseException {

        try {

            PreparedStatement statement= dbConnection.prepareStatement(ADD_TRANSACTION_SQL);
            statement.setDouble(1, transactionDto.getAmount());
            statement.setString(2, transactionDto.getName());
            statement.setString(3, transactionDto.getMerchant());
            statement.setString(4, transactionDto.getCustomerId()));
            statement.setDate(5, transactionDto.getDate());
            statement.setString(6, transactionDto.getKind());

            statement.executeUpdate();
            statement.close();


        } catch (SQLException e) {
            throw  new PaymentProcessorDatabaseException(e);

        }

    }

    public void removeTransaction( String id) throws PaymentProcessorDatabaseException {
        try {

            PreparedStatement statement= dbConnection.prepareStatement(DELETE_TRANSACTION_SQL);
            statement.setString(1, id);
            statement.executeUpdate();
            statement.close();


        } catch (SQLException e) {
            throw  new PaymentProcessorDatabaseException(e);

        }

    }

    public void updateTransaction( TransactionDto transactionDto) throws PaymentProcessorDatabaseException {

        try {

            PreparedStatement statement= dbConnection.prepareStatement(UPDATE_TRANSACTION_SQL);
            statement.setDouble(1, transactionDto.getAmount());
            statement.setString(2, transactionDto.getName());
            statement.setString(3, transactionDto.getMerchant());
            statement.setString(4, transactionDto.getCustomerId()));
            statement.setDate(5, transactionDto.getDate());
            statement.setString(6, transactionDto.getKind());
            statement.executeUpdate();
            statement.close();


        } catch (SQLException e) {
            throw  new PaymentProcessorDatabaseException(e);

        }
    }

    public TransactionDto getTransaction( String id) throws PaymentProcessorDatabaseException {
        TransactionDto transactionDto= new TransactionDto();

        return  transactionDto;
    }
    public List<TransactionDto> getAllTransactionsForCustomer(String customerId) throws PaymentProcessorDatabaseException {


        List<TransactionDto> transactionDtos= new ArrayList<>();

        try {

            PreparedStatement statement = dbConnection.prepareStatement(GET_USER_TRANSACTIONS_SQL);
            statement.setString(1, customerId);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                TransactionDto transactionDto= new TransactionDto();
                transactionDto.setAmount(resultSet.getDouble("amount"));
                transactionDto.setDate(Date.from(  Instant.parse(resultSet.getString("date")));
                transactionDto.setName(resultSet.getString("name"));
                transactionDto.setMerchant(resultSet.getString("merchant"));
                transactionDtos.add(transactionDto);
                statement.close();

            }

        } catch (SQLException e) {
            throw new PaymentProcessorDatabaseException(e);

        }

        return  transactionDtos;


    }
}

