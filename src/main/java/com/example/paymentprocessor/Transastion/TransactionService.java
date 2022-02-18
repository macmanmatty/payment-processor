package com.example.paymentprocessor.Transastion;
import com.example.paymentprocessor.Customer.CustomerDao;
import com.example.paymentprocessor.Setup.PaymentProcessorDatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional(propagation = Propagation.MANDATORY, rollbackFor = PaymentProcessorDatabaseException.class)
public class TransactionService {
   private TransactionDao transactionDao;
   private CustomerDao customerDao;
    public TransactionService( @Autowired  TransactionDao transactionDao, CustomerDao customerDao) {
        this.transactionDao = transactionDao;
        this.customerDao=customerDao;
    }
    public void newTransaction( TransactionDto transactionDto) throws PaymentProcessorDatabaseException{
        transactionDao.newTransaction(transactionDto);
        customerDao.updateCustomerPoints(transactionDto);
    }
    public void removeTransaction( String transactionId) throws PaymentProcessorDatabaseException {
        transactionDao.removeTransaction(transactionId);
    }
    public void updateTransaction( TransactionDto transactionDto) throws PaymentProcessorDatabaseException {
        transactionDao.updateTransaction(transactionDto);
    }
    public TransactionDto getTransaction( String transactionId) throws PaymentProcessorDatabaseException {
            TransactionDto transactionDto=transactionDao.getTransaction(transactionId);
        return  transactionDto;
    }
    public List<TransactionDto> getAllTransactionsForCustomer(String customerId) throws PaymentProcessorDatabaseException {
        List<TransactionDto> transactionDtos= transactionDao.getAllTransactionsForCustomer(customerId);
        return  transactionDtos;
    }
}
