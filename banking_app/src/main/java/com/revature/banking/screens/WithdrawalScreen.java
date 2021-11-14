package com.revature.banking.screens;

import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

public class WithdrawalScreen extends Screen {
    private final UserService userService;

    public WithdrawalScreen(BufferedReader consolReader, ScreenRouter router, UserService userService){
        super("withdrawal", "/withdrawal", consolReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("How much money would you like to Withdrawal?");
        String response = consoleReader.readLine();
        if(userService.withdrawal(response)){
            System.out.println("Success. Enjoy your money");
        }
        else{
            System.out.println("Overdraft. Deposit more funds");
        }

    }
}
