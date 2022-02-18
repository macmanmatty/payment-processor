package com.example.paymentprocessor.Customer;
import com.example.paymentprocessor.Setup.PaymentProcessorDatabaseException;
import com.example.paymentprocessor.Transastion.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpResponse;
@RestController
public class CustomerApi {
    private  CustomerService customerService;
    public CustomerApi( @Autowired  CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/customer")
    public CustomerDto getCustomer(@RequestBody  String id, HttpServletRequest request){
        CustomerDto customerDto= new CustomerDto();
        try {
           customerDto = customerService.getCustomer(id);
        }
        catch (PaymentProcessorDatabaseException e){
        }
        return  customerDto;
    }
    @DeleteMapping("/customer")
    public void deleteCustomer(@RequestBody  String id, HttpServletRequest request){
        CustomerDto customerDto= new CustomerDto();
        try {
           customerService.deleteCustomer(id);
        }
        catch (PaymentProcessorDatabaseException e){
        }
    }
    @PostMapping("/customer")
    public void addCustomer(  @RequestBody  CustomerDto customer, HttpServletRequest request){
        try {
            customerService.updateCustomer(customer);
        }
        catch (PaymentProcessorDatabaseException e){
        }
    }
    @PutMapping("/customer")
    public void updateCustomer(  @RequestBody  CustomerDto customer, HttpServletRequest request){
        try {
            customerService.updateCustomer(customer);
        }
          catch (PaymentProcessorDatabaseException e){
        }
    }
}
