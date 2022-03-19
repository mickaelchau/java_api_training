package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.URI;

public class FireHandler {
    private final ServerTools serverTools;

    public FireHandler() {
        serverTools = new ServerTools();
    }
    public void handleFireGetRequest(HttpExchange exchange) throws  IOException {
        URI test = exchange.getRequestURI();
        String cell = test.toString().split("=")[1];
        System.out.println(cell);
        serverTools.sendResponse("{\"consequence\": \"sunk\", \"shipLeft\": true}", exchange, 200);

    }

    public void createFireContext(HttpServer server) {
        server.createContext("/api/game/fire", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                if (!"GET".equals(exchange.getRequestMethod())) {
                    serverTools.sendResponse("Not a HTTP POST Method", exchange, 404);
                    return;
                }
                try {
                    handleFireGetRequest(exchange);
                } catch (IOException exception) {
                    System.err.println("Error while handling POST request: " + exception);
                }
            }
        });
    }
}