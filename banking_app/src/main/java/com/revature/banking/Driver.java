package com.revature.banking;

import com.revature.banking.models.AppUser;
import com.revature.banking.util.AppState;
import com.revature.banking.util.LinkedList;

public class Driver {

    public static void main(String[] args) {
        AppState app = new AppState();
        app.startup();

        /*LinkedList<AppUser> userList= new LinkedList<>();
        userList.add(new AppUser("a","","","","0"));
        userList.add(new AppUser("a","","","","1"));
        userList.add(new AppUser("a","","","","3"));
        userList.add(new AppUser("b","","","",""));
        userList.add(new AppUser("c","","","",""));
        int passSum = userList.stream()
                .filter(u -> u.getFirstName().equals("a"))//takes in predicate
                .mapToInt(u -> Integer.parseInt(u.getPassword()))//
                .sum();//terminal operations
        System.out.println(passSum);

        //Trainer train;
        LinkedList<String> stringList = new LinkedList<>();*/


    }

}
