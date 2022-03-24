package fr.lernejo.navy_battle;

public class Cell {
    final int x;
    final int y;
    public Cell(int x , int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object cell) {
        if (cell instanceof Cell && ((Cell) cell).x == this.x && ((Cell) cell).y == this.y) {
            return true;
        }
        return false;
    }
}
