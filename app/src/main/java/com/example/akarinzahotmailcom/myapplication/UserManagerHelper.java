package com.example.akarinzahotmailcom.myapplication;

/**
 * Created by akarinzahotmail.com on 4/14/18.
 */

public interface UserManagerHelper {
    public static final String DATABASE_NAME = "myapp_login";

    public static final  int DATABASE_VERSION = 1;

    public long registerUser (User user);

    public User checkUserLogin(User user);

    public int changePassword(User user);
}
