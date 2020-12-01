package com.heiwait.tripagency.pricer.driven.repository.springdata;

import com.heiwait.tripagency.pricer.domain.Trip;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "trip")
public class TripEntity implements Serializable {

    @Id
    @Column(name = "destination")
    private String destination;
    private Integer agencyFees;
    private Integer stayFees;
    private Integer ticketPrice;

    Trip toTrip() {
        return new Trip.Builder().with(builder -> {
            builder.setAgencyFees(agencyFees());
            builder.setStayFees(stayFees());
            builder.setTicketPrice(ticketPrice());
        }).build();
    }

    private Integer agencyFees() {
        return agencyFees;
    }

    private Integer stayFees() {
        return stayFees;
    }

    public Integer ticketPrice() {
        return ticketPrice;
    }
}