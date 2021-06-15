package com.heiwait.tripagency.pricer.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TripPricerTest {
    @InjectMocks
    private TripPricer tripPricer;

    @Test
    void should_return_an_invalid_argument_exception_if_destination_is_null() {
        assertThatThrownBy(() -> tripPricer.priceTrip(null, null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}