package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TargetTest {
    @Test
    void getNextMove() {
        Target target = new Target();
        Assertions.assertEquals("A0", target.getMove());
    }
}
