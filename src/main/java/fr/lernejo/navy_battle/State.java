package fr.lernejo.navy_battle;

public enum State {
    HIT,
    SUNK,
    MISS;
    public String stateToString() {
        return switch (this) {
            case HIT -> "hit";
            case SUNK -> "sunk";
            case MISS -> "miss";
        };

    }
}
