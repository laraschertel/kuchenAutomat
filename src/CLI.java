import automat.AutomatException;
import automat.AutomatPlaceHolder;
import automat.AutomatVerwaltung;
import consolePrinter.ConsolePrinter;
import control.ConsoleReader;
import control.*;
import control.AllergeneBeobachter;
import control.KapazitaetBeobachter;

import java.util.Scanner;

public class CLI {
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
        InputEventHandlerInteger integerHandler = new InputEventHandlerInteger();
        OutputEventHandlerCollection outputCollectionHandler = new OutputEventHandlerCollection();
        OutputEventHandlerString outputEventHandlerString = new OutputEventHandlerString();
        OutputEventHandlerHerstellerMap outputEventHandlerHerstellerMap = new OutputEventHandlerHerstellerMap();
        OutputEventHandlerCakeList outputEventHandlerCakeList = new OutputEventHandlerCakeList();

        InputEventListenerCakeImpl lCake = new InputEventListenerCakeImpl(automatPlaceHolder);
        InputEventListenerHerstellerImpl lHersteller = new InputEventListenerHerstellerImpl(automatPlaceHolder);
        InputEventListenerStringImpl lString = new InputEventListenerStringImpl(automatPlaceHolder, outputCollectionHandler, outputEventHandlerString, outputEventHandlerHerstellerMap,outputEventHandlerCakeList);
        InputEventListenerIntegerImpl lInteger = new InputEventListenerIntegerImpl(automatPlaceHolder);
        OutputEventListenerCollectionImpl lOutputCollection = new OutputEventListenerCollectionImpl(cp);
        OutputEventListenerStringImpl lOutputString = new OutputEventListenerStringImpl(cp);
        OutputEventListenerHerstellerMap lOutputHerstellerMap = new OutputEventListenerHerstellerMap(cp);
        OutputEventListenerCakeList lOutputCakeList = new OutputEventListenerCakeList(cp);

        cakeHandler.add(lCake);
        herstellerHandler.add(lHersteller);
        stringHandler.add(lString);
        integerHandler.add(lInteger);
        outputCollectionHandler.add(lOutputCollection);
        outputEventHandlerString.add(lOutputString);
        outputEventHandlerHerstellerMap.add(lOutputHerstellerMap);
        outputEventHandlerCakeList.add(lOutputCakeList);

        KapazitaetBeobachter kapazitaetBeobachter = new KapazitaetBeobachter(automatPlaceHolder, outputEventHandlerString);
        AllergeneBeobachter allergeneBeobachter = new AllergeneBeobachter(automatPlaceHolder, outputEventHandlerString);


        r.setHandlers(integerHandler, stringHandler, cakeHandler, herstellerHandler, outputEventHandlerString);

        r.readInput();

    }

}
