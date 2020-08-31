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
                trip.setStayFees(250);
                trip.setAgencyFees(25);
                trip.setTicketPrice(150);
                break;
            case "lille":
                trip.setStayFees(0);
                trip.setAgencyFees(0);
                trip.setTicketPrice(0);
                break;
            case "new-york":
                trip.setStayFees(2000);
                trip.setAgencyFees(100);
                trip.setTicketPrice(1000);
                break;
            case "tokyo":
                trip.setStayFees(3000);
                trip.setAgencyFees(150);
                trip.setTicketPrice(1100);
                break;
            case "beijing":
                trip.setStayFees(1200);
                trip.setAgencyFees(120);
                trip.setTicketPrice(1200);
                break;
            default:
                throw new BusinessException(BusinessErrors.MISSING_DESTINATION);
        }

        return trip;
    }
}
