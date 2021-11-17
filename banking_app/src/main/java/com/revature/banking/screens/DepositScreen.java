package com.revature.banking.screens;
import com.revature.banking.util.ScreenRouter;
import com.revature.banking.services.AccountService;

import java.io.BufferedReader;

public class DepositScreen extends Screen{
    private final AccountService accountService;

    public DepositScreen(BufferedReader consolReader, ScreenRouter router, AccountService accountService){
        super("DepositScreen", "/deposit", consolReader, router);
        this.accountService = accountService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("You have: $"+accountService.viewBalance());
        boolean validResponse = false;
        double amt = 0.0;
        //TODO: move this logic to UserService?
        while(validResponse == false){
            System.out.println("How much would you like to deposit?");
            String response = consolReader.readLine();
            try{
                amt = Double.parseDouble(response);
                validResponse = true;
            } catch(Exception e){
                System.out.println("Invalid Deposit. Please enter a number.");
            }
        }
        accountService.deposit(amt);
        System.out.println("Success! You now have: $" + accountService.viewBalance());
        //TODO: call class that tracks the users money
    }
}
