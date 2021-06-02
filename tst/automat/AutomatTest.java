package automat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

import static org.mockito.Mockito.mock;

public class AutomatTest {

    AutomatVerwaltung automat = new AutomatVerwaltung(20);
    Date inspektionsDate = new Date();
    Date einfügedatum = new Date();

    Hersteller hersteller1 = new HerstellerImpl("hersteller1");
    Hersteller hersteller2 = new HerstellerImpl("hersteller2");
    Hersteller hersteller3= new HerstellerImpl("hersteller3");

    Cake berryCake = new ObstkuchenImpl(hersteller1, BigDecimal.valueOf(3.5),200, Duration.ofDays(3), Collections.singleton(Allergen.Gluten) ,   getRandomDate(), automat.getAvailableFachnummer(), einfügedatum ,"berry" );
    Cake butterCake = new KremkuchenImpl(hersteller1, BigDecimal.valueOf(3.5),200, Duration.ofDays(3), Collections.singleton(Allergen.Erdnuss) ,   inspektionsDate, automat.getAvailableFachnummer(), einfügedatum ,"butter" );
    Cake berryCake2 = new ObstkuchenImpl(hersteller1, BigDecimal.valueOf(3.5),200, Duration.ofDays(3), Collections.singleton(Allergen.Gluten) ,   inspektionsDate, automat.getAvailableFachnummer(), einfügedatum ,"apple" );

    public AutomatTest() throws AutomatException {
    }
    // Cake butterBerryCake = new ObsttorteImpl(hersteller1, BigDecimal.valueOf(3.5),200, Duration.ofDays(3), Collections.singleton(Allergen.Gluten) ,   inspektionsDate, automat.getAvailableFachnummer(), einfügedatum ,"berry", "butter" );

    public Date getRandomDate(){
        Random  rnd;
        long    ms;
        rnd = new Random();
        ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        return new Date(ms);
    }

    @Test
    public void addHersteller() throws AutomatException {

        automat.addHersteller(hersteller1);

        Assertions.assertTrue(automat.getHerstellerList().contains(hersteller1));

    }

    @Test
    public void addHerstellerDoppelt() throws AutomatException {

        automat.addHersteller(hersteller1);

        Assertions.assertThrows(AutomatException.class, () -> automat.addHersteller(hersteller1));

    }

    @Test
    public void removeHersteller() throws AutomatException {

        automat.addHersteller(hersteller1);

        automat.addHersteller(hersteller2);

        automat.addHersteller(hersteller3);

        automat.removeHersteller("hersteller2");

        Assertions.assertEquals(automat.getHerstellerListMitKuchenAnzahl().size(), 2);
    }

    @Test
    public void addObstkuchen() throws AutomatException, InterruptedException {

        automat.addHersteller(hersteller1);

        automat.addKuchen(berryCake);
        Assertions.assertEquals(automat.getCakeList()[0], berryCake);

    }

    @Test
    public void addKremkuchen() throws AutomatException, InterruptedException {

        automat.addHersteller(hersteller1);

        automat.addKuchen(butterCake);

        Assertions.assertEquals(automat.getCakeList()[0], butterCake);
    }


    @Test
    public void removeKuchen() throws AutomatException, InterruptedException {

        automat.addHersteller(hersteller1);

        automat.addKuchen(berryCake);
        automat.removeKuchen(0);

        Assertions.assertEquals(automat.getCakeList()[0], null);

    }

    @Test
    public void inspektionsdatumAlleKuchenAendern() throws AutomatException, InterruptedException {
        automat.addHersteller(hersteller1);
        automat.addHersteller(hersteller2);


        automat.addKuchen(butterCake);
        automat.addKuchen(berryCake);

        Date date = new Date();

        automat.inspectAutomat();

        Assertions.assertEquals(automat.getCakeList()[0].getInspektionsdatum(), date );
        Assertions.assertEquals(automat.getCakeList()[1].getInspektionsdatum(), date );
    }


    @Test
    public void inspektionsdatumEineKuchen() throws AutomatException, InterruptedException {
        automat.addHersteller(hersteller1);

        automat.addKuchen(butterCake);

        Date date = new Date();

        automat.inspectCake(0);

        Assertions.assertEquals(automat.getCakeList()[0].getInspektionsdatum(), date );

    }

   @Test
    public void addManyCakesGood() throws AutomatException, InterruptedException {

        automat.addHersteller(hersteller1);
        automat.addHersteller(hersteller2);


        automat.addKuchen(butterCake);
        automat.addKuchen(berryCake);


        Assertions.assertEquals(automat.getCakeList()[0], butterCake);
        Assertions.assertEquals(automat.getCakeList()[1], berryCake);

    }


    @Test
    public void addCakeHerstellerNotExisting() throws AutomatException {

        Assertions.assertThrows(AutomatException.class, () ->  automat.addKuchen(butterCake));

    }

    @Test
    public void getAlleKuchenEinesTypGut() throws AutomatException, InterruptedException {

        automat.addHersteller(hersteller1);
        automat.addHersteller(hersteller2);


        automat.addKuchen(berryCake);
        automat.addKuchen(berryCake2);
        automat.addKuchen(butterCake);


        Assertions.assertEquals(automat.getAlleKuchenEinesTyps("Obstkuchen").size(), 2);
        Assertions.assertEquals(automat.getAlleKuchenEinesTyps("Kremkuchen").size(), 1);

    }

    @Test
    public void getAnzahlKuchenProHersteller() throws AutomatException, InterruptedException {

        automat.addHersteller(hersteller1);


        automat.addKuchen(berryCake);
        automat.addKuchen(berryCake2);
        automat.addKuchen(butterCake);

        Assertions.assertEquals( automat.getHerstellerListMitKuchenAnzahl().get(hersteller1), 3);

    }

    @Test
    public void mockitoAddHerstellerDoppelt() throws AutomatException {

        Hersteller hersteller = mock(HerstellerImpl.class);
        automat.addHersteller(hersteller);

        Assertions.assertThrows(Exception.class, () -> automat.addHersteller(hersteller));

    }

    @Test
    public void mockitoAddCakeHerstellerNotExisting() throws AutomatException {

        Cake cake = mock(ObstkuchenImpl.class);

        Assertions.assertThrows(AutomatException.class, () ->  automat.addKuchen(cake));

    }

    @Test
    public void getHerstellerTest() throws AutomatException, InterruptedException {

       automat.addHersteller(hersteller1);
       automat.addKuchen(berryCake);

       Assertions.assertEquals(automat.getCakeList()[0].getHersteller(), hersteller1);
    }

    @Test
    public void getAllergenTest() throws AutomatException, InterruptedException {

        automat.addHersteller(hersteller1);
        automat.addKuchen(berryCake);

        Assertions.assertEquals(automat.getCakeList()[0].getAllergene(), Collections.singleton(Allergen.Gluten));
    }
    @Test
    public void getNaehrwertTest() throws AutomatException, InterruptedException {

        automat.addHersteller(hersteller1);
        automat.addKuchen(berryCake);

        Assertions.assertEquals(automat.getCakeList()[0].getNaehrwert(), 200);
    }

    @Test
    public void getHaltbarkeitTest() throws AutomatException, InterruptedException {

        automat.addHersteller(hersteller1);
        automat.addKuchen(berryCake);

        Assertions.assertEquals(automat.getCakeList()[0].getHaltbarkeit(), Duration.ofDays(3));
    }

    @Test
    public void getPreisTest() throws AutomatException, InterruptedException {

        automat.addHersteller(hersteller1);
        automat.addKuchen(berryCake);

        Assertions.assertEquals(automat.getCakeList()[0].getPreis(),BigDecimal.valueOf(3.5) );
    }



    @Test
    public void getFachnummerTest() throws AutomatException, InterruptedException {

        automat.addHersteller(hersteller1);
        automat.addHersteller(hersteller2);
        automat.addKuchen(berryCake);
        automat.addKuchen(butterCake);


        Assertions.assertEquals(automat.getCakeList()[0].getFachnummer(),0);
        Assertions.assertEquals(automat.getCakeList()[1].getFachnummer(),1);
    }

    @Test
    public void getEinfügedatumTest() throws AutomatException, InterruptedException {

        automat.addHersteller(hersteller1);
        automat.addKuchen(berryCake);

        Assertions.assertEquals(automat.getCakeList()[0].getEinfuegedatum(), einfügedatum);
    }

    @Test
    public void getObstsorteTest() throws AutomatException, InterruptedException {

        automat.addHersteller(hersteller1);
        automat.addKuchen(berryCake);

        Assertions.assertEquals("berry", ((ObstkuchenImpl) automat.getCakeList()[0]).getObstsorte());

    }


    @Test
    public void getVorhandeneAllergene() throws AutomatException, InterruptedException {

        automat.addHersteller(hersteller1);
        automat.addKuchen(berryCake);
        automat.addHersteller(hersteller2);
        automat.addKuchen(butterCake);
        List<Allergen> allergenList = new LinkedList<>();
        allergenList.addAll(berryCake.getAllergene());
        allergenList.addAll(butterCake.getAllergene());

        Assertions.assertEquals(EnumSet.copyOf(allergenList), automat.getVorhandeneAllergene());

    }

    @Test
    public void getNichtVorhandeneAllergene() throws AutomatException, InterruptedException {

        automat.addHersteller(hersteller1);
        automat.addKuchen(berryCake);
        automat.addHersteller(hersteller2);
        automat.addKuchen(butterCake);


       // Assertions.assertEquals(EnumSet.of(Allergen.Haselnuss, Allergen.Sesamsamen), automat.getNichtVorhandeneAllergene());
        Assertions.assertTrue(EnumSet.of(Allergen.Haselnuss, Allergen.Sesamsamen).containsAll( automat.getNichtVorhandeneAllergene()) && automat.getNichtVorhandeneAllergene().containsAll(EnumSet.of(Allergen.Haselnuss, Allergen.Sesamsamen)) );

    }
}
