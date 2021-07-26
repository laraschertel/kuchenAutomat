package control;

import automat.*;
import consolePrinter.ConsolePrinter;
import control.OutputEventHerstellerMap;
import control.OutputEventListenerHerstellerMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OutputEventListenerHerstellerMapTest {

    @Test
    void printHerstellerMap() {
        try {
            ConsolePrinter cp = mock(ConsolePrinter.class);
            AutomatVerwaltung automat = mock(AutomatVerwaltung.class);
            AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automat);

            Hersteller h1 = new HerstellerImpl("hersteller1");
            Hersteller h2 = new HerstellerImpl("hersteller2");
            automatPlaceHolder.getAutomat().addHersteller(h1);
            automatPlaceHolder.getAutomat().addHersteller(h2);


            OutputEventListenerHerstellerMap lOutputEventListenerHerstellerMap = new OutputEventListenerHerstellerMap(cp);

            OutputEventHerstellerMap outputEventHerstellerMap = new OutputEventHerstellerMap(this, "hersteller", automatPlaceHolder.getAutomat().getHerstellerListMitKuchenAnzahl());

            lOutputEventListenerHerstellerMap.onOutputEvent(outputEventHerstellerMap);
            verify(cp, times(1)).printHerstellerHashMap(any(OutputEventHerstellerMap.class));
        } catch (AutomatException e) {
            fail();
        }
    }
}