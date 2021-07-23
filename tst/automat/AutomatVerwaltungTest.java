package automat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

import static org.mockito.Mockito.mock;

public class AutomatVerwaltungTest {

    AutomatVerwaltung automat = new AutomatVerwaltung(20);
    Date inspektionsDate = new Date();
    Date einfügedatum = new Date();

    Hersteller h1 = new HerstellerImpl("hersteller1");
    Hersteller h2 = new HerstellerImpl("hersteller2");
    Hersteller h3 = new HerstellerImpl("hersteller3");


    KuchenKomponent obstkuchen = new KuchenBoden(Kuchentyp.OBSTKUCHEN, h1,BigDecimal.valueOf(3.5),200, Duration.ofDays(3), EnumSet.of(Allergen.Gluten));
    KuchenKomponent kremkuchen = new KuchenBoden(Kuchentyp.KREMKUCHEN, h1,BigDecimal.valueOf(3.5),200, Duration.ofDays(3), EnumSet.of(Allergen.Gluten));
    KuchenKomponent kremkuchen2 = new KuchenBoden(Kuchentyp.KREMKUCHEN, h1,BigDecimal.valueOf(3.5),200, Duration.ofDays(3), EnumSet.of(Allergen.Gluten) );

    KuchenKomponent o1 = new KuchenBelag(obstkuchen, BigDecimal.valueOf(1), 50, Duration.ofDays(3),  EnumSet.of(Allergen.Erdnuss), "Creme");
    KuchenKomponent k2 = new KuchenBelag(kremkuchen, BigDecimal.valueOf(1), 50, Duration.ofDays(3),  EnumSet.of(Allergen.Haselnuss), "Nutella");

    public AutomatVerwaltungTest() throws AutomatException {
    }

    @Test
    public void addHersteller() throws AutomatException {

        automat.addHersteller(h1);

        Assertions.assertTrue(automat.getHerstellerList().contains(h1));

    }

    @Test
    public void addHerstellerDoppelt() throws AutomatException {

        automat.addHersteller(h1);

        Assertions.assertThrows(AutomatException.class, () -> automat.addHersteller(h1));

    }

    @Test
    public void removeHersteller() throws AutomatException {

        automat.addHersteller(h1);

        automat.addHersteller(h2);

        automat.addHersteller(h3);

        automat.removeHersteller("hersteller2");

        Assertions.assertEquals(automat.getHerstellerListMitKuchenAnzahl().size(), 2);
    }

    @Test
    public void addObstkuchen() throws AutomatException, InterruptedException {

        automat.addHersteller(h1);

        automat.addKuchen(obstkuchen);
        Assertions.assertEquals(automat.getCakeList()[0], obstkuchen);

    }

    @Test
    public void addKremkuchen() throws AutomatException, InterruptedException {

        automat.addHersteller(h1);

        automat.addKuchen(o1);

        Assertions.assertEquals(automat.getCakeList()[0], o1);
    }


    @Test
    public void removeKuchen() throws AutomatException, InterruptedException {

        automat.addHersteller(h1);

        automat.addKuchen(k2);
        automat.removeKuchen(0);

        Assertions.assertEquals(automat.getCakeList()[0], null);

    }

    @Test
    public void inspektionsdatumAlleKuchenAendern() throws AutomatException, InterruptedException {
        automat.addHersteller(h1);
        automat.addHersteller(h2);


        automat.addKuchen(o1);
        automat.addKuchen(k2);

        Date date = new Date();

        automat.inspectAutomat();

        Assertions.assertEquals(automat.getCakeList()[0].getInspektionsdatum(), date );
        Assertions.assertEquals(automat.getCakeList()[1].getInspektionsdatum(), date );
    }


    @Test
    public void inspektionsdatumEineKuchen() throws AutomatException, InterruptedException {
        automat.addHersteller(h1);

        automat.addKuchen(obstkuchen);

        Date date = new Date();

        automat.inspectCake(0);

        Assertions.assertEquals(automat.getCakeList()[0].getInspektionsdatum(), date );

    }

   @Test
    public void addManyCakesGood() throws AutomatException, InterruptedException {

        automat.addHersteller(h1);
        automat.addHersteller(h2);


        automat.addKuchen(o1);
        automat.addKuchen(kremkuchen);


        Assertions.assertEquals(automat.getCakeList()[0], o1);
        Assertions.assertEquals(automat.getCakeList()[1], kremkuchen);

    }


    @Test
    public void addCakeHerstellerNotExisting() throws AutomatException {

        Assertions.assertThrows(AutomatException.class, () ->  automat.addKuchen(o1));

    }

    @Test
    public void getAlleKuchenEinesTypGut() throws AutomatException, InterruptedException {

        automat.addHersteller(h1);
        automat.addHersteller(h2);


        automat.addKuchen(o1);
        automat.addKuchen(o1);
        automat.addKuchen(k2);

       KuchenKomponent[] kuchentypList = automat.getAlleKuchenEinesTyps(Kuchentyp.OBSTKUCHEN.toString());

         Assertions.assertEquals(kuchentypList.length, 2);
         Assertions.assertEquals(automat.getAlleKuchenEinesTyps("KREMKUCHEN").length, 1);



    }

    @Test
    public void getAnzahlKuchenProHersteller() throws AutomatException, InterruptedException {

        automat.addHersteller(h1);


        automat.addKuchen(k2);
        automat.addKuchen(kremkuchen2);
        automat.addKuchen(kremkuchen);

        automat.getHerstellerListMitKuchenAnzahl();

        Assertions.assertEquals( automat.getHerstellerListMitKuchenAnzahl().get(h1), 3);

    }

    @Test
    public void mockitoAddHerstellerDoppelt() throws AutomatException {

        Hersteller hersteller = mock(HerstellerImpl.class);
        automat.addHersteller(hersteller);

        Assertions.assertThrows(Exception.class, () -> automat.addHersteller(hersteller));

    }

    @Test
    public void mockitoAddCakeHerstellerNotExisting() throws AutomatException {

        KuchenKomponent kuchen = mock(KuchenKomponent.class);

        Assertions.assertThrows(AutomatException.class, () ->  automat.addKuchen(kuchen));

    }

    @Test
    public void getHerstellerTest() throws AutomatException, InterruptedException {

       automat.addHersteller(h1);
       automat.addKuchen(k2);

       Assertions.assertEquals(automat.getCakeList()[0].getHersteller(), h1);
    }

    @Test
    public void getAllergenTest() throws AutomatException, InterruptedException {

        automat.addHersteller(h1);
        automat.addKuchen(obstkuchen);

        Assertions.assertEquals(automat.getCakeList()[0].getAllergene(), Collections.singleton(Allergen.Gluten));
    }
    @Test
    public void getNaehrwertTest() throws AutomatException, InterruptedException {

        automat.addHersteller(h1);
        automat.addKuchen(obstkuchen);

        Assertions.assertEquals(automat.getCakeList()[0].getNaehrwert(), 200);
    }

    @Test
    public void getHaltbarkeitTest() throws AutomatException, InterruptedException {

        automat.addHersteller(h1);
        automat.addKuchen(kremkuchen);

        Assertions.assertEquals(automat.getCakeList()[0].getHaltbarkeit(), Duration.ofDays(3));
    }

    @Test
    public void getPreisTest() throws AutomatException, InterruptedException {

        automat.addHersteller(h1);
        automat.addKuchen(obstkuchen);

        Assertions.assertEquals(automat.getCakeList()[0].getPreis(),BigDecimal.valueOf(3.5) );
    }



    @Test
    public void getFachnummerTest() throws AutomatException, InterruptedException {

        automat.addHersteller(h1);
        automat.addKuchen(kremkuchen);
        automat.addKuchen(obstkuchen);


        Assertions.assertEquals(0, automat.getCakeList()[0].getFachnummer());
        Assertions.assertEquals(1, automat.getCakeList()[1].getFachnummer());
    }



    @Test
    public void getVorhandeneAllergene() throws AutomatException, InterruptedException {

        automat.addHersteller(h1);
        automat.addKuchen(k2);

        Assertions.assertEquals(k2.getAllergene(), automat.getVorhandeneAllergene());

    }

    @Test
    public void getNichtVorhandeneAllergene() throws AutomatException, InterruptedException {

        automat.addHersteller(h1);
        automat.addKuchen(k2);
        automat.addKuchen(kremkuchen);

        EnumSet<Allergen> allergensList = EnumSet.allOf(Allergen.class);
        allergensList.removeAll(k2.getAllergene());

        Assertions.assertEquals(allergensList, automat.getNichtVorhandeneAllergene());


    }

}
