package control;

import automat.*;

import java.io.*;

public class InputEventListenerStringImpl {
    private AutomatPlaceHolder automatPlaceHolder;
    private final OutputEventHandlerCollection outputEventHandlerCollection;
    private final OutputEventHandlerString outputEventHandlerString;
    private final OutputEventHandlerHerstellerMap outputEventHandlerHerstellerMap;
    private final OutputEventHandlerCakeList outputEventHandlerCakeList;
    private final String EINFUEGEMODUS = ":c";
    private final String LOESCHMODUS = ":d";
    private final String ANZEIGEMODUS = ":r";
    private final String PERSISTENZMODUS = ":p";
    private final String HERSTELLER = "hersteller";
    private final String ALLERGENEENTHALTEN = "(i)";
    private final String ALLERGENENICHENTHALTEN = "(e)";
    private final String KUCHEN = "kuchen";
    private final String SAVEJOS = "saveJOS";
    private final String LOADJOS = "loadJOS";


    public InputEventListenerStringImpl(AutomatPlaceHolder automatPlaceHolder, OutputEventHandlerCollection outputEventHandlerCollection, OutputEventHandlerString outputEventHandlerString, OutputEventHandlerHerstellerMap outputEventHandlerHerstellerMap, OutputEventHandlerCakeList outputEventHandlerCakeList){
        this.automatPlaceHolder = automatPlaceHolder;
        this.outputEventHandlerCollection = outputEventHandlerCollection;
        this.outputEventHandlerString = outputEventHandlerString;
        this.outputEventHandlerHerstellerMap = outputEventHandlerHerstellerMap;
        this. outputEventHandlerCakeList =  outputEventHandlerCakeList;
    }

    public void onInputEvent(InputEventString event) throws IOException, AutomatException, InterruptedException {

        switch (event.getModus()){
            case(LOESCHMODUS):
                automatPlaceHolder.getAutomat().removeHersteller(event.getString());
                break;
            case(ANZEIGEMODUS):
                if(event.getString().contains("Kuchentyp")){
                    String[] kuchentyp = event.getString().split(":");
                    OutputEventCakeList outputEventCakeList = new  OutputEventCakeList(this, KUCHEN, (automatPlaceHolder.getAutomat().getAlleKuchenEinesTyps(kuchentyp[1])));
                    this. outputEventHandlerCakeList.handle(outputEventCakeList );
                }
                switch (event.getString()){
                    case (HERSTELLER):
                        automatPlaceHolder.getAutomat().getHerstellerListMitKuchenAnzahl();
                        OutputEventHerstellerMap outputEventHerstellerMap = new  OutputEventHerstellerMap(this, HERSTELLER, automatPlaceHolder.getAutomat().getHerstellerListMitKuchenAnzahl());
                        this.outputEventHandlerHerstellerMap.handle(outputEventHerstellerMap);
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
                        OutputEventCakeList outputEventCakeList = new  OutputEventCakeList(this, KUCHEN, (automatPlaceHolder.getAutomat().getCakeList()));
                        this. outputEventHandlerCakeList.handle(outputEventCakeList );
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
