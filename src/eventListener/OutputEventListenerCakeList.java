package eventListener;

import consolePrinter.ConsolePrinter;
import events.OutputEventCakeList;
import events.OutputEventCollection;

public class OutputEventListenerCakeList {

    ConsolePrinter consolePrinter;
    private final String KUCHEN = "kuchen";


    public OutputEventListenerCakeList(ConsolePrinter consolePrinter){
        this.consolePrinter = consolePrinter;
    }

    public void onOutputEvent(OutputEventCakeList event) {
        if(event.getString().equalsIgnoreCase(KUCHEN)){
            consolePrinter.printKuchenList(event);
        }
    }
}
