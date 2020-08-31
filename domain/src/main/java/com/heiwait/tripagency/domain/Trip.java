package com.heiwait.tripagency.domain;

import java.util.Objects;

public class Trip {

    private Destination destination;
    private Integer agencyFees;
    private Integer stayFees;
    private Integer ticketPrice;

    public Trip() {
    }

    public Trip(Destination destination, Integer agencyFees, Integer stayFees, Integer ticketPrice) {
        this.destination = destination;
        this.agencyFees = agencyFees;
        this.stayFees = stayFees;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip=(Trip) o;
        return  Objects.equals(ticketPrice, trip.ticketPrice) &&
                Objects.equals(destination, trip.destination) &&
                Objects.equals(agencyFees, trip.agencyFees) &&
                Objects.equals(stayFees, trip.stayFees) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, agencyFees, stayFees, ticketPrice);
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
        return stayFees;
    }

    public void setStayFees(Integer stayFees) {
        this.stayFees = stayFees;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice=ticketPrice;
    }

    public int ticketPrice() {
        return ticketPrice;
    }
}