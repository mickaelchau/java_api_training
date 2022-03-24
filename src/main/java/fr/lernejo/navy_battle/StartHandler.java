package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

public class StartHandler {

    private final Server server;
    private final JsonSchemaParser jsonSchemaParser;

    public StartHandler(Server server) {
        this.server = server;
        jsonSchemaParser = new JsonSchemaParser();
    }
    public void createStartContext(HttpServer httpServer) {
        httpServer.createContext("/api/game/start", exchange -> {
            if (!"POST".equals(exchange.getRequestMethod())) {
                server.sendResponse("Not a HTTP POST Method", exchange, 404);
                return;
            }
            try {
                handleStartRequest(exchange);
            } catch (IOException exception) {
                System.err.println("Error while handling POST request: " + exception);
            }
        });
    }

    public void handleStartRequest(HttpExchange exchange) throws IOException{
        if (jsonSchemaParser.isBodyValid(exchange)) {
            server.sendResponse("{\"id\": \"" + server.portNumber + "\",\"url\": \"http://localhost:9876\",\"message\": \"May the best code win\"}", exchange, 202);
        } else {
            server.sendResponse("Request body malformed", exchange, 400);
        }
    }
}
