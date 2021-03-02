package com.example.myapplication;

public class RegisterActivity {
    private int id;
    private String userName;
    private String passWord;

    public RegisterActivity(int id, String userName, String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
    }

    // Sergio: using toString to generate fields for printing contents of username, password, id, etc
    @Override
    public String toString() {
        return "RegisterActivity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    // Sergio: Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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


