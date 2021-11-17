package com.revature.banking.screens;

import com.revature.banking.models.Account;
import com.revature.banking.services.AccountService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

public class CreateAccountScreen extends Screen{
    private final AccountService accountService;
    //private AppUser sessionUser;

    public CreateAccountScreen(BufferedReader consolReader, ScreenRouter router, AccountService accountService){
        super("create_account", "/create_account", consolReader, router);
        //this.sessionUser = sessionUser;
        this.accountService = accountService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("New User Detected: Creating account..." +
                "\nInitializing balance to 0...");
        Account newAccount = new Account(0.0);//initializing balance to 0
        newAccount.setUserId(accountService.getSessionUser().getSessionUser().getId());

        accountService.registerNewAccount(newAccount);
        router.navigate("/Dashboard");
    }
}
