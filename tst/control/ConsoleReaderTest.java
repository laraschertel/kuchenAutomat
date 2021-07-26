package control;

import automat.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsoleReaderTest {

    private static final String ANZEIGEMODUS = ":r";
    private static final String LOESCHENMODUS = ":d";
    private ConsoleReader consoleReader;

    private static final String EINFUEGEMODUS = ":c";


    private InputEventHandlerInteger integerHandler;
    private InputEventHandlerString stringHandler;
    private InputEventHandlerCake cakeHandler;
    private InputEventHandlerHersteller herstellerHandler;
    private OutputEventHandlerString outputEventHandlerString;
    private Hersteller h1;
    private Hersteller h2;
    private KuchenKomponent obstkuchen;
    private KuchenKomponent kremkuchen;

    @BeforeEach
    public void setUp(){
       this.consoleReader = new ConsoleReader();
        this.integerHandler = mock(InputEventHandlerInteger.class);
        this.stringHandler = mock(InputEventHandlerString.class);
        this.cakeHandler = mock(InputEventHandlerCake.class);
        this.herstellerHandler = mock(InputEventHandlerHersteller.class);
        this.outputEventHandlerString = mock(OutputEventHandlerString.class);

        consoleReader.setHandlers(integerHandler, stringHandler, cakeHandler, herstellerHandler, outputEventHandlerString);
    }

   @Test
    public void removeHersteller() {
       try {
           this.consoleReader.einfugen("hersteller", EINFUEGEMODUS);
           this.consoleReader.loeschen("hersteller", LOESCHENMODUS);
           verify(stringHandler, times(1)).handle(any(InputEventString.class));
       }catch (AutomatException | IOException | InterruptedException e){
           fail();
       }
    }

    @Test
    public void removeHerstellerFalsch() {
        Assertions.assertThrows(Exception.class, () ->   this.consoleReader.einfugen("h1 h2", EINFUEGEMODUS));

    }

    @Test
    public void addHersteller() {
        try {
            this.consoleReader.einfugen("hersteller", EINFUEGEMODUS);

            verify(herstellerHandler, times(1)).handle(any(InputEventHersteller.class));
        }catch (AutomatException e){
            fail();
        }
    }

    @Test
    public void addHerstellerFalsch() {
        Assertions.assertThrows(Exception.class, () ->   this.consoleReader.einfugen("h1 h2", EINFUEGEMODUS));

    }

    @Test
    public void addKuchen(){

        h1 = new HerstellerImpl("Hersteller1");
        h2 = new HerstellerImpl("Hersteller2");

        try {
            String kuchen = "Kremkuchen Hersteller1 4,50 386 36 , Butter 2,50 200 12 Erdnuss Sahne";
            this.consoleReader.einfugen(kuchen, EINFUEGEMODUS);
            verify(cakeHandler, times(1)).handle(any(InputEventCake.class));
        } catch (AutomatException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void addKuchenFehler(){

        h1 = new HerstellerImpl("hersteller1");
        h2 = new HerstellerImpl("hersteller2");

        String kuchen = "Kremkuchen Hersteller1";
        Assertions.assertThrows(Exception.class, () ->   this.consoleReader.einfugen(kuchen, EINFUEGEMODUS));
    }

    @Test
    public void listAllergeneFehler(){
        Assertions.assertThrows(Exception.class, () ->   this.consoleReader.anzeigen("allergene", ANZEIGEMODUS));
    }


}