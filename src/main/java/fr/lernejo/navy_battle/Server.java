package fr.lernejo.navy_battle;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

public class Server {
    int portNumber;
    Map map;
    Client client;

    public Server(int portNumber) {
        map = new Map();
        this.portNumber = portNumber;
        client = new Client();
    }

    public void sendResponse(String body, HttpExchange exchange, int responseCode) {
        try {
            exchange.sendResponseHeaders(responseCode, body.length());
        } catch (IOException exception) {
            System.err.println("Send Response Headers goes wrong: " + exception);
        }
        try (OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(body.getBytes());
        } catch (IOException exception) {
            System.err.println("Send Message goes wrong: " + exception);
        }
    }

    public void runHttpServer() {
        HttpServer server = initHttpServer();
        if (server == null) {
            return;
        }
        ExecutorService executor = Executors.newFixedThreadPool(1);
        server.setExecutor(executor);

        createContexts(server);
        server.start();
    }

    public HttpServer initHttpServer() {
        HttpServer server;
        try {
            InetSocketAddress port = new InetSocketAddress(portNumber);
            server = HttpServer.create(port, 1);
            return server;
        } catch (IOException exception) {
            System.err.println("Init HttpServer goes wrong: " + exception);
        }
        return null;
    }

    private void createContexts(HttpServer server) {
        PingHandler pingHandler = new PingHandler(this);
        StartHandler startHandler = new StartHandler(this);
        FireHandler fireHandler = new FireHandler(this);

        pingHandler.createPingContext(server);
        startHandler.createStartContext(server);
        fireHandler.createFireContext(server);
    }
}

