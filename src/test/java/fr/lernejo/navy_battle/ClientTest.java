package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientTest {
    @Test
    void startGame() {
        Server serv1 = new Server(6789);
        Assertions.assertTrue(serv1.client.sendStartRequest("http://localhost:8989", "6789"));
    }

    @Test
    void fireACell() {
        Server serv1 = new Server(6789);
        serv1.client.adversaryUrl.add("http://localhost:8888");
        Assertions.assertTrue(serv1.client.sendGetFireRequest());
    }
}
