import automat.AutomatException;
import automat.AutomatPlaceHolder;
import automat.AutomatVerwaltung;
import consolePrinter.ConsolePrinter;
import automat.KuchenBeobachter;
import eventHandlers.OutputEventHandlerString;
import eventListener.OutputEventListenerStringImpl;
import simulation.*;

public class Simulation3 {
    public static void main(String[] args) throws AutomatException {
        AutomatVerwaltung automat= new AutomatVerwaltung(20);
        AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automat);
        AutomatVerwaltungSimulation automatVerwaltungSimulation = new AutomatVerwaltungSimulation(automat);

        ConsolePrinter cp = new ConsolePrinter();

        OutputEventListenerStringImpl lOutputString = new OutputEventListenerStringImpl(cp);
        OutputEventHandlerString outputEventHandlerString = new OutputEventHandlerString();
        outputEventHandlerString.add(lOutputString);

        KuchenBeobachter kuchenBeobachter = new KuchenBeobachter(automatPlaceHolder, outputEventHandlerString);

        automatVerwaltungSimulation.addHerstellers();

        AddCakeThread addCakeThread1 = new AddCakeThread(automatVerwaltungSimulation);
        AddCakeThread addCakeThread2 = new AddCakeThread(automatVerwaltungSimulation);
        DeleteMoreCakesThread deleteMoreCakesThread1 = new DeleteMoreCakesThread(automatVerwaltungSimulation);
        DeleteMoreCakesThread deleteMoreCakesThread2 = new DeleteMoreCakesThread(automatVerwaltungSimulation);

        addCakeThread1.start();
        addCakeThread2.start();
        deleteMoreCakesThread1.start();
        deleteMoreCakesThread2.start();




    }
}
