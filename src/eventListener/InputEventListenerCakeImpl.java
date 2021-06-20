package eventListener;

import automat.*;
import events.InputEventCake;

public class InputEventListenerCakeImpl  {
    private AutomatPlaceHolder automatPlaceHolder;


    public InputEventListenerCakeImpl(AutomatPlaceHolder automatPlaceHolder){
        this.automatPlaceHolder = automatPlaceHolder;
    }


    public void onInputEvent(InputEventCake event) throws AutomatException {

        if (event.getModus().equals(":c")) {
          try {
              automatPlaceHolder.getAutomat().addKuchen(event.getKuchen());
          }catch (Exception e){
              e.printStackTrace();
          }
            }
        }

}


