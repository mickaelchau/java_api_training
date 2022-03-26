package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ServerTest {
    @Test
    void runServer() {
        Server server = new Server(7888);
        Assertions.assertTrue(server.runHttpServer());
    }

}
