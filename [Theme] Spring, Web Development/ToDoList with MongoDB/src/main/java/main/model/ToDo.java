package main.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ToDo {

    @Id
    private int id;
    private String text;
    private String date;

    public ToDo() {}

    public ToDo(int id, String text, String date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format(
                "Todo[id=%s, text='%s', date='%s']",
                id, text, date);
    }
}
