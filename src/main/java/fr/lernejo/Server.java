package fr.lernejo;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

public class Server {
    public static HttpServer initHttpServer()
     {
        HttpServer server;
        try {
            InetSocketAddress port = new InetSocketAddress(9876);
            server = HttpServer.create(port, 1);
            return server;
        } catch (IOException exception){
            System.err.println("Init HttpServer goes wrong: " + exception);
        }
        return null;
    }
    public static void main(String args[]) {
        HttpServer server = initHttpServer();
        if (server == null) {
            return;
        }
        ExecutorService executor = Executors.newFixedThreadPool(1);
        server.setExecutor(executor);

        server.createContext("/ping", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                String body = "Hello";
                exchange.sendResponseHeaders(200, body.length());
                try (OutputStream os = exchange.getResponseBody()) { // (1)
                    os.write(body.getBytes());
                } catch (IOException exception){
                    System.err.println("Send Response goes wrong: " + exception);
                }
            }
        });
        server.start();
    }
}
