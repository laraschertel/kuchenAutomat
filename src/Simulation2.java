import automat.AutomatException;
import automat.AutomatPlaceHolder;
import automat.AutomatVerwaltung;
import consolePrinter.ConsolePrinter;
import simulation.KuchenBeobachter;
import control.OutputEventHandlerString;
import control.OutputEventListenerStringImpl;
import simulation.*;

public class Simulation2 {
    public static void main(String[] args) throws AutomatException {
        AutomatVerwaltung automat= new AutomatVerwaltung(20);
        AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automat);
        AutomatVerwaltungSimulation automatVerwaltungSimulation = new AutomatVerwaltungSimulation(automat);

        ConsolePrinter cp = new ConsolePrinter();

        OutputEventListenerStringImpl lOutputString = new OutputEventListenerStringImpl(cp);
        OutputEventHandlerString outputEventHandlerString = new OutputEventHandlerString();
        outputEventHandlerString.add(lOutputString);

        KuchenBeobachter kuchenBeobachter = new KuchenBeobachter(automatPlaceHolder);

        automatVerwaltungSimulation.addHerstellers();

        AddCakeThread addCakeThread = new AddCakeThread(automatVerwaltungSimulation);
        InspectRandomCakeThread inspectRandomCakeThread = new InspectRandomCakeThread(automatVerwaltungSimulation);
        DeleteOldestDateCakeThread deleteOldestDateCakeThread = new DeleteOldestDateCakeThread(automatVerwaltungSimulation);


        addCakeThread.start();
        deleteOldestDateCakeThread.start();
        inspectRandomCakeThread.start();



    }
}
