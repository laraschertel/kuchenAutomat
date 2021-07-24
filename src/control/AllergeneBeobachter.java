package control;

import automat.Allergen;
import automat.AutomatPlaceHolder;
import automat.Beobachter;
import control.OutputEventHandlerString;
import control.OutputEventString;

import java.util.EnumSet;
import java.util.Set;

public class AllergeneBeobachter implements Beobachter {
    private AutomatPlaceHolder automatPlaceHolder;
    OutputEventHandlerString outputEventHandlerString;

    private  Set<Allergen> alterZustand = EnumSet.noneOf(Allergen.class);

    public AllergeneBeobachter(AutomatPlaceHolder automatPlaceHolder, OutputEventHandlerString outputEventHandlerString) {
        this.automatPlaceHolder = automatPlaceHolder;
        this.outputEventHandlerString = outputEventHandlerString;
        this.automatPlaceHolder.getAutomat().meldeAn(this);


    }
    @Override
    public void aktualisiere()  {
        Set<Allergen> newState = automatPlaceHolder.getAutomat().getVorhandeneAllergene();
        if(!alterZustand.containsAll(newState) || !newState.containsAll(alterZustand)) {
            OutputEventString outputEventString = new OutputEventString(this, "Allergene Änderung");
            outputEventHandlerString.handle(outputEventString);
            this.alterZustand=newState;
            }
        }
    }


