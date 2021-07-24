package control;

import java.util.EventObject;

public abstract class InputEvent extends EventObject {
    private String modus;

    public InputEvent(Object source, String modus) {
        super(source);
        this.modus =modus;
    }


    public String getModus(){
        return this.modus;
    }
}


