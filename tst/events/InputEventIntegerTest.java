package events;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputEventIntegerTest {

    @Test
    public void getModus() {

        String modus = "modus";
        InputEventInteger inputEventInteger = new InputEventInteger(this, modus, null);
        Assertions.assertEquals(modus, inputEventInteger.getModus());
    }

    @Test
    public void getInteger() {

        int nummer = 2;
        InputEventInteger inputEventInteger = new InputEventInteger(this, null, nummer);
        Assertions.assertEquals(nummer, inputEventInteger.getInteger());
    }
}