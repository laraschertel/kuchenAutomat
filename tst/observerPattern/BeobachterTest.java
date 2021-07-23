package observerPattern;

import automat.*;
import consolePrinter.ConsolePrinter;
import eventHandlers.OutputEventHandlerCollection;
import eventHandlers.OutputEventHandlerString;
import eventListener.OutputEventListenerStringImpl;
import eventHandlers.InputEventHandlerString;
import eventListener.InputEventListenerStringImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;

public class BeobachterTest {


    ConsolePrinter cp = new ConsolePrinter();
    AutomatVerwaltung automat= new AutomatVerwaltung(20);
    AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automat);

    Date inspektionsDate = new Date();
    Date einfügedatum = new Date();

    Hersteller hersteller1 = new HerstellerImpl("hersteller1");
    Hersteller hersteller2 = new HerstellerImpl("hersteller2");


   //// KuchenKomponent berryCake = new KuchenBoden(Kuchentyp.OBSTKUCHEN, hersteller1 ,BigDecimal.valueOf(3.5),200, Duration.ofDays(3), EnumSet.of(Allergen.Gluten)  );
    KuchenKomponent butterCake = new KuchenBoden(Kuchentyp.KREMKUCHEN, hersteller1,BigDecimal.valueOf(3.5),200, Duration.ofDays(3), EnumSet.of(Allergen.Gluten)  );


    InputEventHandlerString stringHandler = new InputEventHandlerString();
    OutputEventHandlerCollection outputCollectionHandler = new OutputEventHandlerCollection();
    OutputEventHandlerString outputEventHandlerString = new OutputEventHandlerString();

    //InputEventListenerStringImpl lString = new InputEventListenerStringImpl(automatPlaceHolder, outputCollectionHandler, outputEventHandlerString);
    OutputEventListenerStringImpl lOutputString = new OutputEventListenerStringImpl(cp);


    public BeobachterTest() throws AutomatException {
    }

/*
    @Test
    public void gutTestCapacity() throws AutomatException, IOException, InterruptedException {
        stringHandler.add(lString);
        outputEventHandlerString.add(lOutputString);
        KapazitaetBeobachter kapazitaetBeobachter = new KapazitaetBeobachter(automatPlaceHolder, outputEventHandlerString);


        automatPlaceHolder.getAutomat().addHersteller(hersteller1);
        automatPlaceHolder.getAutomat().addHersteller(hersteller2);

        automatPlaceHolder.getAutomat().addKuchen(butterCake);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        automatPlaceHolder.getAutomat().addKuchen(berryCake);

        Assertions.assertEquals("mehr als 90% der Kapazität erreicht" + System.lineSeparator() , byteArrayOutputStream.toString() );
        byteArrayOutputStream.close();
    }

    @Test
    public void gutTestAllergeneÄnderung() throws AutomatException, IOException, InterruptedException {
        stringHandler.add(lString);
        outputEventHandlerString.add(lOutputString);
        AllergeneBeobachter allergeneBeobachter = new AllergeneBeobachter(automatPlaceHolder, outputEventHandlerString);


        automatPlaceHolder.getAutomat().addHersteller(hersteller2);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        automatPlaceHolder.getAutomat().addKuchen(butterCake);

        Assertions.assertEquals("Allergene Änderung" + System.lineSeparator() , byteArrayOutputStream.toString() );
        byteArrayOutputStream.close();
    }

    @Test
    public void gutTestKeineAllergeneÄnderung() throws AutomatException, IOException, InterruptedException {
        stringHandler.add(lString);
        outputEventHandlerString.add(lOutputString);
        AllergeneBeobachter allergeneBeobachter = new AllergeneBeobachter(automatPlaceHolder, outputEventHandlerString);

        automatPlaceHolder.getAutomat().addHersteller(hersteller1);
        automatPlaceHolder.getAutomat().addHersteller(hersteller2);

        automatPlaceHolder.getAutomat().addKuchen(butterCake);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        automatPlaceHolder.getAutomat().addKuchen(berryCake);

        Assertions.assertNotEquals("Allergene Änderung" + System.lineSeparator() , byteArrayOutputStream.toString() );
        byteArrayOutputStream.close();
    }



 */

}
