package simulation;

import automat.AutomatException;
import automat.AutomatVerwaltung;
import automat.KuchenKomponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.*;

public class AutomatVerwaltungSimulationTest {


    private AutomatVerwaltung automat;
    private AutomatVerwaltungSimulation automatSimulation;

    @BeforeEach
    void setUp() throws AutomatException {
        this.automat = new AutomatVerwaltung(2);
        this.automatSimulation = new AutomatVerwaltungSimulation(automat);
        this.automatSimulation.addHerstellers();
    }

    @Test
    public void addRandomKuchen() throws AutomatException, InterruptedException {
        automatSimulation.addRandomKuchen();
        Assertions.assertTrue(automat.getAnzahlKuchenImAutomat() == 1);
    }

    @Test
    public void deleteRandomKuchen() throws AutomatException, InterruptedException {
        automatSimulation.addRandomKuchen();
        automatSimulation.deleteRandomKuchen();
        Assertions.assertTrue(automat.getAnzahlKuchenImAutomat() == 0);
    }

    @Test
    public synchronized void deleteOldestInpectedKuchen() throws AutomatException, InterruptedException, NullPointerException {
        automatSimulation.addRandomKuchen();
        automatSimulation.addRandomKuchen();

        automat.getCakeList()[0].setEinfuegeDatum(new GregorianCalendar(2021, Calendar.JULY, 15).getTime());
        automat.getCakeList()[1].setEinfuegeDatum(new GregorianCalendar(2021, Calendar.JULY, 20).getTime());

        automatSimulation.deleteOldestInpectedKuchen();

        Assertions.assertEquals(0, automat.getAvailableFachnummer());
    }

    @Test
    public synchronized void inspectRandomKuchen() throws AutomatException,  InterruptedException {

        AutomatVerwaltung mockAutomat = mock(AutomatVerwaltung.class);
        AutomatVerwaltungSimulation automatSimulation = new AutomatVerwaltungSimulation(mockAutomat);

        KuchenKomponent mockCake = mock(KuchenKomponent.class);
        KuchenKomponent[] mockCakeList = {mockCake};

        when(mockAutomat.getCakeList()).thenReturn(mockCakeList);
        when(mockAutomat.getAnzahlKuchenImAutomat()).thenReturn(1);

        automatSimulation.inspectRandomKuchen();

        verify(mockAutomat, times(1)).inspectCake(0);

    }

    @Test
    public void isFull() throws AutomatException, InterruptedException {
        this.automatSimulation.addRandomKuchen();
        this.automatSimulation.addRandomKuchen();

        Assertions.assertTrue(automat.getAnzahlKuchenImAutomat() == automat.getCakeList().length);
    }

    @Test
    public void isEmpty() throws AutomatException {
        Assertions.assertTrue(this.automat.getAnzahlKuchenImAutomat() == 0);
    }
}
