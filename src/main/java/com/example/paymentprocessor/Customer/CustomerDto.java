package com.example.paymentprocessor.Customer;

import com.example.paymentprocessor.Transastion.TransactionDto;

import java.util.ArrayList;
import java.util.List;

public class CustomerDto {
   private  String firstName;
    private  String lastName;
    private String userName;
   private  String email;
   private String address;
   private String id;
    private double points;
    private String rewardsClass;
    private List<TransactionDto> transactions= new ArrayList<>();
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }

    public String getRewardsClass() {
        return rewardsClass;
    }

    public void setRewardsClass(String rewardsClass) {
        this.rewardsClass = rewardsClass;
    }
}
