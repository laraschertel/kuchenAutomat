package eventListener;


import consolePrinter.ConsolePrinter;
import events.OutputEventString;

import java.io.Serializable;

public class OutputEventListenerStringImpl {
    ConsolePrinter consolePrinter;

    public OutputEventListenerStringImpl(ConsolePrinter consolePrinter){
        this.consolePrinter = consolePrinter;
    }

    public void onOutputEvent(OutputEventString event) {
        consolePrinter.printString(event);
    }
}
