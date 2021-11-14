package com.revature.banking.screens;
import com.revature.banking.util.ScreenRouter;
import com.revature.banking.services.UserService;

import java.io.BufferedReader;

public class DepositScreen extends Screen{
    private final UserService userService;

    public DepositScreen(BufferedReader consolReader, ScreenRouter router, UserService userService){
        super("DepositScreen", "/deposit", consolReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("You have: $"+userService.viewBalance());
        System.out.println("How much would you like to deposit?");
        String response = consoleReader.readLine();
        if(userService.deposit(response)){
            System.out.println("Success! You now have: $" + userService.viewBalance());
        }
        else System.out.println("Failed to make a deposit");

        //TODO: call class that tracks the users money
    }
}
