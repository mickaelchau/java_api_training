package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StartHandlerTest {
    @Test
    void basicStart() {
        Server serv1 = new Server(8888);
        Server serv2 = new Server(8887);
        Assertions.assertTrue(serv1.client.sendStartRequest("http://localhost:8887", "8888"));
    }
}
