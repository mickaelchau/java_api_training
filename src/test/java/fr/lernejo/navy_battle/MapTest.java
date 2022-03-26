package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapTest {
    @Test
    void stillAliveShips() {
        Map map = new Map();
        Assertions.assertTrue(map.hasShipsLeft());
    }

    @Test
    void isValidCell() {
        Map map = new Map();
        Assertions.assertTrue(map.isValidStringCell("A0"));
    }

    @Test
    void isInvalidCell() {
        Map map = new Map();
        Assertions.assertFalse(map.isValidStringCell("87890"));
    }

    @Test
    void getCell() {
        Map map = new Map();
        int[] result = map.getCellPosition("A0");
        Assertions.assertEquals(result[0], 0);
        Assertions.assertEquals(result[1], 0);
    }

    @Test
    void hasDied() {
        Map map = new Map();
        Assertions.assertFalse(map.hasShipDied());
    }

    @Test
    void hasHit() {
        Map map = new Map();
        Assertions.assertEquals(map.shootCell("A0"), State.HIT);
    }

    @Test
    void hasMiss() {
        Map map = new Map();
        Assertions.assertEquals(map.shootCell("J9"), State.MISS);
    }
}
