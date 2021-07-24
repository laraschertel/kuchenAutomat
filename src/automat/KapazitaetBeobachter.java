package automat;

import eventHandlers.OutputEventHandlerString;
import events.OutputEventString;

public class KapazitaetBeobachter implements Beobachter {
    private AutomatPlaceHolder automatPlaceHolder;
    OutputEventHandlerString outputEventHandlerString;



    public KapazitaetBeobachter(AutomatPlaceHolder automatPlaceHolder, OutputEventHandlerString outputEventHandlerString) {
        this.automatPlaceHolder = automatPlaceHolder;
        this.outputEventHandlerString = outputEventHandlerString;
        this.automatPlaceHolder.getAutomat().meldeAn(this);

    }
    @Override
    public void aktualisiere() throws AutomatException {
        double neuerZustand = automatPlaceHolder.getAutomat().getCakeList().length;
        double kuchenInAutomat = automatPlaceHolder.getAutomat().getAnzahlKuchenImAutomat();
        if(kuchenInAutomat / neuerZustand >= 0.9) {
                OutputEventString outputEventString = new OutputEventString(this, "mehr als 90% der Kapazität erreicht");
                outputEventHandlerString.handle(outputEventString);
            }
        }
    }





