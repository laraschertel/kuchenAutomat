import automat.AutomatException;
import automat.AutomatPlaceHolder;
import automat.AutomatVerwaltung;
import consolePrinter.ConsolePrinter;
import control.ConsoleReader;
import control.*;
import control.KapazitaetBeobachter;

import java.util.Scanner;
/*
Alternatives CLI: Allergene können nicht aufgelistet werden und kuchen können nicht gelöscht werden
 */
public class AlternativesCLI {
    public static void main(String[] args) throws AutomatException {
        ConsoleReader r=new ConsoleReader();
        ConsolePrinter cp = new ConsolePrinter();

        int capacity = 0;

        if(args.length > 0){
            try {
                capacity = Integer.parseInt(args[0]);
            }catch (NumberFormatException e){
                System.err.println("Keine Zahl eingegeben, die Standard groeße 20 wird benutzt");
            }
            if (capacity == 0){
                capacity = 20;
            }
        }

        AutomatVerwaltung automat= new AutomatVerwaltung(capacity);
        AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automat);


        InputEventHandlerCake cakeHandler = new InputEventHandlerCake();
        InputEventHandlerHersteller herstellerHandler = new InputEventHandlerHersteller();
        InputEventHandlerString stringHandler = new InputEventHandlerString();
        OutputEventHandlerString outputEventHandlerString = new OutputEventHandlerString();
        OutputEventHandlerHerstellerMap outputEventHandlerHerstellerMap = new OutputEventHandlerHerstellerMap();
        OutputEventHandlerCakeList outputEventHandlerCakeList = new OutputEventHandlerCakeList();

        InputEventListenerCakeImpl lCake = new InputEventListenerCakeImpl(automatPlaceHolder);
        InputEventListenerHerstellerImpl lHersteller = new InputEventListenerHerstellerImpl(automatPlaceHolder);
        InputEventListenerStringImpl lString = new InputEventListenerStringImpl(automatPlaceHolder, null, outputEventHandlerString, outputEventHandlerHerstellerMap,outputEventHandlerCakeList);
        OutputEventListenerCollectionImpl lOutputCollection = new OutputEventListenerCollectionImpl(cp);
        OutputEventListenerStringImpl lOutputString = new OutputEventListenerStringImpl(cp);
        OutputEventListenerHerstellerMap lOutputHerstellerMap = new OutputEventListenerHerstellerMap(cp);
        OutputEventListenerCakeList lOutputCakeList = new OutputEventListenerCakeList(cp);

        cakeHandler.add(lCake);
        herstellerHandler.add(lHersteller);
        stringHandler.add(lString);

        outputEventHandlerString.add(lOutputString);
        outputEventHandlerHerstellerMap.add(lOutputHerstellerMap);
        outputEventHandlerCakeList.add(lOutputCakeList);

        KapazitaetBeobachter kapazitaetBeobachter = new KapazitaetBeobachter(automatPlaceHolder, outputEventHandlerString);

        r.setHandlers(null, stringHandler, cakeHandler, herstellerHandler,  outputEventHandlerString);

        r.readInput();

    }

}
