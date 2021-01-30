package main.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

// Здесь важно RedisHash и Serializable
@RedisHash("Todo")
@Data
public class ToDo implements Serializable {

    private String id;
    private String text;

    public ToDo(String id, String text) {
        this.id = id;
        this.text = text;
    }
}
