package sample;

import java.util.prefs.Preferences;

public class AppSettings {

    public static boolean mDateOrString = false;
    public static boolean mItalics = false;
    public static boolean mBold = false;
    public static String mUserString = "";
    public static int mFontSize = 16;

    public static void storePrefs(Class c) {

        Preferences prefs = Preferences.userNodeForPackage(c);
        prefs.putBoolean("mDateOrString", mDateOrString);
        prefs.putBoolean("mItalics", mItalics);
        prefs.putBoolean("mBold", mBold);
        prefs.put("mUserString", mUserString);
        prefs.putInt("mFontSize", mFontSize);

    }

    public static void readPrefs(Class c) {

        Preferences prefs = Preferences.userNodeForPackage(c);
        mDateOrString = prefs.getBoolean("mDateOrString", false);
        mItalics = prefs.getBoolean("mItalics", false);
        mBold = prefs.getBoolean("mBold", false);
        mUserString = prefs.get("mUserString", "");
        mFontSize = prefs.getInt("mFontSize", 16);

    }

}