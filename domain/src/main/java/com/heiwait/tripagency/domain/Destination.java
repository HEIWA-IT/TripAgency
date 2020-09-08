package com.heiwait.tripagency.domain;

public class Destination {
    private final String name;

    public Destination(final String destinationName) {
        name = destinationName;
    }

    public String name() {
        return name;
    }
}