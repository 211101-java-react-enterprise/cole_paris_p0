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
        System.out.println("Provide basic details:" +
                "\n What would you like to call the account?");
        String name = consoleReader.readLine();
        System.out.println("Great choice! \n" +
                "How much would you initially like to deposit?");
        String initialDeposit = consoleReader.readLine();
        System.out.println(accountService.getSessionUser());
        Account newAccount = new Account(name, Double.parseDouble(initialDeposit));
        newAccount.setUserId(accountService.getSessionUser().getSessionUser().getId());

        accountService.registerNewAccount(newAccount);
        router.navigate("/Dashboard");
    }
}
