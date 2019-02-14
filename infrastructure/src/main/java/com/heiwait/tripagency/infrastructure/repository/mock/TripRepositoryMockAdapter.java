package com.heiwait.tripagency.infrastructure.repository.mock;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import com.heiwait.tripagency.domain.error.BusinessErrors;
import com.heiwait.tripagency.domain.error.BusinessException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("TripRepositoryMockAdapter")
public class TripRepositoryMockAdapter implements TripRepositoryPort {

    @Override
    public Trip findTripByDestination(final Destination destination) {

        Trip trip = new Trip();
        trip.setDestination(destination);

        switch (destination.getName().toLowerCase()) {
            case "paris":
                trip.setTravelFees(250);
                trip.setAgencyFees(25);
                trip.setTicketPrice(150);
                break;
            case "lille":
                trip.setTravelFees(0);
                trip.setAgencyFees(0);
                trip.setTicketPrice(0);
                break;
            case "new-york":
                trip.setTravelFees(2000);
                trip.setAgencyFees(100);
                trip.setTicketPrice(1000);
                break;
            case "tokyo":
                trip.setTravelFees(3000);
                trip.setAgencyFees(150);
                trip.setTicketPrice(1100);
                break;
            case "beijing":
                trip.setTravelFees(1200);
                trip.setAgencyFees(120);
                trip.setTicketPrice(1200);
                break;
            default:
                throw new BusinessException(BusinessErrors.MISSING_DESTINATION);
        }

        return trip;
    }
}
