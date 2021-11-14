package com.revature.banking.util;

import com.revature.banking.screens.Screen;

public class ScreenRouter {

    private final LinkedList<Screen> screens;

    public ScreenRouter() {
        screens = new LinkedList<>();
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }

    public void navigate(String route) throws Exception {

        //System.out.println("DEBUG, screen size: " + screens.size());
        for (int i = 0; i < screens.size(); i++) {
            Screen thisScreen = screens.get(i);
            //System.out.println("DEBUG: " + thisScreen);
            if (thisScreen.getRoute().equals(route)) {
                thisScreen.render();
            }
        }

    }
}
