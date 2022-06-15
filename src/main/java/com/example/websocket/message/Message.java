package com.example.websocket.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String message;

    @Override
    public String toString() {
        return String.format("{\"message\":%s}", this.message);
    }
}

