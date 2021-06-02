package eventListener;

import automat.AutomatException;
import automat.AutomatVerwaltung;
import events.InputEventHersteller;

public class InputEventListenerHerstellerImpl{
    private AutomatVerwaltung automat;
    private final String INPUTERROR = "Input error";

    public InputEventListenerHerstellerImpl(AutomatVerwaltung automat){
        this.automat = automat;
    }


    public void onInputEvent(InputEventHersteller event) throws AutomatException {

        if (event.getModus().equals(":c")) {
            try {
                automat.addHersteller(event.getHersteller());
            }catch (AutomatException e){
                System.err.println(INPUTERROR);
            }
        }
    }

}

