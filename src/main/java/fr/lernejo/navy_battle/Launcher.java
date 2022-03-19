package fr.lernejo.navy_battle;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;


public class Launcher {
    
    public static void main(String[] args) {
        if (args.length == 1) {
            String adversaryUrl = args[0];
            Client client = new Client();
            String endpoint = adversaryUrl + "/api/game/start";
            String message = "{\"id\":\"1\", \"url\":\"http://localhost:" + 8795 + "\", \"message\":\"hello\"}";
            client.sendPostRequest(endpoint, message);
        }
        else {
            ServerTools serverTools = new ServerTools();
            serverTools.runHttpServer();
        }
    }
}
