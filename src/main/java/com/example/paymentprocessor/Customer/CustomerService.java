package com.example.paymentprocessor.Customer;
import com.example.paymentprocessor.Setup.PaymentProcessorDatabaseException;
import com.example.paymentprocessor.Transastion.TransactionDao;
import com.example.paymentprocessor.Transastion.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional(propagation = Propagation.MANDATORY, rollbackFor = PaymentProcessorDatabaseException.class)
public class CustomerService {
   private CustomerDao customerDao;
   private TransactionDao transactionDao;
    public CustomerService( @Autowired  CustomerDao customerDao, TransactionDao transactionDao)  {
        this.customerDao = customerDao;
        this.transactionDao=transactionDao;
    }
    public CustomerDto getCustomer(String id) throws PaymentProcessorDatabaseException{
        CustomerDto customerDto=customerDao.getCustomer(id);
        customerDto.setTransactions(transactionDao.getAllTransactionsForCustomer(id));
        return  customerDto;
    }
    public void addCustomer(CustomerDto customerDto) throws PaymentProcessorDatabaseException{
        customerDao.addCustomer(customerDto);
    }
    public void updateCustomer(CustomerDto customerDto) throws PaymentProcessorDatabaseException{
        customerDao.updateCustomer(customerDto);
    }
    public void deleteCustomer(String customerId) throws PaymentProcessorDatabaseException{
        customerDao.deleteCustomer(customerId);
    }
}
