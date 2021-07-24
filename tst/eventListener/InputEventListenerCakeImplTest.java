package eventListener;

import automat.*;
import control.InputEventCake;
import control.InputEventListenerCakeImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

class InputEventListenerCakeImplTest {

    @Test
    void addCakeTest() {

        try {
            AutomatVerwaltung automat = new AutomatVerwaltung(20);
            AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automat);
            Hersteller h1 = new HerstellerImpl("hersteller1");
            automatPlaceHolder.getAutomat().addHersteller(h1);
            KuchenKomponent obstkuchen = new KuchenBoden(Kuchentyp.OBSTKUCHEN, h1, BigDecimal.valueOf(3.5), 200, Duration.ofDays(3), EnumSet.of(Allergen.Gluten));

            InputEventListenerCakeImpl lCake = new InputEventListenerCakeImpl(automatPlaceHolder);
            InputEventCake cakeEvent = new InputEventCake(this, ":c", obstkuchen);
            lCake.onInputEvent(cakeEvent);

            Assertions.assertEquals(obstkuchen, automatPlaceHolder.getAutomat().getCakeList()[0]);
        }catch (AutomatException e){
            fail();
        }
    }

    @Test
    void addMultipleCakeTest() {

        try {
            AutomatVerwaltung automat = new AutomatVerwaltung(20);
            AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automat);
            Hersteller h1 = new HerstellerImpl("hersteller1");
            automatPlaceHolder.getAutomat().addHersteller(h1);
            KuchenKomponent obstkuchen = new KuchenBoden(Kuchentyp.OBSTKUCHEN, h1, BigDecimal.valueOf(3.5), 200, Duration.ofDays(3), EnumSet.of(Allergen.Gluten));
            KuchenKomponent kremkuchen = new KuchenBoden(Kuchentyp.KREMKUCHEN, h1,BigDecimal.valueOf(3.5),200, Duration.ofDays(3), EnumSet.of(Allergen.Gluten));

            InputEventListenerCakeImpl lCake = new InputEventListenerCakeImpl(automatPlaceHolder);
            InputEventCake cakeEvent = new InputEventCake(this, ":c", obstkuchen);
            lCake.onInputEvent(cakeEvent);

            InputEventCake cakeEvent2 = new InputEventCake(this, ":c", kremkuchen);
            lCake.onInputEvent(cakeEvent2);


            Assertions.assertEquals(2, automatPlaceHolder.getAutomat().getAnzahlKuchenImAutomat());
        }catch (AutomatException e){
            fail();
        }
    }
}