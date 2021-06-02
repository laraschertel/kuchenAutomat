package eventListener;


import consolePrinter.ConsolePrinter;
import events.OutputEventString;

public class OutputEventListenerStringImpl{
    ConsolePrinter consolePrinter;

    public OutputEventListenerStringImpl(ConsolePrinter consolePrinter){
        this.consolePrinter = consolePrinter;
    }

    public void onOutputEvent(OutputEventString event) {
        consolePrinter.printString(event);
    }
}
