package consoleReader;

import automat.*;
import eventHandlers.*;
import events.*;


import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
    private final String SAVEJOS = "savejos";
    private final String LOADJOS = "loadjos";


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

                    case AENDERUNGSMODUS:
                        try {
                            aendern(text, modus);
                        } catch (Exception e) {
                            this.outputEventHandlerString.handle(new OutputEventString(this, "ERROR"));
                        }
                }
            } while (true);
        }
    }

    public void einfugen(String text, String modus) throws AutomatException {
        String[] addParts = text.split("\\s+");
        if (addParts.length == 7 || addParts.length == 8 ) {
            List<Allergen> allergenLinkedList = new LinkedList<>();
            String[] allergene = (addParts[5].split(","));
            for (int i = 0; i < allergene.length; i++) {
                allergenLinkedList.add(Allergen.valueOf(allergene[i]));
            }
            switch (addParts[0]) {
                case OBSTKUCHEN:
                    Cake obstkuchen = new ObstkuchenImpl(new HerstellerImpl(addParts[1]), new BigDecimal(addParts[2]), Integer.parseInt(addParts[3]), Duration.ofDays(Long.parseLong(addParts[4])), allergenLinkedList, new Date(), -1, new Date(), addParts[6]);
                    InputEventCake obstkuchenEvent = new InputEventCake(this, modus, obstkuchen);
                    if (null != this.cakeHandler) cakeHandler.handle(obstkuchenEvent);
                    break;
                case KREMKUCHEN:
                    Cake kremkuchen = new KremkuchenImpl(new HerstellerImpl(addParts[1]), new BigDecimal(addParts[2]), Integer.parseInt(addParts[3]), Duration.ofDays(Long.parseLong(addParts[4])), allergenLinkedList, new Date(), -1, new Date(), addParts[6]);
                    InputEventCake kremkuchenEvent = new InputEventCake(this, modus, kremkuchen);
                    if (null != this.cakeHandler) cakeHandler.handle(kremkuchenEvent);
                    break;
                case OBSTTORTE:
                    Cake obsttorte = new ObsttorteImpl(new HerstellerImpl(addParts[1]), new BigDecimal(addParts[2]), Integer.parseInt(addParts[3]), Duration.ofDays(Long.parseLong(addParts[4])), allergenLinkedList, new Date(), -1, new Date(), addParts[6], addParts[7]);
                    InputEventCake obsttorteEvent = new InputEventCake(this, modus, obsttorte);
                    if (null != this.cakeHandler) cakeHandler.handle(obsttorteEvent);
                    break;
            }
        } else if (addParts.length == 1) {
            Hersteller hersteller = new HerstellerImpl(addParts[0]);
            InputEventHersteller herstellerEvent = new InputEventHersteller(this, modus, hersteller);
            if (null != this.herstellerHandler) herstellerHandler.handle(herstellerEvent);
        } else {
            System.err.println("Input is not valid");
        }
    }

    public void loeschen(String text, String modus) throws AutomatException, IOException {
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

    public void anzeigen(String text, String modus) throws IOException, AutomatException {
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
                if(text.length()==2) {
                    InputEventString printKuchenListEvent = new InputEventString(this, modus, anzeigeParts[0]);
                    if (null != this.stringHandler) stringHandler.handle(printKuchenListEvent);
                }
                //TODO
                switch (anzeigeParts[1]) {
                    case (OBSTKUCHEN):

                        break;
                    case (KREMKUCHEN):

                        break;
                    case (OBSTTORTE):

                        break;
            }
        }
    }

    public void persistenz(String text, String modus) throws IOException, AutomatException {
        if(text.length()==1) {
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


