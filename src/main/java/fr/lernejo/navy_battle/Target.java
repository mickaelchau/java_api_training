package fr.lernejo.navy_battle;

import java.util.ArrayList;

public class Target {
    final ArrayList<String> nextMove;

    public Target() {
        nextMove = initMoves();
    }

    private ArrayList<String> initMoves() {
        ArrayList<String> allMoves = new ArrayList<>(100);
        for (char c = 'A'; c <= 'J'; c++) {
            for (char m = '0'; m <= '9'; m++) {
                allMoves.add(String.valueOf(c) + m);
            }
        }
        return allMoves;
    }

    public String getMove() {
        String moveToReturn = nextMove.get(0);
        nextMove.remove(0);
        return  moveToReturn;
    }
}
