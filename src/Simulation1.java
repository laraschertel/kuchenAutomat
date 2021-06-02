import automat.AutomatException;
import automat.AutomatVerwaltung;
import consolePrinter.ConsolePrinter;
import observerPattern.KuchenBeobachter;
import eventHandlers.OutputEventHandlerString;
import eventListener.OutputEventListenerStringImpl;
import simulation.AddCakeThread;
import simulation.AutomatVerwaltungSimulation;
import simulation.DeleteRandomCakeThread;

public class Simulation1 {
    public static void main(String[] args) throws AutomatException {
        AutomatVerwaltung automat = new AutomatVerwaltung(20);
        AutomatVerwaltungSimulation automatVerwaltungSimulation = new AutomatVerwaltungSimulation(automat);
        ConsolePrinter cp = new ConsolePrinter();
        OutputEventListenerStringImpl lOutputString = new OutputEventListenerStringImpl(cp);

        OutputEventHandlerString outputEventHandlerString = new OutputEventHandlerString();
        outputEventHandlerString.add(lOutputString);

        KuchenBeobachter kuchenBeobachter = new KuchenBeobachter(automat, outputEventHandlerString);

        automatVerwaltungSimulation.addHerstellers();

        AddCakeThread addCakeThread = new AddCakeThread(automatVerwaltungSimulation);
        DeleteRandomCakeThread deleteCakeThread = new DeleteRandomCakeThread(automatVerwaltungSimulation);

        addCakeThread.start();
        deleteCakeThread.start();

    }
}
