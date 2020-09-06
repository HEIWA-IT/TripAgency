package com.heiwait.tripagency.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
