package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;

public class StartHandler {

    private final Server server;

    public StartHandler(Server server) {
        this.server = server;
    }

    public void createStartContext(HttpServer httpServer) {
        httpServer.createContext("/api/game/start", exchange -> {
            if (!"POST".equals(exchange.getRequestMethod())) {
                server.sendResponse("Not a HTTP POST Method", exchange, 404);
                return;
            }
            handleStartRequest(exchange);
        });
    }

    public void handleStartRequest(HttpExchange exchange) {
        JSONObject jsonObjectRequest = server.jsonSchemaParser.getRequestJson(exchange);
        System.out.println(jsonObjectRequest);
        if (server.jsonSchemaParser.isBodyValid(jsonObjectRequest)) {
            server.sendResponse("{\"id\": \"" + server.portNumber + "\",\"url\": \"http://localhost:" + server.portNumber + "\",\"message\": \"May the best code win\"}", exchange, 202);
            String adversaryUrl = (jsonObjectRequest.getString("url"));
            server.client.adversaryUrl.add(adversaryUrl);
            server.client.sendGetFireRequest();
        } else {
            server.sendResponse("Request body malformed", exchange, 400);
        }
    }
}
