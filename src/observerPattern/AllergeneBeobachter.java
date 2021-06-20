package observerPattern;

import automat.Allergen;
import automat.AutomatException;
import automat.AutomatPlaceHolder;
import automat.AutomatVerwaltung;
import eventHandlers.OutputEventHandlerString;
import events.OutputEventString;

import java.util.EnumSet;
import java.util.Set;

public class AllergeneBeobachter implements Beobachter {
    private AutomatPlaceHolder automatPlaceHolder;


    private  Set<Allergen> alterZustand = EnumSet.noneOf(Allergen.class);

    public AllergeneBeobachter(AutomatPlaceHolder automatPlaceHolder) {
        this.automatPlaceHolder = automatPlaceHolder;
        this. automatPlaceHolder.getAutomat().meldeAn(this);



    }
    @Override
    public void aktualisiere() throws AutomatException {
        Set<Allergen> newState = automatPlaceHolder.getAutomat().getVorhandeneAllergene();
        if(!alterZustand.containsAll(newState) || !newState.containsAll(alterZustand)) {
            System.out.println("Allergene Änderung");
            this.alterZustand=newState;
            }
        }
    }


