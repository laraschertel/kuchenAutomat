package control;

import automat.*;
import control.InputEventHersteller;
import control.InputEventListenerHerstellerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputEventListenerHerstellerImplTest {

    @Test
    void addHersteller() {
        try{
        AutomatVerwaltung automat= new AutomatVerwaltung(20);
        AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automat);
        Hersteller h1 = new HerstellerImpl("hersteller1");

        InputEventListenerHerstellerImpl lHersteller = new InputEventListenerHerstellerImpl(automatPlaceHolder);
        InputEventHersteller herstellerEvent = new InputEventHersteller(this, ":c", h1);
        lHersteller.onInputEvent(herstellerEvent);

        Assertions.assertTrue(automatPlaceHolder.getAutomat().getHerstellerList().contains(h1));
    }catch (AutomatException e){
            fail();
        }
    }
}