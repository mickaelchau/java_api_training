package fr.lernejo.navy_battle;

public class Target {
    char nextLetter;
    char nextNumber;

    public Target() {
        this.nextLetter = 'A';
        this.nextNumber = '0';
    }

    public void nextChoice() {
        if (nextNumber == '9') {
            nextNumber = '0';
            int intLetter = nextLetter;
            int nextIntLetter = intLetter + 1;
            nextLetter = (char) nextIntLetter;
        }
        else {
            int intNumber = nextNumber;
            int nextIntNumber = intNumber + 1;
            nextNumber = (char) nextIntNumber;
        }
    }
}
