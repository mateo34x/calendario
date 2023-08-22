package com.example.calendario;

public class Task {

     String title,description,date,important,uidCreated,taskID;

    public Task() {
    }

    public Task(String title, String description, String date, String important, String uidCreated,String taskID) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.important = important;
        this.uidCreated = uidCreated;
        this.taskID = taskID;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getImportant() {
        return important;
    }

    public String getUidCreated() {
        return uidCreated;
    }

    public String getTaskID() {
        return taskID;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", important='" + important + '\'' +
                ", uidCreated='" + uidCreated + '\'' +
                ", taskID='" + taskID + '\'' +
                '}';
    }
}
