package consoleReader;

import automat.AutomatException;
import control.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsoleReaderTest {

    private ConsoleReader consoleReader;

    private static final String EINFUEGEMODUS = ":c";


    private InputEventHandlerInteger integerHandler;
    private InputEventHandlerString stringHandler;
    private InputEventHandlerCake cakeHandler;
    private InputEventHandlerHersteller herstellerHandler;
    private OutputEventHandlerString outputEventHandlerString;

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
    public void addHersteller() {
       try {
           this.consoleReader.einfugen("hersteller", EINFUEGEMODUS);

           verify(herstellerHandler, times(1)).handle(any(InputEventHersteller.class));
       }catch (AutomatException e){
           fail();
       }
    }

}