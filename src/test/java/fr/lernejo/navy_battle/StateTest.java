package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StateTest {
    @Test
    void getMiss() {
       Assertions.assertEquals(State.MISS.stateToString(), "miss");
    }
    @Test
    void getSunk() {
        Assertions.assertEquals(State.SUNK.stateToString(), "sunk");
    }

    @Test
    void getHit() {
        Assertions.assertEquals(State.HIT.stateToString(), "hit");
    }
}
