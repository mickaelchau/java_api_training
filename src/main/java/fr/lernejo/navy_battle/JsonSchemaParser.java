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
    public JSONObject getRequestJson(HttpExchange exchange) {
        try (InputStream os = exchange.getRequestBody()) {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(os, StandardCharsets.UTF_8));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            return new JSONObject(responseStrBuilder.toString());
        } catch (IOException exception) {
            return null;
        }
    }

    public boolean isBodyValid(JSONObject jsonObject) {
        try (InputStream inputStream = getClass().getResourceAsStream("/SchemaJSON.json")) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            Schema schema = SchemaLoader.load(rawSchema);
            try {
                schema.validate(jsonObject);
                return true;
            } catch (ValidationException exception) {
                System.out.println(jsonObject);
                System.err.println("Schema is invalid" + exception);
            }
        } catch (IOException exception) {
            System.err.println("Schema doesn't found" + exception);
        }
        return false;
    }
}
