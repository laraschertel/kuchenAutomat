package eventListener;

import automat.AutomatException;
import automat.AutomatVerwaltung;
import events.InputEventInteger;

public class InputEventListenerIntegerImpl {
    private AutomatVerwaltung automat;

   public InputEventListenerIntegerImpl (AutomatVerwaltung automat){
        this.automat = automat;
    }


    public void onInputEvent(InputEventInteger event) throws AutomatException {
        if (event.getModus().equals(":d")) {
            try {
                automat.removeKuchen(event.getInteger());
            }catch (AutomatException | InterruptedException e){
               throw new AutomatException("Kein kuchen mit gegebenem Fachnummer gefunden");
            }
        }
    }
}

