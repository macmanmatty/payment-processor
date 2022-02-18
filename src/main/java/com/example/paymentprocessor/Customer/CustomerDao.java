package com.example.paymentprocessor.Customer;
import com.example.paymentprocessor.Setup.PaymentProcessorDatabaseException;
import com.example.paymentprocessor.Transastion.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.*;
@Repository
public class CustomerDao {
    private Connection dbConnection;
    private final  String GET_USER_SQL="SELECT * FROM  users u  where u.id= ?";
    // db insert trigger will create the user id
    private final  String ADD_USER_SQL="INSERT INTO users  (user_name, first_name, last_name,  email, address, points, rewards_class)  values( ?, ?, ?, ?,?,?,? )";
    private final  String UPDATE_USER_SQL="UPDATE users SET user_name= ?, first_name= ?, last_name= ?,  email=? , address= ?, points= ?, rewards_class= ? WHERE user_id=?";
    private final  String DELETE_USER_SQL="DELETE FROM users WHERE user_id=?";
    private final String UPDATE_USER_POINTS="UPDATE  users, SET points= SUM( points, ? ) where id= ?";
    private final String GET_REWARD_POINTS_MULTIPLIER="SELECT  multiplier FROM rewards  where reward= (select reward_class FROM users WHERE id= ? ) ";
    private final String GET_TRANSACTION_KIND_POINTS_MULTIPLIER="SELECT  transaction_multiplier FROM transaction_rewards  where type= (select kind FROM transactions WHERE id= ? ) ";
    public CustomerDao( @Autowired Connection dbConnection) {
        this.dbConnection = dbConnection;
    }
    public CustomerDao() {
    }
    public CustomerDto getCustomer(String id) throws PaymentProcessorDatabaseException {
        CustomerDto customer= new CustomerDto();
        try {
            PreparedStatement statement= dbConnection.prepareStatement(GET_USER_SQL);
            statement.setString(1, id);
            ResultSet resultSet=statement.executeQuery();
            customer.setAddress(resultSet.getString("address"));
            customer.setFirstName(resultSet.getString("first_name"));
            customer.setLastName(resultSet.getString("last_name"));
            customer.setUserName(resultSet.getString("userName"));
            customer.setPoints(resultSet.getDouble("points"));
            statement.close();
        } catch (SQLException e) {
            throw  new PaymentProcessorDatabaseException(e);
        }
        return  customer;
    }
    public void updateCustomerPoints(TransactionDto transactionDto) throws PaymentProcessorDatabaseException {
        try {
            double rewardsMultiplier = getRewardPointsMultiplierForUser(transactionDto);
            double rewardsMultiplierTransaction = getRewardPointsMultiplierForTransaction(transactionDto);
            if (rewardsMultiplierTransaction > rewardsMultiplier){
                rewardsMultiplier = rewardsMultiplierTransaction;
             }
         PreparedStatement  statement= dbConnection.prepareStatement(UPDATE_USER_POINTS);
            double points=rewardsMultiplier*transactionDto.getAmount();
            statement.setDouble(1, points);
            statement.setString(2, transactionDto.getCustomerId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw  new PaymentProcessorDatabaseException(e);
        }
    }
    private double getRewardPointsMultiplierForUser(TransactionDto transactionDto) throws   PaymentProcessorDatabaseException {
        double rewardsMultiplier = 0;
        try {
            PreparedStatement statement = dbConnection.prepareStatement(GET_REWARD_POINTS_MULTIPLIER);
            statement.setString(1, transactionDto.getCustomerId());
            ResultSet resultSet = statement.executeQuery();
            rewardsMultiplier = resultSet.getDouble(1);
        }
        catch( IllegalArgumentException | SQLException e){
            throw new PaymentProcessorDatabaseException();
        }
        return  rewardsMultiplier;
    }
    private double getRewardPointsMultiplierForTransaction(TransactionDto transactionDto) throws   PaymentProcessorDatabaseException {
        double rewardsMultiplier = 0;
        try {
            PreparedStatement statement = dbConnection.prepareStatement(GET_TRANSACTION_KIND_POINTS_MULTIPLIER);
            statement.setString(1, transactionDto.getCustomerId());
            ResultSet resultSet = statement.executeQuery();
            rewardsMultiplier = resultSet.getDouble(1);
        }
        catch( IllegalArgumentException | SQLException e){
            throw new PaymentProcessorDatabaseException();
        }
        return  rewardsMultiplier;
    }
    public void updateCustomer(CustomerDto customerDto) throws PaymentProcessorDatabaseException{
        try {
            PreparedStatement statement= dbConnection.prepareStatement(UPDATE_USER_SQL);
            statement.setString(1, customerDto.getUserName());
            statement.setString(2, customerDto.getFirstName());
            statement.setString(3, customerDto.getLastName());
            statement.setString(4, customerDto.getEmail());
            statement.setString(5, customerDto.getAddress());
            statement.setDouble(6, customerDto.getPoints());
            statement.setString(7, customerDto.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw  new PaymentProcessorDatabaseException(e);
        }
    }
    public void addCustomer(CustomerDto customerDto) throws PaymentProcessorDatabaseException {
        try {
            PreparedStatement statement = dbConnection.prepareStatement(ADD_USER_SQL);
            statement.setString(1, customerDto.getUserName());
            statement.setString(2, customerDto.getFirstName());
            statement.setString(3, customerDto.getLastName());
            statement.setString(4, customerDto.getEmail());
            statement.setString(5, customerDto.getAddress());
            statement.setDouble(6, customerDto.getPoints());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new PaymentProcessorDatabaseException(e);
        }
    }
    public void deleteCustomer(String customerId) throws PaymentProcessorDatabaseException {
        try {
            PreparedStatement statement = dbConnection.prepareStatement(DELETE_USER_SQL);
            statement.setString(1, customerId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new PaymentProcessorDatabaseException(e);
        }
    }
}
