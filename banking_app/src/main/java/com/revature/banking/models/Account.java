package com.revature.banking.models;

import java.util.Objects;

public class Account extends Object{

    private AppUser owner;
    private String accountId;
    private String userId;
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    /*public Account(String userId, double balance) {
        this.userId = userId;
        this.balance = balance;
    }*/

    /*public Account(String userId, String name, double balance, String accountId) {
        this(userId, balance);
        this.accountId = accountId;
    }*/

    public Account() {
        super();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner){
        this.owner = owner;
    }

    public void setAccountId(String accountId) {
         this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String toFileString() {
        StringBuilder builder = new StringBuilder();
        builder.append(accountId).append(":")
                .append(balance).append(":")
                .append(userId).append(":");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.revature.banking.models.Account account = (com.revature.banking.models.Account) o;
        return Objects.equals(userId, account.userId) && Objects.equals(accountId, account.accountId) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, accountId, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId='" + userId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", balance='" + balance +  '\'' +
                '}';
    }
}
