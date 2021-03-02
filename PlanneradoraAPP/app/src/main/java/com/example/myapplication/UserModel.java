package com.example.myapplication;


public class UserModel {
    private String userName;
    private String passWord;

    public UserModel(int id, String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    // Sergio: using toString to generate fields for printing contents of username, password, etc

    @Override
    public String toString() {
        return "UserModel{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    // Sergio: Getters and setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}


