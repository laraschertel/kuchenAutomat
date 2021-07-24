package control;

import consolePrinter.ConsolePrinter;

public class OutputEventListenerHerstellerMap {

    private final ConsolePrinter consolePrinter;
    private final String HERSTELLER = "hersteller";

    public OutputEventListenerHerstellerMap(ConsolePrinter consolePrinter){
        this.consolePrinter = consolePrinter;
    }

    public void onOutputEvent(OutputEventHerstellerMap event) {

            if(event.getString().equalsIgnoreCase(HERSTELLER)) {
                consolePrinter.printHerstellerHashMap(event);
            }

    }
}
