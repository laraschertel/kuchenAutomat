package events;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutputEventStringTest {

    @Test
    void getString() {

        String text = "text";
        OutputEventString outputEventString = new OutputEventString(this, text);
        Assertions.assertEquals(text, outputEventString.getString());
    }
}