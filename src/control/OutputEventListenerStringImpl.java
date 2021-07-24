package control;

import consolePrinter.ConsolePrinter;

public class OutputEventListenerStringImpl {
    private final ConsolePrinter consolePrinter;

    public OutputEventListenerStringImpl(ConsolePrinter consolePrinter){
        this.consolePrinter = consolePrinter;
    }

    public void onOutputEvent(OutputEventString event) {
        consolePrinter.printString(event);
    }
}
