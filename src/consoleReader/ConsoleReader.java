package consoleReader;

import automat.*;
import eventHandlers.*;
import events.*;


import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

public class ConsoleReader {
    private final String EINFUEGEMODUS = ":c";
    private final String LOESCHMODUS = ":d";
    private final String ANZEIGEMODUS = ":r";
    private final String AENDERUNGSMODUS = ":u";
    private final String PERSISTENZMODUS = ":p";
    private final String NULL = null;
    private final String HERSTELLER = "hersteller";
    private final String ALLERGEN = "allergene";
    private final String ENTHALTEN = "(i)";
    private final String NICHENTHALTEN = "(e)";
    private final String KUCHEN = "kuchen";
    private final String OBSTKUCHEN = "Obstkuchen";
    private final String KREMKUCHEN = "Kremkuchen";
    private final String OBSTTORTE = "Obsttorte";
    private final String SAVEJOS = "saveJOS";
    private final String LOADJOS = "loadJOS";


    private InputEventHandlerInteger integerHandler;
    private InputEventHandlerString stringHandler;
    private InputEventHandlerCake cakeHandler;
    private InputEventHandlerHersteller herstellerHandler;
    private OutputEventHandlerCollection outputCollectionHandler;
    private OutputEventHandlerString outputEventHandlerString;


    public void setHandlers(InputEventHandlerInteger integerHandler, InputEventHandlerString stringHandler, InputEventHandlerCake cakeHandler, InputEventHandlerHersteller herstellerHandler, OutputEventHandlerCollection outputCollectionHandler, OutputEventHandlerString outputEventHandlerString) {
        this.integerHandler = integerHandler;
        this.stringHandler = stringHandler;
        this.cakeHandler = cakeHandler;
        this.herstellerHandler = herstellerHandler;
        this.outputCollectionHandler = outputCollectionHandler;
        this.outputEventHandlerString = outputEventHandlerString;
    }


    private static String readStringFromStdIn() {
        System.out.print(" ");
        Scanner myInput = new Scanner(System.in);
        return myInput.nextLine();
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void start() {
        System.out.println("Wilkommen - Kuchen Automat - Enter a Modus");
        String modus = ":c";
        try (Scanner s = new Scanner(System.in)) {
            do {
                String text = null;
                String string = s.nextLine();
                if(string.contains(":")) {
                    modus = string;
                }else{
                    text = string;
                }
                switch (modus) {
                    case EINFUEGEMODUS:
                        try {
                            if (text != null) {
                                einfugen(text, modus);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;

                    case LOESCHMODUS:
                        try {
                            if (text != null) {
                                loeschen(text, modus);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;

                    case ANZEIGEMODUS:
                        try {
                            if (text != null) {
                                anzeigen(text, modus);
                            }

                        } catch (Exception e) {
                            this.outputEventHandlerString.handle(new OutputEventString(this, "ERROR"));
                        }
                        break;

                    case PERSISTENZMODUS:
                        try {
                            if (text != null) {
                                persistenz(text, modus);
                            }
                        } catch (Exception e) {
                            this.outputEventHandlerString.handle(new OutputEventString(this, "ERROR"));
                        }
                        break;

                    case AENDERUNGSMODUS:
                        try {
                            if (text != null) {
                                aendern(text, modus);
                            }
                        } catch (Exception e) {
                            this.outputEventHandlerString.handle(new OutputEventString(this, "ERROR"));
                        }
                        break;
                }
            } while (true);
        }
    }

    public Kuchentyp getKuchentypFromString(String kuchentypString){
        EnumSet<Kuchentyp> kuchentypEnumSet = EnumSet.allOf(Kuchentyp.class);
        Object[] kuchentypArrayList = kuchentypEnumSet.toArray();
        for(int i = 0; i < kuchentypArrayList.length; i++){
            if(kuchentypString.equalsIgnoreCase(kuchentypArrayList[i].toString())){
                return (Kuchentyp) kuchentypArrayList[i];
            }
        }
        return null;
    }

    public EnumSet<Allergen> getaAllergene(String text){
        EnumSet<Allergen> allergenLinkedList = EnumSet.noneOf(Allergen.class);
        String[] allergene = (text.split(","));
        try {
            for (int i = 0; i < allergene.length; i++) {
                allergenLinkedList.add(Allergen.valueOf(allergene[i]));

            }
            return allergenLinkedList;
        } catch (Exception e){
           this.outputEventHandlerString.handle(new OutputEventString(this, "ERROR: unkown Allergene"));
        }
        return null;
    }

    public KuchenBoden getKuchenBoden(String text) {
        String[] addParts = text.split("\\s+");
        Kuchentyp kuchentyp = getKuchentypFromString(addParts[0]);
        return new KuchenBoden(kuchentyp, new HerstellerImpl(addParts[1]), BigDecimal.valueOf(0), 0, Duration.ofDays(1000), new HashSet<>());
    }

    public KuchenBelag getKuchenBelag(KuchenKomponent kuchenKomponent, String text) {
        String[] addParts = text.split("\\s+");
        BigDecimal preis = new BigDecimal(addParts[0].replace(',', '.'));
        EnumSet<Allergen> allergenEnumSet = getaAllergene(addParts[3]);
        return new KuchenBelag(kuchenKomponent, preis, Integer.parseInt(addParts[1]), Duration.ofDays(Long.parseLong(addParts[2])), allergenEnumSet, addParts[4]);
    }

    public void einfugen(String text, String modus) throws AutomatException {
        String[] addParts = text.split("\\s+");
        if ((addParts.length - 2) % 5 == 0 ) {
            KuchenBoden kuchenBoden = getKuchenBoden(text);
            String belagKuchen = addParts[2] + " " + addParts[3] + " " + addParts[4] + " " + addParts[5] + " " + addParts[6];
            KuchenBelag kuchenBelagTemp = getKuchenBelag(kuchenBoden, belagKuchen);
            KuchenBelag kuchenBelag = kuchenBelagTemp;

            for(int i = 7; i<addParts.length; i+=5){
                String belag = addParts[i] + " " + addParts[i+1] + " " + addParts[i+2] + " " + addParts[i+3] + " " + addParts[i+4];
                kuchenBelag = getKuchenBelag(kuchenBelagTemp, belag);
                kuchenBelagTemp = kuchenBelag;
            }
            InputEventCake kuchenBelagEvent = new InputEventCake(this, modus, kuchenBelag);
            if(null != this.cakeHandler) cakeHandler.handle(kuchenBelagEvent);
        }  else if (addParts.length == 1) {
            Hersteller hersteller = new HerstellerImpl(addParts[0]);
            InputEventHersteller herstellerEvent = new InputEventHersteller(this, modus, hersteller);
            if (null != this.herstellerHandler) herstellerHandler.handle(herstellerEvent);
        } else {
            System.err.println("Input is not valid");
        }
    }

    public void loeschen(String text, String modus) throws AutomatException, IOException, InterruptedException {
        String[] removeParts = text.split("\\s+");
        if (removeParts.length == 1) {
            if (isInteger(removeParts[0])) {
                InputEventInteger removeKuchenEvent = new InputEventInteger(this, modus, Integer.parseInt(removeParts[0]));
                if (null != this.integerHandler) integerHandler.handle(removeKuchenEvent);
            } else {
                InputEventString removeHerstellerEvent = new InputEventString(this, modus, removeParts[0]);
                if (null != this.stringHandler) stringHandler.handle(removeHerstellerEvent);
            }
        } else {
            this.outputEventHandlerString.handle(new OutputEventString(this, "ERROR: input is not valid"));
        }
    }

    public void anzeigen(String text, String modus) throws IOException, AutomatException, InterruptedException {
        String[] anzeigeParts = text.split("\\s+");
        switch (anzeigeParts[0]) {
            case (HERSTELLER):
                InputEventString printHerstelelrListEvent = new InputEventString(this, modus, anzeigeParts[0]);
                if (null != this.stringHandler) stringHandler.handle(printHerstelelrListEvent);
                break;
            case (ALLERGEN):
                switch (anzeigeParts[1]) {
                    case (ENTHALTEN):
                        InputEventString printEnthaltenAllergeneListEvent = new InputEventString(this, modus, anzeigeParts[1]);
                        if (null != this.stringHandler)
                            stringHandler.handle(printEnthaltenAllergeneListEvent);
                        break;
                    case (NICHENTHALTEN):
                        InputEventString printNichtEnthaltenAllergeneListEvent = new InputEventString(this, modus, anzeigeParts[1]);
                        if (null != this.stringHandler)
                            stringHandler.handle(printNichtEnthaltenAllergeneListEvent);
                        break;
                }
            case (KUCHEN):
                if(anzeigeParts.length==1) {
                    InputEventString printKuchenListEvent = new InputEventString(this, modus, anzeigeParts[0]);
                    if (null != this.stringHandler) stringHandler.handle(printKuchenListEvent);
                }
                if(anzeigeParts.length==2) {
                    Kuchentyp kuchentyp = getKuchentypFromString(anzeigeParts[1]);
                    InputEventString printKuchentypListEvent = new InputEventString(this, modus, "Kuchentyp:"+kuchentyp.toString());
                    if (null != this.stringHandler) stringHandler.handle(printKuchentypListEvent);
                    break;

                }
        }
    }

    public void persistenz(String text, String modus) throws IOException, AutomatException, InterruptedException {
            switch (text) {
                case (SAVEJOS):
                    InputEventString saveJOSEvent = new InputEventString(this, modus, text);
                    if (null != this.stringHandler) stringHandler.handle(saveJOSEvent);
                    break;
                case (LOADJOS):
                    InputEventString loadJOSEvent = new InputEventString(this, modus, text);
                    if (null != this.stringHandler) stringHandler.handle(loadJOSEvent);
                    break;
                default:
                    this.outputEventHandlerString.handle(new OutputEventString(this, "ERROR: invalid input"));

                    break;

        }

    }

    public void aendern(String text, String modus) throws AutomatException {

        if(text.length()==1) {
            InputEventInteger inspectKuchenEvent = new InputEventInteger(this, modus, Integer.parseInt(text));
            if (null != this.integerHandler) integerHandler.handle(inspectKuchenEvent);
        }else {
            this.outputEventHandlerString.handle(new OutputEventString(this, "ERROR: invalid input"));
        }
    }
}


