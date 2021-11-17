package com.revature.banking.screens;

import com.revature.banking.services.AccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

public class WithdrawalScreen extends Screen {
    private final AccountService accountService;

    public WithdrawalScreen(BufferedReader consolReader, ScreenRouter router, AccountService accountService){
        super("withdrawal", "/withdrawal", consolReader, router);
        this.accountService = accountService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("How much money would you like to Withdrawal?");
        String response = consoleReader.readLine();
        if(accountService.withdrawal(Double.parseDouble(response))){
            System.out.println("Success. Enjoy your money");
        }
        else{
            System.out.println("Overdraft. Deposit more funds");
        }

    }
}
