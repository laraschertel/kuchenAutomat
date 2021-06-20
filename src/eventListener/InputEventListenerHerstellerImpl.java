package eventListener;

import automat.AutomatException;
import automat.AutomatPlaceHolder;
import automat.AutomatVerwaltung;
import events.InputEventHersteller;

public class InputEventListenerHerstellerImpl{
    private AutomatPlaceHolder automatPlaceHolder;
    private final String INPUTERROR = "Input error";

    public InputEventListenerHerstellerImpl(AutomatPlaceHolder automatPlaceHolder){
        this.automatPlaceHolder = automatPlaceHolder;
    }


    public void onInputEvent(InputEventHersteller event) throws AutomatException {

        if (event.getModus().equals(":c")) {
            try {
                automatPlaceHolder.getAutomat().addHersteller(event.getHersteller());
            }catch (AutomatException e){
                System.err.println(INPUTERROR);
            }
        }
    }

}

