package com.revature.banking.models;

import java.util.Objects;

public class Account extends Object{

    private AppUser owner;
    private String accountId;
    private String userId;
    private String name;
    private double balance;

    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public Account(String userId, String name, double balance) {
        this.userId = userId;
        this.name = name;
        this.balance = balance;
    }

    public Account(String userId, String name, double balance, String accountId) {
        this(userId, name, balance);
        this.accountId = accountId;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // potentially add validation logic here (but there's really a better place to do this kind of logic)
        this.name = name;
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
                .append(name).append(":")
                .append(balance).append(":")
                .append(userId).append(":");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.revature.banking.models.Account account = (com.revature.banking.models.Account) o;
        return Objects.equals(userId, account.userId) && Objects.equals(accountId, account.accountId) && Objects.equals(name, account.name) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, accountId, name, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId='" + userId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", name='" + name + '\'' +
                ", balance='" + balance +  '\'' +
                '}';
    }
}
