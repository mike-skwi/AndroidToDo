package com.example.csc372_mikeskwierawski_a2;

public class IndivObject {

    String title;
    String body;
    String time;

    public IndivObject(String title, String body, String time){
        this.title = title;
        this.body = body;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "IndivObject{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
