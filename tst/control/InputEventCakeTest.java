package control;

import automat.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class InputEventCakeTest {


    @Test
    public void getKuchen() {

        KuchenKomponent cake = mock(KuchenKomponent.class);
        InputEventCake inputEventCake = new InputEventCake(this, null, cake);
        Assertions.assertEquals(cake, inputEventCake.getKuchen());

    }
}