package com.bnpparibas.hackathon.yellowteam.yellowproject.domain;

public enum TravelClass {
    ECONOMIC(1), FIRST(2), BUSINESS(5);

    private final int coefficient;

    TravelClass(int coefficient) {
        this.coefficient = coefficient;
    }

    public int coefficient() {
        return coefficient;
    }
}
