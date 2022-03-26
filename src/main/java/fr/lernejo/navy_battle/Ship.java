package fr.lernejo.navy_battle;

import java.util.ArrayList;

public class Ship {

    final ArrayList<Cell> positions;

    public Cell appendPositions(int startX, int startY) {
        Cell position = new Cell(startX, startY);
        positions.add(position);
        return position;
    }
    public Ship(int nbCells, int startX, int startY) {
        positions = new ArrayList<Cell>(nbCells);
        if (startY > 7)
            startY = 7;
        if (startX > 8)
            startX = 8;
        // Always 2 tuples in the first line of position
        for (int i = 0; i <  2; i++) {
            appendPositions(startX, startY + i);
        }
        // Adding remaining tuples
        startX++;
        for (int i = 0; i < nbCells - 2; i++) {
            appendPositions(startX, startY + i);
        }
    }

    public boolean isAlive() {
        return !(positions.size() == 0);
    }

    public State isShot(Cell cell) {
        if (positions.contains(cell)) {
            positions.remove(cell);
            if (isAlive())
                return State.HIT;
            else
                return State.SUNK;
        }
        return State.MISS;
    }
}
