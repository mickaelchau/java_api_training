package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JsonSchemaParser {
    public JSONObject getRequestJson(InputStream os) throws IOException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(os, StandardCharsets.UTF_8));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        return new JSONObject(responseStrBuilder.toString());
    }

    public boolean isBodyValid(HttpExchange exchange) throws IOException {
        try (InputStream os = exchange.getRequestBody()) {
            JSONObject jsonObject = getRequestJson(os);
            try (InputStream inputStream = getClass().getResourceAsStream("/SchemaJSON.json")) {
                JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
                Schema schema = SchemaLoader.load(rawSchema);
                try {
                    schema.validate(jsonObject);
                    return true;
                } catch (ValidationException exception) {
                    return false;
                }
            }
        }
    }
}
