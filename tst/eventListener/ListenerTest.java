package eventListener;



import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Random;


import automat.*;
import consolePrinter.ConsolePrinter;
import events.InputEventCake;
import events.InputEventHersteller;
import events.InputEventInteger;
import events.OutputEventCollection;
import eventHandlers.InputEventHandlerCake;
import eventHandlers.InputEventHandlerHersteller;
import eventHandlers.InputEventHandlerInteger;
import eventHandlers.InputEventHandlerString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import eventHandlers.OutputEventHandlerCollection;


public class ListenerTest {
    ConsolePrinter cp = new ConsolePrinter();
    AutomatVerwaltung automat = new AutomatVerwaltung(2);
    Date inspektionsDate = new Date();
    Date einfügedatum = new Date();

    Hersteller hersteller1 = new HerstellerImpl("hersteller1");
    Hersteller hersteller2 = new HerstellerImpl("hersteller2");


    Cake berryCake = new ObstkuchenImpl(hersteller1, BigDecimal.valueOf(3.5), 200, Duration.ofDays(3), Collections.singleton(Allergen.Gluten),  getRandomDate(), -1, einfügedatum, "berry");
    Cake butterCake = new KremkuchenImpl(hersteller2, BigDecimal.valueOf(3.5), 200, Duration.ofDays(3), Collections.singleton(Allergen.Gluten),  getRandomDate(), -1, einfügedatum, "butter");
    Cake berryCake2 = new ObstkuchenImpl(hersteller1, BigDecimal.valueOf(3.5), 200, Duration.ofDays(3), Collections.singleton(Allergen.Gluten),  getRandomDate(), -1, einfügedatum, "apple");

    OutputEventHandlerCollection outputCollectionHandler = new OutputEventHandlerCollection();
    OutputEventListenerCollectionImpl lOutputCollection = new OutputEventListenerCollectionImpl(cp);
    InputEventHandlerCake cakeHandler = new InputEventHandlerCake();
    InputEventHandlerHersteller herstellerHandler = new InputEventHandlerHersteller();
    InputEventHandlerString stringHandler = new InputEventHandlerString();
    InputEventListenerCakeImpl lCake = new InputEventListenerCakeImpl(this.automat);
    InputEventListenerHerstellerImpl lHersteller = new InputEventListenerHerstellerImpl(this.automat);
    InputEventListenerStringImpl lString = new InputEventListenerStringImpl(this.automat, outputCollectionHandler);
    InputEventHandlerInteger integerHandler = new InputEventHandlerInteger();
    InputEventListenerIntegerImpl lInteger = new InputEventListenerIntegerImpl(this.automat);

    public ListenerTest() throws AutomatException {
    }


    @Test
    public void gutTestAddHersteller() throws AutomatException {
        InputEventHersteller herstellerEvent = new InputEventHersteller(this, ":c", hersteller1);
        lHersteller.onInputEvent(herstellerEvent);

        Assertions.assertTrue(automat.getHerstellerList().contains(hersteller1));
    }

    @Test
    public void gutTestAddObstkuchen() throws AutomatException {
        automat.addHersteller(hersteller1);

        InputEventCake cakeEvent = new InputEventCake(this, ":c", berryCake);
        lCake.onInputEvent(cakeEvent);

        Assertions.assertEquals(berryCake, automat.getCakeList()[0]);
    }

    @Test
    public void gutTestAddKremkuchen() throws AutomatException {
        automat.addHersteller(hersteller2);

        InputEventCake cakeEvent = new InputEventCake(this, ":c", butterCake);
        lCake.onInputEvent(cakeEvent);

        Assertions.assertEquals(butterCake, automat.getCakeList()[0]);
    }

    @Test
    public void gutTestAddMehrereKcuhen() throws AutomatException {
        automat.addHersteller(hersteller1);
        automat.addHersteller(hersteller2);

        InputEventCake kremkuchenEvent = new InputEventCake(this, ":c", butterCake);
        lCake.onInputEvent(kremkuchenEvent);

        InputEventCake obstkuchenEvent = new InputEventCake(this, ":c", berryCake);
        lCake.onInputEvent(obstkuchenEvent);

        Assertions.assertTrue(automat.getHerstellerList().contains(hersteller1));
        Assertions.assertEquals(butterCake, automat.getCakeList()[0]);
        Assertions.assertEquals(berryCake, automat.getCakeList()[1]);

    }

    @Test
    public void gutTestRemoveObstkuchen() throws AutomatException {
        automat.addHersteller(hersteller1);

        InputEventCake cakeAddEvent = new InputEventCake(this, ":c", berryCake);
        lCake.onInputEvent(cakeAddEvent);

        InputEventInteger cakeRemoveEvent = new InputEventInteger(this, ":d", 0);
        lInteger.onInputEvent(cakeRemoveEvent);

        Assertions.assertEquals(null, automat.getCakeList()[0]);
    }

    @Test
    public void gutPrintKuchen() throws AutomatException {
        automat.addHersteller(hersteller1);
        automat.addHersteller(hersteller2);

        InputEventCake kremkuchenEvent = new InputEventCake(this, ":c", butterCake);
        lCake.onInputEvent(kremkuchenEvent);

        InputEventCake obstkuchenEvent = new InputEventCake(this, ":c", berryCake);
        lCake.onInputEvent(obstkuchenEvent);

        OutputEventCollection outputEventCollection = new OutputEventCollection(this, "kuchen", Arrays.asList(automat.getCakeList()));
        lOutputCollection.onOutputEvent(outputEventCollection);

        Assertions.assertTrue(automat.getHerstellerList().contains(hersteller1));
    }

    public Date getRandomDate(){
        Random rnd;
        long    ms;
        rnd = new Random();
        ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        return new Date(ms);
    }


}
