package automat;

import eventHandlers.OutputEventHandlerString;
import events.InputEventString;
import events.OutputEventString;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AllergeneBeobachterTest {

    @Test
    void aktualisiere() {

        final AutomatVerwaltung automatVerwaltungMock = mock(AutomatVerwaltung.class);
        final AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automatVerwaltungMock);
        final OutputEventHandlerString outputEventHandlerStringMock = mock(OutputEventHandlerString.class);
        final AllergeneBeobachter allergeneBeobachter = new AllergeneBeobachter(automatPlaceHolder, outputEventHandlerStringMock);

        EnumSet<Allergen> allergens = EnumSet.noneOf(Allergen.class);
        allergens.add(Allergen.Erdnuss);

        when(automatVerwaltungMock.getVorhandeneAllergene()).thenReturn(allergens);

        allergeneBeobachter.aktualisiere();
        verify(outputEventHandlerStringMock, times(1)).handle(any(OutputEventString.class));

    }

    @Test
    void aktualisiereNoChange() {

            final AutomatVerwaltung automatVerwaltungMock = mock(AutomatVerwaltung.class);
            final AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automatVerwaltungMock);
            final OutputEventHandlerString outputEventHandlerStringMock = mock(OutputEventHandlerString.class);
            final AllergeneBeobachter allergeneBeobachter = new AllergeneBeobachter(automatPlaceHolder, outputEventHandlerStringMock);

            EnumSet<Allergen> allergens = EnumSet.noneOf(Allergen.class);

            when(automatVerwaltungMock.getVorhandeneAllergene()).thenReturn(allergens);
            allergeneBeobachter.aktualisiere();
            verify(outputEventHandlerStringMock, times(0)).handle(any(OutputEventString.class));
        }
    }


