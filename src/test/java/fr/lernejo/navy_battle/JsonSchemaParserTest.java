package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

public class JsonSchemaParserTest {
    @Test
    void basicErrorParse() {
        JsonSchemaParser jsonSchemaParser = new JsonSchemaParser();
        JSONObject obj = new JSONObject("{}");
        Assertions.assertFalse(jsonSchemaParser.isBodyValid(obj));
    }

    @Test
    void basicValidParse() {
        JsonSchemaParser jsonSchemaParser = new JsonSchemaParser();
        JSONObject obj = new JSONObject("{id:\"test\", url: \"test2\", message: \"test3\"}");
        Assertions.assertTrue(jsonSchemaParser.isBodyValid(obj));
    }
}
