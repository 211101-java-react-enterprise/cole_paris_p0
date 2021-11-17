package com.revature.banking.screens;
import com.revature.banking.util.ScreenRouter;
import com.revature.banking.services.AccountService;

import java.io.BufferedReader;
import java.io.IOException;

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
        while(validResponse == false){
            System.out.println("How much would you like to deposit?");
            String response = consolReader.readLine();
            try{
                amt = Double.parseDouble(response);
                if(amt<=0) {
                    System.out.println("Invalid deposit. Please enter an amount greater than $0.01");
                }
                else {
                    accountService.deposit(amt);
                    System.out.println("Success! You now have: $" + accountService.viewBalance());
                    validResponse = true;
                }
            } catch(Exception e){
                System.out.println("Invalid Deposit. Please enter a number.");
            }
        }
        }


        //for use in future update. gives user option to return to dashboard when called. Currently too buggy.
    private boolean dashboardReturnOption(BufferedReader consolReader) throws IOException {
        while(true){
            System.out.println("Would you like to return to the dashboard? (y/n)");
            String response = consolReader.readLine();
            if(response.equals("y")) return true;
            else if(response.equals("n")) return false;
            else System.out.println("Invalid response: please enter either the letter 'y' or the letter 'n' into the terminal");
        }
    }
}
