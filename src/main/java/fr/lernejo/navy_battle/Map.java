package fr.lernejo.navy_battle;

import java.util.ArrayList;

public class Map {
    final ArrayList<Ship> ships;

    public Map() {
        ships = new ArrayList<Ship>(5);
        ships.add(new Ship(5, 0, 0));
        ships.add(new Ship(4, 3, 3));
        ships.add(new Ship(3, 9, 0));
        ships.add(new Ship(3, 0, 7));
        ships.add(new Ship(2, 6, 6));
    }

    public boolean hasShipsLeft() {
        return ships.size() != 0;
    }

    public boolean isValidStringCell(String cell) {
        if (cell.length() != 2)
            return false;
        if (cell.charAt(0) < 'A' && cell.charAt(0) > 'J')
            return false;
        if (cell.charAt(1) < '0' && cell.charAt(1) > '9')
            return false;
        return true;
    }

    public int[] getCellPosition(String cell) {
        int cellX = cell.charAt(0) - 'A';
        int cellY = cell.charAt(1) - '0';
        int[] position = new int[2];
        position[0] = cellX;
        position[1] = cellY;
        return position;
    }

    public boolean hasShipDied() {
        int beforeUpdateAliveShips = ships.size();
        ships.forEach(ship -> {
            if (!ship.isAlive()) {
                ships.remove(ship);
            }
        });
        return (beforeUpdateAliveShips != ships.size());
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
