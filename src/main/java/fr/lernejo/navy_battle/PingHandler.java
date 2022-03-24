package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public class PingHandler {
    private final Server server;
    public PingHandler(Server server) {
        this.server = server;
    }

    public void createPingContext(HttpServer httpServer) {
        httpServer.createContext("/ping", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                server.sendResponse("OK", exchange, 200);
            }
        });
    }
}
