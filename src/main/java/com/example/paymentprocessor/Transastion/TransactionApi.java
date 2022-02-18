package com.example.paymentprocessor.Transastion;
import com.example.paymentprocessor.Setup.PaymentProcessorDatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@RestController
public class TransactionApi {
    
    private TransactionService transactionService;
    
    public TransactionApi( @Autowired  TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping("/transaction")
    public void newTransaction(@RequestBody TransactionDto transactionDto, HttpServletRequest request){
        try {
            transactionService.newTransaction(transactionDto);
        }
        catch (PaymentProcessorDatabaseException e){
        }
    }
    
        @DeleteMapping("/transaction")
    public void removeTransaction(  @RequestBody String transactionId, HttpServletRequest request)  {
        try {
            transactionService.removeTransaction(transactionId);
        }
        catch (PaymentProcessorDatabaseException e){
        }
    }
    @PutMapping("/transaction")
    public void updateTransaction(  @RequestBody  TransactionDto transactionDto, HttpServletRequest request)  {
        try {
            transactionService.updateTransaction(transactionDto);
        }
        catch (PaymentProcessorDatabaseException e){
        }
    }
    @GetMapping("/transaction")
    public TransactionDto getTransaction( String transactionId, HttpServletRequest request)  {
        TransactionDto transactionDto = new TransactionDto();
        try {
          transactionDto = transactionService.getTransaction(transactionId);
        }
        catch (PaymentProcessorDatabaseException e){
        }
        return  transactionDto;
    }
    @GetMapping("/allTransactions")
    public List<TransactionDto> getAllTransactionsForCustomer(String customerId, HttpServletRequest request)  {
        List<TransactionDto> transactionDtos= new ArrayList<>();
        try {
            transactionDtos = transactionService.getAllTransactionsForCustomer(customerId);
        }
        catch (PaymentProcessorDatabaseException e){
        }
        return  transactionDtos;
    }
}
