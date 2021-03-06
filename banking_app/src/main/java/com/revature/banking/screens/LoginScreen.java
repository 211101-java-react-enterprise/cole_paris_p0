package com.revature.banking.screens;

import com.revature.banking.exceptions.AuthenticationException;
import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.services.AccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

// TODO: Implement me!
public class LoginScreen extends Screen {

    private final UserService userService;
    private AccountService accountService;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("LoginScreen", "/login", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {

        System.out.println("Please provide your credentials to log into your account.");
        System.out.print("Username > ");
        String username = consolReader.readLine();
        System.out.print("Password > ");
        String password = consolReader.readLine();

        try {
            userService.authenticateUser(username, password);
            if(userService.hasAccount())//if the user has an attached count, go to dashboard. Otherwise, make one!
                router.navigate("/dashboard");
            else
                router.navigate("/create_account");

        } catch (InvalidRequestException | AuthenticationException e) {
            System.out.println(e.getMessage());
        }

    }

}
