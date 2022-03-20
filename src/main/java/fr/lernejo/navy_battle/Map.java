package fr.lernejo.navy_battle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class Map {
    ArrayList<Ship> ships;
    int aliveShips;

    public Map() {
        ships = new ArrayList<Ship>(5);
        ships.add(new Ship(5, 0, 0));
        ships.add(new Ship(4, 3, 3));
        ships.add(new Ship(3, 9, 0));
        ships.add(new Ship(3, 0, 7));
        ships.add(new Ship(2, 6, 6));
        aliveShips = 5;
    }

    public boolean hasShipsAlive() {
        return aliveShips != 0;
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
        int beforeUpdateAliveShips = aliveShips;
        ships.forEach(ship -> {
            if (!ship.isAlive()) {
                ships.remove(ship);
                aliveShips--;
            }
        });
        return (beforeUpdateAliveShips != aliveShips);
    }

    // 2 ships cannot be hit the same time
    public Ship.State shootCell(String cell) {
        if (!isValidStringCell(cell))
            return Ship.State.MISS;
        int[] position = getCellPosition(cell);
        Cell shotCell = new Cell(position[0], position[1]);
        boolean hasShot = ships.stream().anyMatch(ship -> ship.isShot(shotCell) != Ship.State.MISS);
        if (hasShot) {
            if (hasShipDied()) {
                return Ship.State.SUNK;
            }
            return Ship.State.HIT;
        }
        return Ship.State.MISS;
    }
}
