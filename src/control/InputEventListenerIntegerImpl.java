package control;

import automat.AutomatException;
import automat.AutomatPlaceHolder;

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
        if(event.getModus().equals(":u")){
            try {
                automatPlaceHolder.getAutomat().inspectCake(event.getInteger());
            }catch (AutomatException e){
                throw new AutomatException("Kein kuchen mit gegebenem Fachnummer gefunden");
            }
        }
    }
}

