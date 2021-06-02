import automat.AutomatException;
import automat.AutomatVerwaltung;
import consolePrinter.ConsolePrinter;
import consoleReader.ConsoleReader;
import eventListener.InputEventListenerCakeImpl;
import eventListener.InputEventListenerHerstellerImpl;
import eventListener.InputEventListenerIntegerImpl;
import eventListener.InputEventListenerStringImpl;
import eventHandlers.InputEventHandlerCake;
import eventHandlers.InputEventHandlerHersteller;
import eventHandlers.InputEventHandlerInteger;
import eventHandlers.InputEventHandlerString;
import observerPattern.KuchenBeobachter;
import eventHandlers.OutputEventHandlerCollection;
import eventHandlers.OutputEventHandlerString;
import eventListener.OutputEventListenerCollectionImpl;
import eventListener.OutputEventListenerStringImpl;
import observerPattern.AllergeneBeobachter;
import observerPattern.KapazitaetBeobachter;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws AutomatException {
        ConsoleReader r=new ConsoleReader();
        ConsolePrinter cp = new ConsolePrinter();
        int capacity = readAutomatCapacity("Bitte the gewünschte grosse des Automaten eingeben");

        AutomatVerwaltung automat = new AutomatVerwaltung(capacity);

        InputEventHandlerCake cakeHandler = new InputEventHandlerCake();
        InputEventHandlerHersteller herstellerHandler = new InputEventHandlerHersteller();
        InputEventHandlerString stringHandler = new InputEventHandlerString();
        InputEventHandlerInteger integerHandler = new InputEventHandlerInteger();
        OutputEventHandlerCollection outputCollectionHandler = new OutputEventHandlerCollection();
        OutputEventHandlerString outputEventHandlerString = new OutputEventHandlerString();

        InputEventListenerCakeImpl lCake = new InputEventListenerCakeImpl(automat);
        InputEventListenerHerstellerImpl lHersteller = new InputEventListenerHerstellerImpl(automat);
        InputEventListenerStringImpl lString = new InputEventListenerStringImpl(automat, outputCollectionHandler);
        InputEventListenerIntegerImpl lInteger = new InputEventListenerIntegerImpl(automat);
        OutputEventListenerCollectionImpl lOutputCollection = new OutputEventListenerCollectionImpl(cp);
        OutputEventListenerStringImpl lOutputString = new OutputEventListenerStringImpl(cp);

        cakeHandler.add(lCake);
        herstellerHandler.add(lHersteller);
        stringHandler.add(lString);
        integerHandler.add(lInteger);
        outputCollectionHandler.add(lOutputCollection);
        outputEventHandlerString.add(lOutputString);

        KapazitaetBeobachter kapazitaetBeobachter = new KapazitaetBeobachter(automat, outputEventHandlerString);
        AllergeneBeobachter allergeneBeobachter = new AllergeneBeobachter(automat, outputEventHandlerString);
        KuchenBeobachter kuchenBeobachter = new KuchenBeobachter(automat, outputEventHandlerString);

        r.setHandlers(integerHandler, stringHandler, cakeHandler, herstellerHandler, outputCollectionHandler);
        r.start();

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