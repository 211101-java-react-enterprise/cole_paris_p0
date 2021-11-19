package com.revature.banking.screens;

import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.ResourcePersistenceException;
import com.revature.banking.models.AppUser;
import com.revature.banking.services.UserService;
import com.revature.banking.util.Logger;
import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

public class RegisterScreen extends Screen {

    private final UserService userService;
    private Logger logger;

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("RegisterScreen", "/register", consoleReader, router);
        logger = Logger.getLogger(false);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("The user selected Register");
        System.out.println("Please provide us with some basic information.");
        System.out.print("First name: ");
        String firstName = consolReader.readLine();

        System.out.print("Last name: ");
        String lastName = consolReader.readLine();

        System.out.print("Email: ");
        String email = consolReader.readLine();

        System.out.print("Username: ");
        String username = consolReader.readLine();

        System.out.print("Password: ");
        String password = consolReader.readLine();

        System.out.printf("Provided user first and last name: { \"firstName\": %s, \"lastName\": %s}\n", firstName, lastName);
        // String format specifiers: %s (strings), %d (whole numbers), %f (decimal values)

        AppUser newUser = new AppUser(firstName, lastName, email, username, password);
        newUser.setRegisterDateTime(logger.getTime());

        try {
            userService.registerNewUser(newUser);
            router.navigate("/login");

        } catch (InvalidRequestException | ResourcePersistenceException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // TODO log this unexpected exception to a file
            e.printStackTrace();
        }


    }

}
