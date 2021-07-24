package automat;

import eventHandlers.OutputEventHandlerString;
import events.OutputEventString;

public class KuchenBeobachter implements Beobachter {
    private AutomatPlaceHolder automatPlaceHolder;
    OutputEventHandlerString outputEventHandlerString;

    private int alterZustand = 0;

    public KuchenBeobachter(AutomatPlaceHolder automatPlaceHolder, OutputEventHandlerString outputEventHandlerString) {
        this.automatPlaceHolder = automatPlaceHolder;
        this.outputEventHandlerString = outputEventHandlerString;
        this.automatPlaceHolder.getAutomat().meldeAn(this);
    }

    @Override
    public void aktualisiere(){
        try {
            int neuerZustand = automatPlaceHolder.getAutomat().getAnzahlKuchenImAutomat();
            if (neuerZustand > alterZustand) {
                OutputEventString outputEventString = new OutputEventString(this, "kuchen hingefügt");
                outputEventHandlerString.handle(outputEventString);
            } else if (neuerZustand < alterZustand) {
                OutputEventString outputEventString = new OutputEventString(this, "kuchen gelöscht");
                outputEventHandlerString.handle(outputEventString);
            }
            alterZustand = neuerZustand;
        }catch(AutomatException e){
            System.out.println("Fehler bei Aktualisieren");;
        }

    }
}
