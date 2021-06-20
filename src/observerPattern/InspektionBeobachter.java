package observerPattern;

import automat.AutomatException;
import automat.AutomatPlaceHolder;
import automat.AutomatVerwaltung;
import eventHandlers.OutputEventHandlerString;
import events.OutputEventString;

public class InspektionBeobachter implements Beobachter {
    private AutomatPlaceHolder automatPlaceHolder;


    private int alterZustand = 0;

    public InspektionBeobachter(AutomatPlaceHolder automatPlaceHolder) {
        this. automatPlaceHolder =  automatPlaceHolder;
        this. automatPlaceHolder.getAutomat().meldeAn(this);
    }

    @Override
    public void aktualisiere() throws AutomatException {
        int neuerZustand =  automatPlaceHolder.getAutomat().getAnzahlKuchenImAutomat();
        if(neuerZustand > alterZustand){
            System.out.println("kuchen inpected");

        }
        alterZustand = neuerZustand;

    }
}
