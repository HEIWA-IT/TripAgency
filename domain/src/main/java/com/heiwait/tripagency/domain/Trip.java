package com.heiwait.tripagency.domain;

import java.util.Objects;

public class Trip {

    private Destination destination;
    private Integer agencyFees;
    private Integer travelFees;

    public Trip() {
    }

    public Trip(Destination destination, Integer agencyFees, Integer travelFees) {
        this.destination = destination;
        this.agencyFees = agencyFees;
        this.travelFees = travelFees;
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Trip otherTrip = (Trip) other;
        return Objects.equals(destination, otherTrip.destination) &&
                Objects.equals(agencyFees, otherTrip.agencyFees) &&
                Objects.equals(travelFees, otherTrip.travelFees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, agencyFees, travelFees);
    }

    public Destination destination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Integer agencyFees() {
        return agencyFees;
    }

    public void setAgencyFees(Integer agencyFees) {
        this.agencyFees = agencyFees;
    }

    public Integer travelFees() {
        return travelFees;
    }

    public void setTravelFees(Integer travelFees) {
        this.travelFees = travelFees;
    }
}
