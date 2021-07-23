package eventListener;



import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Random;


import automat.*;
import consolePrinter.ConsolePrinter;
import eventHandlers.*;
import eventListener.*;
import events.InputEventCake;
import events.InputEventHersteller;
import events.InputEventInteger;
import events.OutputEventCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class ListenerTest {
  /* ConsolePrinter cp = new ConsolePrinter();
    AutomatVerwaltung automat= new AutomatVerwaltung(20);
    AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automat);
    Date inspektionsDate = new Date();
    Date einfügedatum = new Date();

    Hersteller hersteller1 = new HerstellerImpl("hersteller1");
    Hersteller hersteller2 = new HerstellerImpl("hersteller2");


    Cake berryCake = new ObstkuchenOldImpl(hersteller1, BigDecimal.valueOf(3.5), 200, Duration.ofDays(3), Collections.singleton(Allergen.Gluten),  getRandomDate(), -1, einfügedatum, "berry");
    Cake butterCake = new KremkuchenOldImpl(hersteller2, BigDecimal.valueOf(3.5), 200, Duration.ofDays(3), Collections.singleton(Allergen.Gluten),  getRandomDate(), -1, einfügedatum, "butter");
    Cake berryCake2 = new ObstkuchenOldImpl(hersteller1, BigDecimal.valueOf(3.5), 200, Duration.ofDays(3), Collections.singleton(Allergen.Gluten),  getRandomDate(), -1, einfügedatum, "apple");

    OutputEventHandlerCollection outputCollectionHandler = new OutputEventHandlerCollection();
    OutputEventListenerCollectionImpl lOutputCollection = new OutputEventListenerCollectionImpl(cp);
    OutputEventHandlerString outputEventHandlerString = new OutputEventHandlerString();
    InputEventHandlerCake cakeHandler = new InputEventHandlerCake();
    InputEventHandlerHersteller herstellerHandler = new InputEventHandlerHersteller();
    InputEventHandlerString stringHandler = new InputEventHandlerString();
    InputEventListenerCakeImpl lCake = new InputEventListenerCakeImpl(this.automatPlaceHolder);
    InputEventListenerHerstellerImpl lHersteller = new InputEventListenerHerstellerImpl(this.automatPlaceHolder);
    InputEventListenerStringImpl lString = new InputEventListenerStringImpl(this.automatPlaceHolder, outputCollectionHandler, outputEventHandlerString);
    InputEventHandlerInteger integerHandler = new InputEventHandlerInteger();
    InputEventListenerIntegerImpl lInteger = new InputEventListenerIntegerImpl(this.automatPlaceHolder);

    public ListenerTest() throws AutomatException {
    }


    @Test
    public void gutTestAddHersteller() throws AutomatException {
        InputEventHersteller herstellerEvent = new InputEventHersteller(this, ":c", hersteller1);
        lHersteller.onInputEvent(herstellerEvent);

        Assertions.assertTrue(automatPlaceHolder.getAutomat().getHerstellerList().contains(hersteller1));
    }

    @Test
    public void gutTestAddObstkuchen() throws AutomatException {
        automatPlaceHolder.getAutomat().addHersteller(hersteller1);

        InputEventCake cakeEvent = new InputEventCake(this, ":c", berryCake);
        lCake.onInputEvent(cakeEvent);

        Assertions.assertEquals(berryCake, automatPlaceHolder.getAutomat().getCakeList()[0]);
    }

    @Test
    public void gutTestAddKremkuchen() throws AutomatException {
        automatPlaceHolder.getAutomat().addHersteller(hersteller2);

        InputEventCake cakeEvent = new InputEventCake(this, ":c", butterCake);
        lCake.onInputEvent(cakeEvent);

        Assertions.assertEquals(butterCake, automatPlaceHolder.getAutomat().getCakeList()[0]);
    }

    @Test
    public void gutTestAddMehrereKuchen() throws AutomatException {
        automatPlaceHolder.getAutomat().addHersteller(hersteller1);
        automatPlaceHolder.getAutomat().addHersteller(hersteller2);

        InputEventCake kremkuchenEvent = new InputEventCake(this, ":c", butterCake);
        lCake.onInputEvent(kremkuchenEvent);

        InputEventCake obstkuchenEvent = new InputEventCake(this, ":c", berryCake);
        lCake.onInputEvent(obstkuchenEvent);

        Assertions.assertTrue(automatPlaceHolder.getAutomat().getHerstellerList().contains(hersteller1));
        Assertions.assertEquals(butterCake, automatPlaceHolder.getAutomat().getCakeList()[0]);
        Assertions.assertEquals(berryCake, automatPlaceHolder.getAutomat().getCakeList()[1]);

    }

    @Test
    public void gutTestRemoveObstkuchen() throws AutomatException {
        automatPlaceHolder.getAutomat().addHersteller(hersteller1);

        InputEventCake cakeAddEvent = new InputEventCake(this, ":c", berryCake);
        lCake.onInputEvent(cakeAddEvent);

        InputEventInteger cakeRemoveEvent = new InputEventInteger(this, ":d", 0);
        lInteger.onInputEvent(cakeRemoveEvent);

        Assertions.assertEquals(null, automatPlaceHolder.getAutomat().getCakeList()[0]);
    }

    @Test
    public void gutPrintKuchen() throws AutomatException {
        automatPlaceHolder.getAutomat().addHersteller(hersteller1);
        automatPlaceHolder.getAutomat().addHersteller(hersteller2);

        InputEventCake kremkuchenEvent = new InputEventCake(this, ":c", butterCake);
        lCake.onInputEvent(kremkuchenEvent);

        InputEventCake obstkuchenEvent = new InputEventCake(this, ":c", berryCake);
        lCake.onInputEvent(obstkuchenEvent);

        OutputEventCollection outputEventCollection = new OutputEventCollection(this, "kuchen", Arrays.asList(automatPlaceHolder.getAutomat().getCakeList()));
        lOutputCollection.onOutputEvent(outputEventCollection);

        Assertions.assertTrue(automatPlaceHolder.getAutomat().getHerstellerList().contains(hersteller1));
    }

    public Date getRandomDate(){
        Random rnd;
        long    ms;
        rnd = new Random();
        ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        return new Date(ms);
    }


 */


}
