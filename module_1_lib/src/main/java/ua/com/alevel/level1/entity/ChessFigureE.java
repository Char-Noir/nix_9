package ua.com.alevel.level1.entity;

public enum ChessFigureE {
    Knight("♘");
    private final String emodj;

    ChessFigureE(String emodj) {
        this.emodj = emodj;
    }

    @Override
    public String toString() {
        return emodj;
    }
}
