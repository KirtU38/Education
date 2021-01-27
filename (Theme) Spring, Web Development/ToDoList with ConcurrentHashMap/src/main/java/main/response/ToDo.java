package main.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDo {

    private int id;
    private String text;
    private String date;

    public ToDo(@JsonProperty("text") String text) {
        this.text = text;
        this.date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
