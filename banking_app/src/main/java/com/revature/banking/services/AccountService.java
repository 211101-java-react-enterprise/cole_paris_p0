package com.revature.banking.services;

import com.revature.banking.daos.AccountDAO;
import com.revature.banking.daos.AppUserDAO;
import com.revature.banking.exceptions.AuthenticationException;
import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.ResourcePersistenceException;
import com.revature.banking.models.Account;
import com.revature.banking.models.AppUser;
import com.revature.banking.util.Logger;

public class AccountService {
    private final AccountDAO accountDAO ;
    private Account sessionAccount;
    private UserService sessionUser;
    Logger logger;

    public AccountService(AccountDAO accountDAO, UserService sessionUser) {
        logger = Logger.getLogger(false);
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
            logger.log("invalid initial deposit");
            throw new InvalidRequestException("Please enter a valid name and inital deposit.");
        }
        Account registeredAccount = accountDAO.save(newAccount);
        if (registeredAccount == null) {
            logger.log("problem persisting");
            throw new ResourcePersistenceException("The user could not be persisted to the datasource!");
        }
        return true;
    }


    public boolean deposit(double amount){
        logger.log("depositing funds");
        double balance = sessionAccount.getBalance();
        double addedAmount = amount;
        sessionAccount.setBalance(balance + addedAmount);
        accountDAO.update(sessionAccount);
        return true;
    }


    public boolean isAccountValid(Account newAccount){return true;}

    public boolean withdrawal(double amount){
        logger.log("withdrawing funds");
        double balance = sessionAccount.getBalance();
        if(amount <=0.01){
            logger.log("tried to withdrawal amount less than 0");
            return false;
        }
        else if((balance-amount) >= 0){
            logger.log("overdraft error");
            sessionAccount.setBalance(balance - amount);
            accountDAO.update(sessionAccount);
            return true;
        }
        else return false;

    }

    public double viewBalance(){
        if(sessionAccount == null){//if local sessionAccount hasn't been defined yet, get it from the database
            logger.log("no account found. pulling from datasource");
            //query balance from aws
            sessionAccount = accountDAO.findById(sessionUser.getSessionUser().getId());
            //sessionAccount = accountDAO.findById(sessionAccount.getAccountId());
        }
        return sessionAccount.getBalance();
    }
}
