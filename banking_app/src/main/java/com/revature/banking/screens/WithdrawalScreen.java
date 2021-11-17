package com.revature.banking.screens;

import com.revature.banking.services.AccountService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class WithdrawalScreen extends Screen {
    private final AccountService accountService;

    public WithdrawalScreen(BufferedReader consolReader, ScreenRouter router, AccountService accountService){
        super("withdrawal", "/withdrawal", consolReader, router);
        this.accountService = accountService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("You currently have: $"+accountService.viewBalance());
        boolean validResponse = false;
        boolean overDraftValidResponse = false;
        while(validResponse == false) {
            try {
                System.out.println("How much money would you like to Withdrawal?");
                String response = consolReader.readLine();
                double amountWithdrawn = Double.parseDouble(response);
                if (accountService.withdrawal(amountWithdrawn)) {
                    System.out.println("Success! You now have: $"+accountService.viewBalance()+" in your account. Enjoy your money");
                    break;
                }
                else if(amountWithdrawn > 0){ //if withdrawal returns false and amount is greater than 0, then overdraft
                    System.out.println("Error: You tried to withdrawal $"+response+ " but you only have $"+accountService.viewBalance()+" in your account.");
                    if(dashboardReturnOption(consolReader)) {//check whether user wants to return to dashboard
                        validResponse = true;
                        router.navigate("/dashboard");
                    }

                }
                else if (amountWithdrawn < 0.01){//if user tries to withdrawal 0 or negative value
                    System.out.println("Error: you must withdrawal at least $0.01.");
                    if(dashboardReturnOption(consolReader)) {//check whether user wants to return to dashboard
                        validResponse = true;
                        router.navigate("/dashboard");
                    }
                }
            } catch(Exception e){
                System.out.println("Invalid response. Please enter the amount of money you would like to withdrawal" +
                        "in the form of a decimal number. For example: 50.43 or 50");

            }
        }
        //router.navigate("dashboard");
    }

    private boolean dashboardReturnOption(BufferedReader consolReader) throws IOException {
        while(true){
            System.out.println("Would you like to return to the dashboard? (y/n)");
            String overDraftResponse = consolReader.readLine();
            if(overDraftResponse.equals("y")) return true;
            else if(overDraftResponse.equals("n")) return false;
            else System.out.println("Invalid response: please enter either the letter 'y' or the letter 'n' into the terminal");
        }
    }
}
