package eventListener;

import automat.*;
import events.InputEventCake;

public class InputEventListenerCakeImpl  {
    private AutomatVerwaltung automat;


    public InputEventListenerCakeImpl(AutomatVerwaltung automat){
        this.automat = automat;
    }


    public void onInputEvent(InputEventCake event) throws AutomatException {

        if (event.getModus().equals(":c")) {
          try {
              automat.addKuchen(event.getKuchen());
          }catch (Exception e){
              e.printStackTrace();
          }
            }
        }

}


