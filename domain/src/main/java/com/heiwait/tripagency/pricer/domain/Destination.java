package com.heiwait.tripagency.pricer.domain;

public class Destination {
    private final String name;

    public Destination(final String destinationName) {
        checkDestination(destinationName);
        name = destinationName;
    }

    private void checkDestination(final String destinationName) {
        if (destinationName == null)
            throw new IllegalArgumentException();
    }

    public String name() {
        return name;
    }
}