package com.heiwait.tripagency.domain;

public class TripBuilder {

    private Integer agencyFees;
    private Integer stayFees;
    private Integer ticketPrice;

    public TripBuilder withAgencyFees(Integer agencyFees) {
        this.agencyFees = agencyFees;
        return this;
    }

    public TripBuilder withStayFees(Integer stayFees) {
        this.stayFees = stayFees;
        return this;
    }

    public TripBuilder withTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public Trip build() {
        return new Trip(agencyFees, stayFees, ticketPrice);
    }
}
