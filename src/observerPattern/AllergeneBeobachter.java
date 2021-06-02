package observerPattern;

import automat.Allergen;
import automat.AutomatException;
import automat.AutomatVerwaltung;
import eventHandlers.OutputEventHandlerString;
import events.OutputEventString;

import java.util.EnumSet;
import java.util.Set;

public class AllergeneBeobachter implements Beobachter {
    AutomatVerwaltung automat;
    OutputEventHandlerString outputEventHandlerString;

    private  Set<Allergen> alterZustand = EnumSet.noneOf(Allergen.class);

    public AllergeneBeobachter(AutomatVerwaltung automat, OutputEventHandlerString outputEventHandlerString) {
        this.automat = automat;
        this.outputEventHandlerString = outputEventHandlerString;
        this.automat.meldeAn(this);


    }
    @Override
    public void aktualisiere() throws AutomatException {
        Set<Allergen> newState = automat.getVorhandeneAllergene();
        if(!alterZustand.containsAll(newState) || !newState.containsAll(alterZustand)) {
            OutputEventString outputEventString = new OutputEventString(this, "Allergene Änderung");
            outputEventHandlerString.handle(outputEventString);
            this.alterZustand=newState;
            }
        }
    }


