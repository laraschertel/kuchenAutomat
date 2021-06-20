package observerPattern;

import automat.AutomatException;
import automat.AutomatPlaceHolder;
import automat.AutomatVerwaltung;
import eventHandlers.OutputEventHandlerString;
import events.OutputEventString;

public class KapazitaetBeobachter implements Beobachter {
    private AutomatPlaceHolder automatPlaceHolder;




    public KapazitaetBeobachter(AutomatPlaceHolder automatPlaceHolder) {
        this.automatPlaceHolder = automatPlaceHolder;
        this.automatPlaceHolder.getAutomat().meldeAn(this);

    }
    @Override
    public void aktualisiere() throws AutomatException {
        double neuerZustand = automatPlaceHolder.getAutomat().getCakeList().length;
        double kuchenInAutomat = automatPlaceHolder.getAutomat().getAnzahlKuchenImAutomat();
        if(kuchenInAutomat / neuerZustand >= 0.9) {
            System.out.println("mehr als 90% der Kapazität erreicht");

            }
        }
    }





