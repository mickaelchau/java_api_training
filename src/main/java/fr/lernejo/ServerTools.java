package fr.lernejo;
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

    public void handlePostRequest(HttpExchange exchange) throws IOException{
        try (InputStream os = exchange.getRequestBody()) { 
            JSONObject jsonObject = getRequestJson(os);
            try (InputStream inputStream = getClass().getResourceAsStream("SchemaJSON.json")) {
                JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
                Schema schema = SchemaLoader.load(rawSchema);
                try {
                    schema.validate(jsonObject);
                    sendResponse("{\n\"id\": \"2aca7611-0ae4-49f3-bf63-75bef4769028\",\n\"url\": \"http://localhost:9876\",\n\"message\": \"May the best code win\"\n}", exchange, 202);
                } catch (ValidationException exception) {
                    sendResponse("Request body malformed", exchange, 400);
                    return;
                }
            }
        }    
    }
}
