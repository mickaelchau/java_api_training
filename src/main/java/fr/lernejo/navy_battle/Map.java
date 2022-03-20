package fr.lernejo.navy_battle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class Map {
    ArrayList<Ship> ships;
    int leftShips;

    public Map() {
        ships = new ArrayList<Ship>(5);
        ships.add(new Ship(5, 0, 0));
        ships.add(new Ship(4, 3, 3));
        ships.add(new Ship(3, 9, 0));
        ships.add(new Ship(3, 0, 7));
        ships.add(new Ship(2, 6, 6));
        leftShips = 5;
    }

    public boolean hasShipsLeft() {
        return leftShips != 0;
    }

    private boolean isValidStringCell(String cell) {
        if (cell.length() != 2)
            return false;
        if (cell.charAt(0) < 'A' && cell.charAt(0) > 'J')
            return false;
        if (cell.charAt(1) < '0' && cell.charAt(1) > '9')
            return false;
        return true;
    }

    private int[] getCellPosition(String cell) {
        int cellX = cell.charAt(0) - 'A';
        int cellY = cell.charAt(1) - '0';
        int[] position = new int[2];
        position[0] = cellX;
        position[1] = cellY;
        return position;
    }

    private boolean hasShipDied() {
        int beforeUpdateAliveShips = leftShips;
        ships.forEach(ship -> {
            if (!ship.isAlive()) {
                ships.remove(ship);
                leftShips--;
            }
        });
        return (beforeUpdateAliveShips != leftShips);
    }

    // 2 ships cannot be hit the same time
    public State shootCell(String cell) {
        if (!isValidStringCell(cell))
            return State.MISS;
        int[] position = getCellPosition(cell);
        Cell shotCell = new Cell(position[0], position[1]);
        boolean hasShot = ships.stream().anyMatch(ship -> ship.isShot(shotCell) != State.MISS);
        if (hasShot) {
            if (hasShipDied()) {
                return State.SUNK;
            }
            return State.HIT;
        }
        return State.MISS;
    }
}
