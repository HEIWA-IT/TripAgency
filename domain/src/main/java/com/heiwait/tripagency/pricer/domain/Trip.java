package com.heiwait.tripagency.pricer.domain;

import java.util.function.Consumer;

public class Trip {

    private final Integer agencyFees;
    private final Integer stayFees;
    private final Integer ticketPrice;

    private Trip(Builder builder) {
        this.agencyFees = builder.agencyFees();
        this.stayFees = builder.stayFees();
        this.ticketPrice = builder.ticketPrice();
    }

    private Trip(Integer agencyFees, Integer stayFees, Integer ticketPrice) {
        this.agencyFees = agencyFees;
        this.stayFees = stayFees;
        this.ticketPrice = ticketPrice;
    }

    public Integer agencyFees() {
        return agencyFees;
    }

    public Integer stayFees() {
        return stayFees;
    }

    public int ticketPrice() {
        return ticketPrice;
    }

    public static class Builder {

        public static final Trip MISSING_DESTINATION = new Trip(0, 0, 0);

        private Integer agencyFees;
        private Integer stayFees;
        private Integer ticketPrice;

        public Builder with(Consumer<Builder> consumer) {
            consumer.accept(this);
            return this;
        }

        public Trip build() {
            return new Trip(this);
        }

        public Integer agencyFees() {
            return agencyFees;
        }

        public Integer stayFees() {
            return stayFees;
        }

        public int ticketPrice() {
            return ticketPrice;
        }

        public void setAgencyFees(Integer agencyFees) {
            this.agencyFees = agencyFees;
        }

        public void setStayFees(Integer stayFees) {
            this.stayFees = stayFees;
        }

        public void setTicketPrice(Integer ticketPrice) {
            this.ticketPrice = ticketPrice;
        }
    }
}