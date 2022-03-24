package fr.lernejo.navy_battle;

public class Target {
    char nextLetter;
    char nextNumber;

    public Target() {
        this.nextLetter = 'A';
        this.nextNumber = '0';
    }

    public char addOneToChar(char character) {
        int nextIntCharacter = (int) character + 1;
        return (char) nextIntCharacter;
    }

    public void nextChoice() {
        if (nextNumber == '9') {
            nextNumber = '0';
            nextLetter = addOneToChar(nextLetter);
        }
        else {
            nextNumber = addOneToChar(nextNumber);
        }
    }
}
