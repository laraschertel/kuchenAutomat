package consoleReader;

import automat.*;
import eventHandlers.*;
import events.*;


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
    private final String ALLERGEN = "ALLERGENE";
    private final String ENTHALTEN = "(i)";
    private final String NICHENTHALTEN = "(e)";
    private final String KUCHEN = "kuchen";
    private final String OBSTKUCHEN = "Obstkuchen";
    private final String KREMKUCHEN = "Kremkuchen";
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

    private static String readStringFromStdIn(String text) {
        System.out.print(text + " ");
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
        try (Scanner s = new Scanner(System.in)) {
            do {
                String modus = s.next();
                switch (modus) {
                    case EINFUEGEMODUS:
                        try {
                            String[] addParts = readStringFromStdIn("Einfügen: ").split("\\s+");
                            if (addParts.length == 7) {
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
                                } //TODO: default invalid input
                            } else if (addParts.length == 1) {
                                Hersteller hersteller = new HerstellerImpl(addParts[0]);
                                InputEventHersteller herstellerEvent = new InputEventHersteller(this, modus, hersteller);
                                if (null != this.herstellerHandler) herstellerHandler.handle(herstellerEvent);
                            } else {
                                System.err.println("Input is not valid");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;


                    case LOESCHMODUS:
                        try {
                            String[] removeParts = readStringFromStdIn("Löschen: ").split("\\s+");
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
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;

                    case ANZEIGEMODUS:
                        try {
                            String[] anzeigeParts = readStringFromStdIn("Anzeigen: ").split("\\s+");
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
                                    InputEventString printKuchenListEvent = new InputEventString(this, modus, anzeigeParts[0]);
                                    if (null != this.stringHandler) stringHandler.handle(printKuchenListEvent);
                                    break;
                            }

                        } catch (Exception e) {
                            this.outputEventHandlerString.handle(new OutputEventString(this, "ERROR"));
                        }
                        break;

                    case PERSISTENZMODUS:
                        try {
                            String[] persistenzParts = readStringFromStdIn("Eingabe: ").split("\\s+");
                            switch (persistenzParts[0]) {
                                case (SAVEJOS):
                                    InputEventString saveJOSEvent = new InputEventString(this, modus, persistenzParts[0]);
                                    if (null != this.stringHandler) stringHandler.handle(saveJOSEvent);
                                    break;
                                case (LOADJOS):
                                    InputEventString loadJOSEvent = new InputEventString(this, modus, persistenzParts[0]);
                                    if (null != this.stringHandler) stringHandler.handle(loadJOSEvent);
                                    break;
                                default:
                                    this.outputEventHandlerString.handle(new OutputEventString(this, "ERROR: invalid input"));

                                    break;


                            }

                        } catch (Exception e) {
                            this.outputEventHandlerString.handle(new OutputEventString(this, "ERROR"));
                        }
                }
            } while (true);
        }
    }
}


