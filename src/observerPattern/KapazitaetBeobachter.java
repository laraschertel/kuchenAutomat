package observerPattern;

import automat.AutomatException;
import automat.AutomatVerwaltung;
import eventHandlers.OutputEventHandlerString;
import events.OutputEventString;

public class KapazitaetBeobachter implements Beobachter {
    AutomatVerwaltung automat;
    OutputEventHandlerString outputEventHandlerString;



    public KapazitaetBeobachter(AutomatVerwaltung automat, OutputEventHandlerString outputEventHandlerString) {
        this.automat = automat;
        this.outputEventHandlerString = outputEventHandlerString;
        this.automat.meldeAn(this);

    }
    @Override
    public void aktualisiere() throws AutomatException {
        double neuerZustand = automat.getCakeList().length;
        double kuchenInAutomat = automat.getAnzahlKuchenImAutomat();
        if(kuchenInAutomat / neuerZustand >= 0.9) {
                OutputEventString outputEventString = new OutputEventString(this, "mehr als 90% der Kapazität erreicht");
                outputEventHandlerString.handle(outputEventString);
            }
        }
    }





