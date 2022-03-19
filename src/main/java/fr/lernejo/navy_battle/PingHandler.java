package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public class PingHandler {
    ServerTools serverTools;
    public PingHandler() {
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
}
