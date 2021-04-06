package com.example.myapplication;

public class TaskModel {
    public String name, description, date, userID;
    public int difficulty;
    public boolean completed;

    public TaskModel(){
        this.name = "";
        this.description = "";
        this.date = "";
        this.userID = "";
        this.difficulty = 1;
        this.completed = false;
    }

    public TaskModel(String name, String description, String date, int difficulty, String userID){
        this.name = name;
        this.description = description;
        this.date = date;
        this.userID = userID;
        this.difficulty = difficulty;
        this.completed = false;
    }
}
