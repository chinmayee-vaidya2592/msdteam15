package com.example.chinmayee.mainactivity;

import android.app.Application;
/**
 * Created by bhumi on 4/4/2016.
 */
public class Drive extends Application {

    private String userId;
    private String userEmail;
    private String level;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}