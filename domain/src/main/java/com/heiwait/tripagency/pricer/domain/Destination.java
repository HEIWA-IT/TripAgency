package com.bnpparibas.hackathon.yellowteam.yellowproject.domain;

public class Destination {
    private final String name;

    public Destination(final String destinationName) {
        name = destinationName;
    }

    public String name() {
        return name;
    }
}