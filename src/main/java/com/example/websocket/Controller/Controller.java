package com.example.websocket.Controller;

import com.example.websocket.handler.WSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class Controller {

    private static final String URL = "wss://streaming.usw2.pure.cloud/channels/";
    private static final Map<String, WSClient> connections = new HashMap();

    @GetMapping("/")
    public String createConnection(@PathParam("url") String url) throws Exception {
        log.info("received request");
        String identifier = url.split("/")[url.split("/").length-1];
        WSClient client = new WSClient(URI.create(url), identifier, identifier, identifier, identifier);
        connections.put(url, client);
        if (client.connectBlocking()) {
            client.send("{\"message\":\"ping\"}");
            return "Connected";
        }
        else {
            log.error("Cannot connect");
            return "Cannot connect";
        }
    }

    @DeleteMapping("/")
    public String closeConnection(@PathParam("url") String url) throws Exception {

        if(connections.containsKey(url)) {
            WSClient client = connections.get(url);
            client.closeBlocking();
            connections.remove(url);

            return "Closed";
        }
        return "Not Found";



    }

}

