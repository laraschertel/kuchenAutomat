package eventListener;

import events.InputEvent;

public class InputEventListenerExit  {

    public void onInputEvent(InputEvent event) {
        if(null!=event.getModus()&&event.getModus().equals("exit"))
            System.exit(0);
    }
}
