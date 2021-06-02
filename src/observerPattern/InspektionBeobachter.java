package observerPattern;

import automat.AutomatException;
import automat.AutomatVerwaltung;
import eventHandlers.OutputEventHandlerString;
import events.OutputEventString;

public class InspektionBeobachter implements Beobachter {
    AutomatVerwaltung automat;
    OutputEventHandlerString outputEventHandlerString;

    private int alterZustand = 0;

    public InspektionBeobachter(AutomatVerwaltung automat, OutputEventHandlerString outputEventHandlerString) {
        this.automat = automat;
        this.outputEventHandlerString = outputEventHandlerString;
        this.automat.meldeAn(this);
    }

    @Override
    public void aktualisiere() throws AutomatException {
        int neuerZustand = automat.getAnzahlKuchenImAutomat();
        if(neuerZustand > alterZustand){
            OutputEventString outputEventString = new OutputEventString(this, "kuchen inpected");
            outputEventHandlerString.handle(outputEventString);
        }
        alterZustand = neuerZustand;

    }
}
