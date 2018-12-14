package com.heiwait.tripagency.infrastructure.repository.springdata;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="trip")
public class TripEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="destination")
    private String destination;
    private Integer agencyFees;
    private Integer travelFees;
    private Integer ticketPrice;

    public TripEntity(){}

    public TripEntity(String destination, Integer agencyFees, Integer travelFees, Integer ticketPrice) {
        this.destination = destination;
        this.agencyFees = agencyFees;
        this.travelFees = travelFees;
        this.ticketPrice = ticketPrice;
    }

    Trip toTrip(){
        return new Trip(new Destination(destination()), agencyFees(), travelFees(), ticketPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripEntity that=(TripEntity) o;
        return Objects.equals(destination, that.destination) &&
                Objects.equals(agencyFees, that.agencyFees) &&
                Objects.equals(travelFees, that.travelFees) &&
                Objects.equals(ticketPrice, that.ticketPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, agencyFees, travelFees, ticketPrice);
    }

    public String destination() {
        return destination;
    }

    public void setDestination(String destinationName) {
        this.destination = destinationName;
    }

    private Integer agencyFees() {
        return agencyFees;
    }

    public void setAgencyFees(Integer agencyFees) {
        this.agencyFees = agencyFees;
    }

    private Integer travelFees() {
        return travelFees;
    }

    public void setTravelFees(Integer travelFees) {
        this.travelFees = travelFees;
    }

    public Integer ticketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice=ticketPrice;
    }
}