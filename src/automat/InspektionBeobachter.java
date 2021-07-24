package automat;

import eventHandlers.OutputEventHandlerString;
import events.OutputEventString;

public class InspektionBeobachter implements Beobachter {
    private AutomatPlaceHolder automatPlaceHolder;
    OutputEventHandlerString outputEventHandlerString;

    private int alterZustand = 0;

    public InspektionBeobachter(AutomatPlaceHolder automatPlaceHolder, OutputEventHandlerString outputEventHandlerString) {
        this. automatPlaceHolder =  automatPlaceHolder;
        this.outputEventHandlerString = outputEventHandlerString;
        this. automatPlaceHolder.getAutomat().meldeAn(this);
    }

    @Override
    public void aktualisiere() throws AutomatException {
        int neuerZustand =  automatPlaceHolder.getAutomat().getAnzahlKuchenImAutomat();
        if(neuerZustand > alterZustand){
            OutputEventString outputEventString = new OutputEventString(this, "kuchen inpected");
            outputEventHandlerString.handle(outputEventString);
        }
        alterZustand = neuerZustand;

    }
}
