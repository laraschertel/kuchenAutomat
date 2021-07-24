package control;

import java.util.EventObject;

public abstract class OutputEvent extends EventObject {
     String text;

    public OutputEvent(Object source, String text) {
        super(source);
        this.text =text;
    }


    public String getString(){
        return this.text;
    }
}




