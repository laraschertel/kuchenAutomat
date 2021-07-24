package control;

import automat.Hersteller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class InputEventHerstellerTest {

    @Test
    public void getModus(){

        String modus = "modus";
        InputEventHersteller inputEventHersteller = new InputEventHersteller(this, modus, null);
        Assertions.assertEquals(modus, inputEventHersteller.getModus());
    }

    @Test
    public void getHersteller() {

        Hersteller hersteller = mock(Hersteller.class);
        InputEventHersteller inputEventHersteller = new InputEventHersteller(this, null, hersteller);
        Assertions.assertEquals(hersteller, inputEventHersteller.getHersteller());
    }

}