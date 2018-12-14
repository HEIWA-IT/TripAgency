package com.heiwait.tripagency.domain;

import java.util.Objects;

public class Trip {

    private Destination destination;
    private Integer agencyFees;
    private Integer travelFees;
    private TravelClass travelClass;
    private Integer ticketPrice;

    public Trip() {
    }

    public Trip(Destination destination, Integer agencyFees, Integer travelFees, Integer ticketPrice) {
        this.destination = destination;
        this.agencyFees = agencyFees;
        this.travelFees = travelFees;
        this.ticketPrice = ticketPrice;
    }

    public Trip(Destination destination, Integer agencyFees, Integer travelFees, Integer ticketPrice, TravelClass travelClass) {
        this.destination = destination;
        this.agencyFees = agencyFees;
        this.travelFees = travelFees;
        this.ticketPrice = ticketPrice;
        this.travelClass = travelClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip=(Trip) o;
        return  Objects.equals(ticketPrice, trip.ticketPrice) &&
                Objects.equals(destination, trip.destination) &&
                Objects.equals(agencyFees, trip.agencyFees) &&
                Objects.equals(travelFees, trip.travelFees) &&
                travelClass == trip.travelClass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, agencyFees, travelFees, travelClass, ticketPrice);
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

    Integer travelFees() {
        return travelFees;
    }

    public void setTravelFees(Integer travelFees) {
        this.travelFees = travelFees;
    }

    public void setTravelClass(TravelClass travelClass) {
        this.travelClass = travelClass;
    }

    TravelClass travelClass() {
        return travelClass;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice=ticketPrice;
    }

    public int ticketPrice() {
        return ticketPrice;
    }
}
