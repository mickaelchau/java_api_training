package fr.lernejo.navy_battle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

public class ServerTools {
    public HttpServer initHttpServer()
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

    private void createContexts(HttpServer server) {
        PingHandler pingHandler = new PingHandler();
        StartHandler startHandler = new StartHandler();
        FireHandler fireHandler = new FireHandler();

        pingHandler.createPingContext(server);
        startHandler.createStartContext(server);
        fireHandler.createFireContext(server);
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

    public void sendResponse(String body, HttpExchange exchange, int responseCode) throws IOException {
        exchange.sendResponseHeaders(responseCode, body.length());
        try (OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(body.getBytes());
        } catch (IOException exception){
            System.err.println("Send Response goes wrong: " + exception);
        }
    }

    public JSONObject getRequestJson(InputStream os) throws IOException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(os, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
    
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
        return jsonObject;
    }
}
