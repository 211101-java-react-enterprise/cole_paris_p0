package com.revature.banking.screens;

import com.revature.banking.models.AppUser;
import com.revature.banking.services.UserService;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

public class DashboardScreen extends Screen {

    private final UserService userService;

    public DashboardScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("DashboardScreen", "/dashboard", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {

        AppUser sessionUser = userService.getSessionUser();
        if (sessionUser == null) {
            System.out.println("You are not currently logged in! Navigating to Login Screen");
            router.navigate("/login");
            return;
        }

        while (userService.isSessionActive()) {
            System.out.printf("\n%s's Dashboard\n", sessionUser.getFirstName());

            String menu = "1) Create new Account\n" +
                    "2) Deposit Funds\n" +
                    "3) Make a Withdrawal\n" +
                    "4) View balance\n" +
                    "5) Logout\n"+
                    "> ";

            System.out.print(menu);

            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("Create new account selected");
                    break;
                case "2":
                    System.out.println("Deposit funds selected");
                    router.navigate("/deposit");
                    break;
                case "3":
                    System.out.println("Make withdrawal selected");
                    router.navigate("/withdrawal");
                    break;
                case "4":
                    System.out.println("View account balance selected");
                    router.navigate("/balance");
                    break;
                case "5":
                    userService.logout();
                default:
                    System.out.println("The user made an invalid selection");
            }
        }

    }

}
