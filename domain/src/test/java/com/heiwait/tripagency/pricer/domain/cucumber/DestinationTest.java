package com.heiwait.tripagency.pricer.domain.cucumber;

import com.heiwait.tripagency.pricer.domain.Destination;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class DestinationTest {

    @Test
    public void should_throw_an_illegal_argument_exception_with_a_null_name() {
        String destinationName = null;
                assertThatIllegalArgumentException().isThrownBy(() -> {
            Destination destination = new Destination(destinationName);
        });
    }
}