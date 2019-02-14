package com.heiwait.tripagency.domain;

public class Destination {
    private String name;

    public Destination() {
    }

    public Destination(String destinationName) {
        name = destinationName;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
