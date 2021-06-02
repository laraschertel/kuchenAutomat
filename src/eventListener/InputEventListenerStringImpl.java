package eventListener;

import automat.*;
import events.InputEventString;
import events.OutputEventCollection;
import eventHandlers.OutputEventHandlerCollection;

import java.util.Arrays;

public class InputEventListenerStringImpl {
    private AutomatVerwaltung automat;
    private final OutputEventHandlerCollection outputEventHandlerCollection;
    private final String EINFUEGEMODUS = ":c";
    private final String LOESCHMODUS = ":d";
    private final String ANZEIGEMODUS = ":r";
    private final String HERSTELLER = "hersteller";
    private final String ALLERGENEENTHALTEN = "(i)";
    private final String ALLERGENENICHENTHALTEN = "(e)";
    private final String KUCHEN = "kuchen";

    public InputEventListenerStringImpl(AutomatVerwaltung automat, OutputEventHandlerCollection outputEventHandlerCollection){
        this.automat = automat;
        this.outputEventHandlerCollection = outputEventHandlerCollection;
    }

    public void onInputEvent(InputEventString event){

        switch (event.getModus()){
            case(EINFUEGEMODUS):

                break;
            case(ANZEIGEMODUS):
                switch (event.getString()){
                    case (HERSTELLER):
                        OutputEventCollection outputEventCollectionHersteller = new OutputEventCollection(this, HERSTELLER ,automat.getHerstellerList());
                        this.outputEventHandlerCollection.handle(outputEventCollectionHersteller);
                        break;
                    case(ALLERGENEENTHALTEN):
                        OutputEventCollection outputEventCollectionAllergeneEnthalten = new OutputEventCollection(this, ALLERGENEENTHALTEN, automat.getVorhandeneAllergene());
                        this.outputEventHandlerCollection.handle(outputEventCollectionAllergeneEnthalten);
                        break;
                    case(ALLERGENENICHENTHALTEN):
                        OutputEventCollection outputEventCollectionAllergeneNichtEnthalten = new OutputEventCollection(this, ALLERGENENICHENTHALTEN, automat.getNichtVorhandeneAllergene());
                        this.outputEventHandlerCollection.handle(outputEventCollectionAllergeneNichtEnthalten);
                        break;
                    case(KUCHEN):
                        OutputEventCollection outputEventCollectionKuchen = new OutputEventCollection(this, KUCHEN, Arrays.asList(automat.getCakeList()));
                        this.outputEventHandlerCollection.handle(outputEventCollectionKuchen );
                        break;
                }
        }

    }
}
