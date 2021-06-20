package eventListener;

import automat.AutomatException;
import automat.AutomatPlaceHolder;
import automat.AutomatVerwaltung;
import events.InputEventInteger;

public class InputEventListenerIntegerImpl {
    private AutomatPlaceHolder automatPlaceHolder;

   public InputEventListenerIntegerImpl (AutomatPlaceHolder automatPlaceHolder){
        this.automatPlaceHolder = automatPlaceHolder;
    }


    public void onInputEvent(InputEventInteger event) throws AutomatException {
        if (event.getModus().equals(":d")) {
            try {
                automatPlaceHolder.getAutomat().removeKuchen(event.getInteger());
            }catch (AutomatException | InterruptedException e){
               throw new AutomatException("Kein kuchen mit gegebenem Fachnummer gefunden");
            }
        }
    }
}

