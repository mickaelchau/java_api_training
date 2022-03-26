package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShipTest {
    @Test
    void basicAppendPositions() {
        Ship ship = new Ship(5, 0, 0);
        Assertions.assertEquals(ship.appendPositions(3, 3), new Cell(3, 3));
    }

    @Test
    void isAlive() {
        Ship ship = new Ship(5, 0 ,0);
        Assertions.assertTrue(ship.isAlive());
    }

    @Test
    void isShot() {
        Ship ship = new Ship(5, 0 ,0);
        Assertions.assertEquals(ship.isShot(new Cell(0, 0)), State.HIT);
    }

    @Test
    void isMiss() {
        Ship ship = new Ship(5, 0 ,0);
        Assertions.assertEquals(ship.isShot(new Cell(9, 9)), State.MISS);
    }
}
