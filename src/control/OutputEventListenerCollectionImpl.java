package control;

import consolePrinter.ConsolePrinter;

public class OutputEventListenerCollectionImpl {
    private final ConsolePrinter consolePrinter;
    private final String HERSTELLER = "hersteller";
    private final String ALLERGENEENTHALTEN = "(i)";
    private final String ALLERGENENICHENTHALTEN = "(e)";
    private final String KUCHEN = "kuchen";


    public OutputEventListenerCollectionImpl(ConsolePrinter consolePrinter){
        this.consolePrinter = consolePrinter;
    }


    public void onOutputEvent(OutputEventCollection event) {
       if(event.getString().equalsIgnoreCase(ALLERGENEENTHALTEN) || event.getString().equalsIgnoreCase(ALLERGENENICHENTHALTEN)){
            consolePrinter.printAllergeneList(event);
        }
    }
}
