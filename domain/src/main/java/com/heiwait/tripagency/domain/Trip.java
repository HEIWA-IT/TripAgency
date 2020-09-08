package com.heiwait.tripagency.domain;

public class Trip {

    private final Destination destination;
    private final Integer agencyFees;
    private final Integer stayFees;
    private final Integer ticketPrice;

    public static final Trip MISSING_DESTINATION = new Trip(new Destination("Missing destination"), 0, 0, 0);

    public Trip(Destination destination, Integer agencyFees, Integer stayFees, Integer ticketPrice) {
        this.destination = destination;
        this.agencyFees = agencyFees;
        this.stayFees = stayFees;
        this.ticketPrice = ticketPrice;
    }

    public Integer agencyFees() {
        return agencyFees;
    }

    public Integer travelFees() {
        return stayFees;
    }

    public int ticketPrice() {
        return ticketPrice;
    }
}