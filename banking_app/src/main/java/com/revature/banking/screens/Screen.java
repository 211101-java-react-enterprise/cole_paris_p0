package com.revature.banking.screens;

import com.revature.banking.util.ScreenRouter;

import java.io.BufferedReader;

/**
 * Key Differences between Abstract Classes and Interfaces:
 *
 *      - Abstract classes can have instance fields (that are not implicitly public, static, final)
 *      - Abstract classes can have methods with implementation that are not declared as default
 *      - Abstract class method stubs must be explicitly declared as "abstract"
 */
public abstract class Screen {

    protected String name;
    protected String route;
    protected BufferedReader consolReader;
    protected ScreenRouter router;

    public Screen(String name, String route, BufferedReader consolReader, ScreenRouter router) {
        this.name = name;
        this.route = route;
        this.consolReader = consolReader;
        this.router = router;
    }

    public final String getName() {
        return name;
    }

    public final String getRoute() {
        return route;
    }

    public abstract void render() throws Exception;//throws exception makes it so you have to override this

}
