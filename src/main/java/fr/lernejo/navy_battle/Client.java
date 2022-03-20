package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.URI;

import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;

public class Client {
    HttpClient client;

    public Client() {
        client = HttpClient.newHttpClient();
    }

    public void sendStartRequest(String endpoint, String message) {
        HttpRequest postRequest = HttpRequest.newBuilder()
            .uri(URI.create(endpoint))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(message))
            .build();
        try {
            HttpResponse<String> response = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        }
        catch(InterruptedException | IOException exception) {
            System.err.println("Error while receiving response: " + exception);
        }
    }
}
