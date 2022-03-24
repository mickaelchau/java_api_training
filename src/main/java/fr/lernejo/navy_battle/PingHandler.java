package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpServer;

public class PingHandler {
    private final Server server;
    public PingHandler(Server server) {
        this.server = server;
    }

    public void createPingContext(HttpServer httpServer) {
        httpServer.createContext("/ping", exchange -> server.sendResponse("OK", exchange, 200));
    }
}
