package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

public class StartHandler {

    private final Server server;

    public StartHandler(Server server) {
        this.server = server;
    }
    public void createStartContext(HttpServer httpServer) {
        httpServer.createContext("/api/game/start", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                if (!"POST".equals(exchange.getRequestMethod())) {
                    server.sendResponse("Not a HTTP POST Method", exchange, 404);
                    return;
                }
                try {
                    handleStartRequest(exchange);
                } catch (IOException exception) {
                    System.err.println("Error while handling POST request: " + exception);
                }
            }
        });
    }

    public void handleStartRequest(HttpExchange exchange) throws IOException{
        try (InputStream os = exchange.getRequestBody()) {
            JSONObject jsonObject = server.getRequestJson(os);
            try (InputStream inputStream = getClass().getResourceAsStream("/SchemaJSON.json")) {
                JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
                Schema schema = SchemaLoader.load(rawSchema);
                try {
                    schema.validate(jsonObject);
                    server.sendResponse("{\n\"id\": \"" + server.portNumber + "\",\n\"url\": \"http://localhost:9876\",\n\"message\": \"May the best code win\"\n}", exchange, 202);
                } catch (ValidationException exception) {
                    server.sendResponse("Request body malformed", exchange, 400);
                }
            }
        }
    }
}
