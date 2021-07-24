import automat.AutomatException;
import automat.AutomatPlaceHolder;
import automat.AutomatVerwaltung;
import consolePrinter.ConsolePrinter;
import consoleReader.ConsoleReader;
import eventHandlers.*;
import eventListener.*;
import automat.KapazitaetBeobachter;

import java.util.Scanner;

public class AlternativesCLI {
    public static void main(String[] args) throws AutomatException {
        ConsoleReader r=new ConsoleReader();
        ConsolePrinter cp = new ConsolePrinter();
        int capacity = readAutomatCapacity("Bitte the gewünschte grosse des Automaten eingeben");

        AutomatVerwaltung automat= new AutomatVerwaltung(capacity);
        AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automat);


        InputEventHandlerCake cakeHandler = new InputEventHandlerCake();
        InputEventHandlerHersteller herstellerHandler = new InputEventHandlerHersteller();
        InputEventHandlerString stringHandler = new InputEventHandlerString();
        //InputEventHandlerInteger integerHandler = new InputEventHandlerInteger();
       // OutputEventHandlerCollection outputCollectionHandler = new OutputEventHandlerCollection();
        OutputEventHandlerString outputEventHandlerString = new OutputEventHandlerString();
        OutputEventHandlerHerstellerMap outputEventHandlerHerstellerMap = new OutputEventHandlerHerstellerMap();
        OutputEventHandlerCakeList outputEventHandlerCakeList = new OutputEventHandlerCakeList();

        InputEventListenerCakeImpl lCake = new InputEventListenerCakeImpl(automatPlaceHolder);
        InputEventListenerHerstellerImpl lHersteller = new InputEventListenerHerstellerImpl(automatPlaceHolder);
        InputEventListenerStringImpl lString = new InputEventListenerStringImpl(automatPlaceHolder, null, outputEventHandlerString, outputEventHandlerHerstellerMap,outputEventHandlerCakeList);
       // InputEventListenerIntegerImpl lInteger = new InputEventListenerIntegerImpl(automatPlaceHolder);
        OutputEventListenerCollectionImpl lOutputCollection = new OutputEventListenerCollectionImpl(cp);
        OutputEventListenerStringImpl lOutputString = new OutputEventListenerStringImpl(cp);
        OutputEventListenerHerstellerMap lOutputHerstellerMap = new OutputEventListenerHerstellerMap(cp);
        OutputEventListenerCakeList lOutputCakeList = new OutputEventListenerCakeList(cp);

        cakeHandler.add(lCake);
        herstellerHandler.add(lHersteller);
        stringHandler.add(lString);
        //integerHandler.add(lInteger);
        //outputCollectionHandler.add(lOutputCollection);
        outputEventHandlerString.add(lOutputString);
        outputEventHandlerHerstellerMap.add(lOutputHerstellerMap);
        outputEventHandlerCakeList.add(lOutputCakeList);

        KapazitaetBeobachter kapazitaetBeobachter = new KapazitaetBeobachter(automatPlaceHolder, outputEventHandlerString);
        //AllergeneBeobachter allergeneBeobachter = new AllergeneBeobachter(automatPlaceHolder, outputEventHandlerString);
        //KuchenBeobachter kuchenBeobachter = new KuchenBeobachter(automatPlaceHolder, outputEventHandlerString);

        r.setHandlers(null, stringHandler, cakeHandler, herstellerHandler,  outputEventHandlerString);

        r.readInput();

    }

    public static int readAutomatCapacity(String text) {
        System.out.print(text + " ");
        int x = 0;
        boolean a = true;
        while (a) {
            Scanner myInput = new Scanner(System.in);
            if (myInput.hasNextInt()) {
                x = myInput.nextInt();
                a = false;
                return x;
            }
            else {
                System.out.println("Invalid input, try again");
                System.out.print(text);
                myInput.next();
            }
        }
        return x;
    }

}
