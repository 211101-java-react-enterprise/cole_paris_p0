package com.revature.banking.screens;

import com.revature.banking.services.AccountService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

public class BalanceScreen extends Screen{

    private final AccountService accountService;

    public BalanceScreen(BufferedReader consolReader, ScreenRouter router, AccountService accountService){
        super("balance", "/balance", consolReader, router);
        this.accountService = accountService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("Your current balance is $"+accountService.viewBalance());
    }
}
