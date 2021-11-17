package com.revature.banking.services;

import com.revature.banking.daos.AccountDAO;
import com.revature.banking.daos.AppUserDAO;
import com.revature.banking.exceptions.AuthenticationException;
import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.ResourcePersistenceException;
import com.revature.banking.models.Account;
import com.revature.banking.models.AppUser;

public class AccountService {
    private final AccountDAO accountDAO ;
    private Account sessionAccount;
    private UserService sessionUser;

    public AccountService(AccountDAO accountDAO, UserService sessionUser) {
        this.accountDAO = accountDAO;
        sessionAccount = null;
        this.sessionUser = sessionUser;
        //sessionUser = null;//sets the sessionAccount userId to the session users id.
    }

    /*public AccountService(AppUser sessionUser){
        this.sessionUser = sessionUser;
        accountDAO = new AccountDAO();
    }*/

    public UserService getSessionUser() {
        return sessionUser;
    }

    public boolean registerNewAccount(Account newAccount){
        newAccount.setUserId(sessionUser.getSessionUser().getId());
        //sessionUser = userService.getSessionUser();//once logged in, this should work
        //sessionAccount.setUserId(sessionUser.getId());
        if (!isAccountValid(newAccount)) {
            throw new InvalidRequestException("Please enter a valid name and inital deposit.");
        }
        Account registeredAccount = accountDAO.save(newAccount);
        if (registeredAccount == null) {
            throw new ResourcePersistenceException("The user could not be persisted to the datasource!");
        }
        return true;
    }


    public boolean deposit(double amount){
        double balance = sessionAccount.getBalance();
        double addedAmount = amount;
        sessionAccount.setBalance(balance + addedAmount);
        accountDAO.update(sessionAccount);
        return true;
    }

    public boolean isAccountValid(Account newAccount){return true;}

    public boolean withdrawal(double amount){
        double balance = sessionAccount.getBalance();
        if(amount <=0.01){
            return false;
        }
        else if((balance-amount) >= 0){
            sessionAccount.setBalance(balance - amount);
            accountDAO.update(sessionAccount);
            return true;
        }
        else return false;

    }

    public double viewBalance(){
        if(sessionAccount == null){//if local sessionAccount hasn't been defined yet, get it from the database
            //query balance from aws
            sessionAccount = accountDAO.findById(sessionUser.getSessionUser().getId());
            //sessionAccount = accountDAO.findById(sessionAccount.getAccountId());
        }
        return sessionAccount.getBalance();
    }
}
