package com.heiwait.tripagency.domain.tu;

import com.heiwait.tripagency.domain.TripPricer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class TripPricerTest {
    @InjectMocks
    private TripPricer travelPricer;

    @Test
    public void computeTravelPrice_should_return_an_invalid_argument_exception_if_destination_is_null() {
        assertThatThrownBy(() -> travelPricer.priceTravel(null, null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}