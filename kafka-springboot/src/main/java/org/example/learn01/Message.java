package org.example.learn01;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author haoren
 * @date 2024/05/26 22:06
 **/
@Setter
@Getter
public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 6678420965611108427L;

    private String from;

    private String message;

    public Message() {

    }

    public Message(String from, String message) {
        this.from = from;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
