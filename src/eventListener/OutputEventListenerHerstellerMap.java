package eventListener;

import consolePrinter.ConsolePrinter;
import events.OutputEventHerstellerMap;

public class OutputEventListenerHerstellerMap {

    ConsolePrinter consolePrinter;
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
