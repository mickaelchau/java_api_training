package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public class ServerHandler {
    ServerTools serverTools;
    public ServerHandler() {
        serverTools = new ServerTools();
    }

    public void createPingContext(HttpServer server) {
        server.createContext("/ping", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                System.out.println("hey");
                serverTools.sendResponse("OK", exchange, 200);
            }
        });
    }

    public void createStartContext(HttpServer server) {
        server.createContext("/api/game/start", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                if (!"POST".equals(exchange.getRequestMethod())) {
                    serverTools.sendResponse("Not a HTTP POST Method", exchange, 404);
                    return;
                }
                try {
                    serverTools.handlePostRequest(exchange);
                } catch (IOException exception) {
                    System.err.println("Error while handling POST request: " + exception);
                }
            }
        });
    }
}
