package com.revature.banking.screens;

import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

public class BalanceScreen extends Screen{

    private final UserService userService;

    public BalanceScreen(BufferedReader consolReader, ScreenRouter router, UserService userService){
        super("balance", "/balance", consolReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("Your current balance is $"+userService.viewBalance());
    }
}
