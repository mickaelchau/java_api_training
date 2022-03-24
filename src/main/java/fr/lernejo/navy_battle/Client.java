package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.URI;

import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;

public class Client {
    HttpClient client;
    String adversaryUrl;
    Target target;

    public Client() {
        target = new Target();
        client = HttpClient.newHttpClient();
    }

    public void sendStartRequest(String adversaryUrl, String port) {
        this.adversaryUrl = adversaryUrl;
        String endpoint = adversaryUrl + "/api/game/start";
        String message = "{\"id\":\"" + port + "\", \"url\":\"http://localhost:" + port + "\", \"message\":\"hello\"}";
        HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create(endpoint))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(message)).build();
        try {
            HttpResponse<String> response = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        }
        catch(InterruptedException | IOException exception) {
            System.err.println("Error while receiving response: " + exception);
        }
    }

    public void sendGetFireRequest() {
        String endpoint =  adversaryUrl + "/api/game/fire" + "?cell=" + target.nextLetter + target.nextNumber;
        System.out.println(endpoint);
        HttpRequest getRequest = HttpRequest.newBuilder().uri(URI.create(endpoint))
            .GET()
            .build();
        try {
            HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
            target.nextChoice();
            System.out.println(response.body());
        }
        catch(InterruptedException | IOException exception) {
            System.err.println("Error while receiving response: " + exception);
        }
    }
}
