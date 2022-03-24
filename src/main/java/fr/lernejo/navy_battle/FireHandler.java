package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.URI;

public class FireHandler {
    private final Server server;

    public FireHandler(Server server) {
        this.server = server;
    }
    public void handleFireGetRequest(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        String cell = requestURI.toString().split("=")[1];
        String attackResult = server.map.shootCell(cell).stateToString();
        boolean stillLeft = server.map.hasShipsLeft();
        server.sendResponse("{\"consequence\": \"" + attackResult + "\", \"shipLeft\": " + stillLeft + "}", exchange, 200);
    }

    public void createFireContext(HttpServer httpServer) {
        httpServer.createContext("/api/game/fire", exchange -> {
            if (!"GET".equals(exchange.getRequestMethod())) {
                server.sendResponse("Not a HTTP POST Method", exchange, 404);
                return;
            }
            try {
                handleFireGetRequest(exchange);
            } catch (IOException exception) {
                System.err.println("Error while handling POST request: " + exception);
            }
        });
    }
}
