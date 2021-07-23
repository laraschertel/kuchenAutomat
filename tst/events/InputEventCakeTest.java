package events;

import automat.*;
import consolePrinter.ConsolePrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class InputEventCakeTest {


    @Test
    public void getKuchen() {

        KuchenKomponent cake = mock(KuchenKomponent.class);
        InputEventCake inputEventCake = new InputEventCake(this, null, cake);
        Assertions.assertEquals(cake, inputEventCake.getKuchen());

    }
}