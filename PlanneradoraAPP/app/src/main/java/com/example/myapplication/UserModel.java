package com.example.myapplication;

public class UserModel {
    public String userName, passWord;
    public int level;

    public UserModel(){

    }

    public UserModel(String userName, String passWord, int level){
        this.userName = userName;
        this.passWord = passWord;
        this.level = level;
    }
}

//public class UserModel {
//    private String userName;
//    private String passWord;
//    private int id;
//
//    public UserModel(int id, String userName, String passWord) {
//        this.id = id;
//        this.userName = userName;
//        this.passWord = passWord;
//    }
//
//    // Sergio: using toString to generate fields for printing contents of username, password, etc
//
//    @Override
//    public String toString() {
//        return "UserModel{" +
//                "userName='" + userName + '\'' +
//                ", passWord='" + passWord + '\'' +
//                ", id=" + id +
//                '}';
//    }
//
//
//    // Sergio: Getters and setters
//
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassWord() {
//        return passWord;
//    }
//
//    public void setPassWord(String passWord) {
//        this.passWord = passWord;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//}
//
//
