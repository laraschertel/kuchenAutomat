package observerPattern;

import automat.AutomatException;
import automat.AutomatVerwaltung;
import eventHandlers.OutputEventHandlerString;
import events.OutputEventString;

public class KuchenBeobachter implements Beobachter {
    AutomatVerwaltung automat;
    OutputEventHandlerString outputEventHandlerString;

    private int alterZustand = 0;

    public KuchenBeobachter(AutomatVerwaltung automat, OutputEventHandlerString outputEventHandlerString) {
        this.automat = automat;
        this.outputEventHandlerString = outputEventHandlerString;
        this.automat.meldeAn(this);
    }

    @Override
    public void aktualisiere() throws AutomatException {
        int neuerZustand = automat.getAnzahlKuchenImAutomat();
        if(neuerZustand > alterZustand){
            OutputEventString outputEventString = new OutputEventString(this, "kuchen hingefügt");
            outputEventHandlerString.handle(outputEventString);
        } else if(neuerZustand < alterZustand){
            OutputEventString outputEventString = new OutputEventString(this, "kuchen gelöscht");
            outputEventHandlerString.handle(outputEventString);
        }
        alterZustand = neuerZustand;

    }
}
