package com.example.paymentprocessor.Transastion;
import java.sql.Date;
public class TransactionDto {
   private  double amount;
   private String customerId;
   private String name;
   private String merchant;
   private Date date;
   private String id;
   private String kind;
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMerchant() {
        return merchant;
    }
    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
}
