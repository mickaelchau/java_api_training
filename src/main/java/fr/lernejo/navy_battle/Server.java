package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    final int portNumber;
    final Map map;
    final Client client;
    final JsonSchemaParser jsonSchemaParser;

    public Server(int portNumber) {
        map = new Map();
        this.portNumber = portNumber;
        client = new Client();
        jsonSchemaParser = new JsonSchemaParser();
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

    public boolean runHttpServer() {
        HttpServer server = initHttpServer();
        if (server == null) {
            return false;
        }
        ExecutorService executor = Executors.newFixedThreadPool(1);
        server.setExecutor(executor);

        createContexts(server);
        server.start();
        return true;
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

    private boolean createContexts(HttpServer server) {
        PingHandler pingHandler = new PingHandler(this);
        StartHandler startHandler = new StartHandler(this);
        FireHandler fireHandler = new FireHandler(this);

        pingHandler.createPingContext(server);
        startHandler.createStartContext(server);
        fireHandler.createFireContext(server);
        return true;
    }
}

