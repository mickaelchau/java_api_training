package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CellTest {
    @Test
    void same_cell() {
        int a = 10; // (1)
        int b = 2;
        Cell first = new Cell(a, b);
        Cell second = new Cell(a, b);
        boolean isSame = first.equals(second);
        Assertions.assertSame(isSame, true, "10 + 2 = 12");
    }
}
