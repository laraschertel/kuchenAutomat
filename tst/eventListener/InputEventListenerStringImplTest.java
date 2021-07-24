package eventListener;

import automat.*;
import control.InputEventListenerStringImpl;
import control.OutputEventHandlerHerstellerMap;
import control.InputEventString;
import control.OutputEventHerstellerMap;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InputEventListenerStringImplTest {

    @Test
    void printHerstellerMap() {

        AutomatVerwaltung automat= null;
        try {
            automat = mock(AutomatVerwaltung.class);
            AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automat);
            Hersteller h1 = new HerstellerImpl("hersteller1");
            Hersteller h2 = new HerstellerImpl("hersteller2");
            automatPlaceHolder.getAutomat().addHersteller(h1);
            automatPlaceHolder.getAutomat().addHersteller(h2);

            OutputEventHandlerHerstellerMap outputEventHandlerHerstellerMap = mock(OutputEventHandlerHerstellerMap.class);


            InputEventListenerStringImpl lString = new InputEventListenerStringImpl(automatPlaceHolder, null, null, outputEventHandlerHerstellerMap,null);

            InputEventString inputEventString = new InputEventString(this, ":r", "hersteller");

            lString.onInputEvent(inputEventString);

            verify(outputEventHandlerHerstellerMap, times(1)).handle(any(OutputEventHerstellerMap.class));

        } catch (AutomatException e) {
            fail();
        } catch (InterruptedException e) {
            fail();
        } catch (IOException e) {
            fail();
        }

    }
}