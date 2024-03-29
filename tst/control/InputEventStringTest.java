package control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InputEventStringTest {

    @Test
    void getModus() {

        String modus = "modus";
        InputEventString inputEventString = new InputEventString(this, modus, null);
        Assertions.assertEquals(modus, inputEventString.getModus());
    }

    @Test
    void getString() {
        String text = "text";
        InputEventString inputEventString = new InputEventString(this, null, text);
        Assertions.assertEquals(text, inputEventString.getString());
    }
}