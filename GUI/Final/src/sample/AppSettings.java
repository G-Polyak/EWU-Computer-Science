package sample;

import java.util.prefs.Preferences;

public class AppSettings {

    public static int initGhosts = 2;
    public static int addGhosts = 2;

    public static void storePrefs(Class c) {

        Preferences prefs = Preferences.userNodeForPackage(c);
        prefs.putInt("initGhosts", initGhosts);
        prefs.putInt("addGhosts", addGhosts);

    }

    public static void readPrefs(Class c) {

        Preferences prefs = Preferences.userNodeForPackage(c);
        initGhosts = prefs.getInt("initGhosts", 2);
        addGhosts = prefs.getInt("addGhosts", 2);

    }

}
