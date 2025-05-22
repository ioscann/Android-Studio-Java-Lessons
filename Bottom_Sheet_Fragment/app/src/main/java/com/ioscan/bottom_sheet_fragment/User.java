package com.ioscan.bottom_sheet_fragment;

import java.util.ArrayList;

public class User {
    private String userName;

    public User(){

    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static ArrayList<User> getUserList()
    {
        ArrayList<User> userArrayList = new ArrayList<>();
        String[] users = {"Ali", "Veli", "Ayşe", "İsmail", "Sinem"};
        User mUser;

        for (int i = 0; i < users.length ; i++ ){
            mUser = new User(users[i]);
            userArrayList.add(mUser);
        }

        return userArrayList;
    }
}
