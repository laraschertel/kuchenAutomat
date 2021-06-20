package eventListener;

import automat.*;
import eventHandlers.OutputEventHandlerString;
import events.InputEventString;
import events.OutputEventCollection;
import eventHandlers.OutputEventHandlerCollection;
import events.OutputEventString;

import java.io.*;
import java.util.Arrays;

public class InputEventListenerStringImpl {
    private AutomatPlaceHolder automatPlaceHolder;
    private final OutputEventHandlerCollection outputEventHandlerCollection;
    private final OutputEventHandlerString outputEventHandlerString;
    private final String EINFUEGEMODUS = ":c";
    private final String LOESCHMODUS = ":d";
    private final String ANZEIGEMODUS = ":r";
    private final String PERSISTENZMODUS = ":p";
    private final String HERSTELLER = "hersteller";
    private final String ALLERGENEENTHALTEN = "(i)";
    private final String ALLERGENENICHENTHALTEN = "(e)";
    private final String KUCHEN = "kuchen";
    private final String SAVEJOS = "savejos";
    private final String LOADJOS = "loadjos";

    public InputEventListenerStringImpl(AutomatPlaceHolder automatPlaceHolder, OutputEventHandlerCollection outputEventHandlerCollection, OutputEventHandlerString outputEventHandlerString){
        this.automatPlaceHolder = automatPlaceHolder;
        this.outputEventHandlerCollection = outputEventHandlerCollection;
        this.outputEventHandlerString = outputEventHandlerString;
    }

    public void onInputEvent(InputEventString event) throws IOException {

        switch (event.getModus()){
            case(EINFUEGEMODUS):

                break;
            case(ANZEIGEMODUS):
                switch (event.getString()){
                    case (HERSTELLER):
                        OutputEventCollection outputEventCollectionHersteller = new OutputEventCollection(this, HERSTELLER ,automatPlaceHolder.getAutomat().getHerstellerList());
                        this.outputEventHandlerCollection.handle(outputEventCollectionHersteller);
                        break;
                    case(ALLERGENEENTHALTEN):
                        OutputEventCollection outputEventCollectionAllergeneEnthalten = new OutputEventCollection(this, ALLERGENEENTHALTEN, automatPlaceHolder.getAutomat().getVorhandeneAllergene());
                        this.outputEventHandlerCollection.handle(outputEventCollectionAllergeneEnthalten);
                        break;
                    case(ALLERGENENICHENTHALTEN):
                        OutputEventCollection outputEventCollectionAllergeneNichtEnthalten = new OutputEventCollection(this, ALLERGENENICHENTHALTEN, automatPlaceHolder.getAutomat().getNichtVorhandeneAllergene());
                        this.outputEventHandlerCollection.handle(outputEventCollectionAllergeneNichtEnthalten);
                        break;
                    case(KUCHEN):
                        OutputEventCollection outputEventCollectionKuchen = new OutputEventCollection(this, KUCHEN, Arrays.asList(automatPlaceHolder.getAutomat().getCakeList()));
                        this.outputEventHandlerCollection.handle(outputEventCollectionKuchen );
                        break;
                }

            case(PERSISTENZMODUS):
                switch (event.getString()) {
                    case (SAVEJOS):
                        saveJOS();
                        break;
                    case (LOADJOS):
                        loadJOS();
                        break;
                }
        }

    }

    private void saveJOS() throws IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("automat.ser"));
        try{
            objectOutputStream.writeObject(this.automatPlaceHolder.getAutomat());
            this.outputEventHandlerString.handle(new OutputEventString(this, "Automat was saved"));
        }catch (IOException e){
            this.outputEventHandlerString.handle(new OutputEventString(this, "Automat could not be saved"));
        }
    }

    private void loadJOS() throws IOException{
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("automat.ser"));
        AutomatVerwaltung automat = null;
        try{
           automat = (AutomatVerwaltung) objectInputStream.readObject();
            this.outputEventHandlerString.handle(new OutputEventString(this, "Automat was loaded"));
        } catch (FileNotFoundException e) {
            this.outputEventHandlerString.handle(new OutputEventString(this, "File not found"));
        }
        catch (IOException e){
            this.outputEventHandlerString.handle(new OutputEventString(this, "Automat could not be loaded"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        automatPlaceHolder.setAutomat(automat);

    }
}
