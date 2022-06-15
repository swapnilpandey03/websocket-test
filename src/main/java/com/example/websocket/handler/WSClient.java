package com.example.websocket.handler;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;

@Slf4j
@Getter
@Setter
public class WSClient extends WebSocketClient {

    private String onMessageRecipeId;
    private String onErrorRecipeId;
    private String onCloseRecipeId;
    private String onOpenRecipeId;


    public WSClient(URI serverURI, String messageRecipeId, String errorRecipeId, String closeRecipeId, String openRecipeId) {
        super(serverURI);
        this.onMessageRecipeId = messageRecipeId;
        this.onErrorRecipeId = errorRecipeId;
        this.onCloseRecipeId = closeRecipeId;
        this.onOpenRecipeId = openRecipeId;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {

        System.out.println("new connection opened: " + this.onMessageRecipeId);
        System.out.println("triggering onOpenRecipe: " + this.onOpenRecipeId);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closed with exit code " + this.onMessageRecipeId + " " + code + " additional info: " + reason);
        System.out.println("triggering onCloseRecipe: " + this.onCloseRecipeId);

    }

    @Override
    public void onMessage(String message) {
        System.out.println("received message: " + message + " " + this.onMessageRecipeId);
        //trigger the recipe via kafka

    }

    @Override
    public void onMessage(ByteBuffer message) {
        System.out.println("received ByteBuffer");
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred for: " + this.onMessageRecipeId + " " + ex);
        System.out.println("triggering onErrorRecipe: " + this.onErrorRecipeId);
    }

}
