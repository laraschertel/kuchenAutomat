package automat;

import control.AllergeneBeobachter;
import control.KapazitaetBeobachter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class AutomatVerwaltungTest {

    AutomatVerwaltung automat;

    Hersteller h1;
    Hersteller h2;
    Hersteller h3;

    KuchenKomponent obstkuchen;
    KuchenKomponent kremkuchen;
    KuchenKomponent kremkuchen2;

    KuchenKomponent o1;
    KuchenKomponent k2;

   @BeforeEach
   public void setUp() throws AutomatException {
       automat = new AutomatVerwaltung(20);

        h1 = new HerstellerImpl("hersteller1");
        h2 = new HerstellerImpl("hersteller2");
        h3 = new HerstellerImpl("hersteller3");

       obstkuchen = new KuchenBoden(Kuchentyp.OBSTKUCHEN, h1,BigDecimal.valueOf(3.5),200, Duration.ofDays(3), EnumSet.of(Allergen.Gluten));
       kremkuchen = new KuchenBoden(Kuchentyp.KREMKUCHEN, h1,BigDecimal.valueOf(3.5),200, Duration.ofDays(3), EnumSet.of(Allergen.Gluten));
       kremkuchen2 = new KuchenBoden(Kuchentyp.KREMKUCHEN, h1,BigDecimal.valueOf(3.5),200, Duration.ofDays(3), EnumSet.of(Allergen.Gluten) );

       o1 = new KuchenBelag(obstkuchen, BigDecimal.valueOf(1), 50, Duration.ofDays(3),  EnumSet.of(Allergen.Erdnuss), "Creme");
       k2 = new KuchenBelag(kremkuchen, BigDecimal.valueOf(1), 50, Duration.ofDays(3),  EnumSet.of(Allergen.Haselnuss), "Nutella");

   }

    @Test
    public void addHersteller() {

        try {
            automat.addHersteller(h1);
        } catch (AutomatException e) {
            fail();
        }

        Assertions.assertTrue(automat.getHerstellerList().contains(h1));

    }

    @Test
    public void addHerstellerDoppelt() {

        try {
            automat.addHersteller(h1);
        } catch (AutomatException e) {
            fail();
        }

        Assertions.assertThrows(AutomatException.class, () -> automat.addHersteller(h1));

    }

    @Test
    public void removeHersteller(){
        try {
            automat.addHersteller(h1);

            automat.addHersteller(h2);

            automat.addHersteller(h3);

            automat.removeHersteller("hersteller2");
        } catch (AutomatException e) {
            fail();
        }

        Assertions.assertEquals(2, automat.getHerstellerListMitKuchenAnzahl().size());
    }


    @Test
    public void addObstkuchenBoden() {

        try {
            automat.addHersteller(h1);
            automat.addKuchen(obstkuchen);
        } catch (AutomatException e) {
            fail();
        } catch (InterruptedException e) {
            fail();
        }
        Assertions.assertEquals(obstkuchen, automat.getCakeList()[0]);

    }

    @Test
    public void addObstkuchenBelag() {

        try {
            automat.addHersteller(h1);
            automat.addKuchen(o1);
        } catch (AutomatException e) {
            fail();
        } catch (InterruptedException e) {
            fail();
        }
        Assertions.assertEquals(o1, automat.getCakeList()[0]);

    }
    @Test
    public void addObstkuchenBodenUndBelag() {

        try {
            automat.addHersteller(h1);
            automat.addKuchen(obstkuchen);
            automat.addKuchen(o1);
            Assertions.assertEquals(2, automat.getAnzahlKuchenImAutomat());
        } catch (AutomatException e) {
            fail();
        } catch (InterruptedException e) {
            fail();
        }

    }

    @Test
    public void addKremkuchenBoden() {

        try {
            automat.addHersteller(h1);
            automat.addKuchen(kremkuchen);
        } catch (AutomatException e) {
            fail();
        } catch (InterruptedException e) {
            fail();
        }
        Assertions.assertEquals(kremkuchen, automat.getCakeList()[0]);

    }

    @Test
    public void addKremkuchenBodenUndBelag() {

        try {
            automat.addHersteller(h1);
            automat.addKuchen(kremkuchen);
            automat.addKuchen(o1);
            Assertions.assertEquals(2, automat.getAnzahlKuchenImAutomat());
        } catch (AutomatException | InterruptedException e) {
            fail();
        }

    }


    @Test
    public void addKremkuchenBelag() {

        try {
            automat.addHersteller(h1);
            automat.addKuchen(o1);
        } catch (AutomatException | InterruptedException e) {
            fail();
        }

        Assertions.assertEquals(automat.getCakeList()[0], o1);
    }


    @Test
    public void removeKuchen(){

       try{
            automat.addHersteller(h1);
            automat.addKuchen(k2);
            automat.removeKuchen(0);
    } catch (AutomatException | InterruptedException e) {
        fail();
    }

        Assertions.assertEquals(automat.getCakeList()[0], null);

    }

    @Test
    public void inspektionsdatumAlleKuchenAendern() {

        try {
            automat.addHersteller(h1);
            automat.addHersteller(h2);
            automat.addKuchen(o1);
            automat.addKuchen(k2);
        } catch (AutomatException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Date date = new Date();

        automat.inspectAutomat();

        Assertions.assertEquals(automat.getCakeList()[0].getInspektionsdatum(), date );
        Assertions.assertEquals(automat.getCakeList()[1].getInspektionsdatum(), date );
    }


    @Test
    public void inspektionsdatumEineKuchen()  {
        try {
            automat.addHersteller(h1);
            automat.addKuchen(obstkuchen);

            automat.inspectCake(0);
        } catch (AutomatException | InterruptedException e) {
            e.printStackTrace();
        }

        Date date = new Date();

        Assertions.assertEquals(automat.getCakeList()[0].getInspektionsdatum(), date );

    }

   @Test
    public void addManyCakesGood() {

       try {
            automat.addHersteller(h1);
            automat.addHersteller(h2);
            automat.addKuchen(o1);
           automat.addKuchen(kremkuchen);
       } catch (AutomatException e) {
           e.printStackTrace();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

       Assertions.assertEquals(automat.getCakeList()[0], o1);
        Assertions.assertEquals(automat.getCakeList()[1], kremkuchen);

    }

    @Test
    public void addCakeHerstellerNotExisting() {

        Assertions.assertThrows(AutomatException.class, () ->  automat.addKuchen(o1));

    }

    @Test
    public void getAlleKuchenEinesTypGut() {
        try {
            automat.addHersteller(h1);
            automat.addHersteller(h2);
            automat.addKuchen(o1);
            automat.addKuchen(o1);
            automat.addKuchen(k2);
        } catch (AutomatException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        KuchenKomponent[] kuchentypList = automat.getAlleKuchenEinesTyps(Kuchentyp.OBSTKUCHEN.toString());

         Assertions.assertEquals(kuchentypList.length, 2);
         Assertions.assertEquals(automat.getAlleKuchenEinesTyps("KREMKUCHEN").length, 1);

    }

    @Test
    public void getAnzahlKuchenProHersteller() {
        try {
            automat.addHersteller(h1);
            automat.addKuchen(k2);
            automat.addKuchen(kremkuchen2);
            automat.addKuchen(kremkuchen);
        } catch (AutomatException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        automat.getHerstellerListMitKuchenAnzahl();

        Assertions.assertEquals( automat.getHerstellerListMitKuchenAnzahl().get(h1), 3);

    }

    @Test
    public void mockitoAddHerstellerDoppelt() {

        Hersteller hersteller = mock(HerstellerImpl.class);
        try {
            automat.addHersteller(hersteller);
        } catch (AutomatException e) {
            e.printStackTrace();
        }

        Assertions.assertThrows(Exception.class, () -> automat.addHersteller(hersteller));

    }

    @Test
    public void mockitoAddCakeHerstellerNotExisting(){

        KuchenKomponent kuchen = mock(KuchenKomponent.class);

        Assertions.assertThrows(AutomatException.class, () ->  automat.addKuchen(kuchen));

    }

    @Test
    public void getHerstellerTest()  {
        try {
            automat.addHersteller(h1);
            automat.addKuchen(k2);
        } catch (AutomatException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(automat.getCakeList()[0].getHersteller(), h1);
    }

    @Test
    public void getAllergenTest(){
        try {
            automat.addHersteller(h1);
            automat.addKuchen(obstkuchen);
        } catch (AutomatException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(automat.getCakeList()[0].getAllergene(), Collections.singleton(Allergen.Gluten));
    }
    @Test
    public void getNaehrwertTest() {
        try {
            automat.addHersteller(h1);
            automat.addKuchen(obstkuchen);
        } catch (AutomatException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(automat.getCakeList()[0].getNaehrwert(), 200);
    }

    @Test
    public void getHaltbarkeitTest(){
        try {
            automat.addHersteller(h1);
            automat.addKuchen(kremkuchen);
        } catch (AutomatException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(automat.getCakeList()[0].getHaltbarkeit(), Duration.ofDays(3));
    }

    @Test
    public void getPreisTest() {
        try {
            automat.addHersteller(h1);
            automat.addKuchen(obstkuchen);
        } catch (AutomatException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(automat.getCakeList()[0].getPreis(),BigDecimal.valueOf(3.5) );
    }

    @Test
    public void getFachnummer(){
        try {
            automat.addHersteller(h1);
            automat.addKuchen(kremkuchen);
            automat.addKuchen(obstkuchen);
        } catch (AutomatException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(0, automat.getCakeList()[0].getFachnummer());
        Assertions.assertEquals(1, automat.getCakeList()[1].getFachnummer());
    }


    @Test
    public void getVorhandeneAllergene(){
        try {
            automat.addHersteller(h1);
            automat.addKuchen(k2);
        } catch (AutomatException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(k2.getAllergene(), automat.getVorhandeneAllergene());

    }

    @Test
    public void getNichtVorhandeneAllergene() {
        try {
            automat.addHersteller(h1);
            automat.addKuchen(k2);
            automat.addKuchen(kremkuchen);
        } catch (AutomatException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        EnumSet<Allergen> allergensList = EnumSet.allOf(Allergen.class);
        allergensList.removeAll(k2.getAllergene());

        Assertions.assertEquals(allergensList, automat.getNichtVorhandeneAllergene());


    }

    @Test
    void beobachterAnmelden() {
        try {
            AutomatVerwaltung automatVerwaltung = new AutomatVerwaltung(5);
            Beobachter beobachter = mock(AllergeneBeobachter.class);
            automatVerwaltung.meldeAn(beobachter);

            Assertions.assertTrue(automatVerwaltung.beobachterList.contains(beobachter));

        } catch (AutomatException e) {
            fail();
        }
    }

    @Test
    void meherereBeobachterAnmelden() {
        try {
            AutomatVerwaltung automatVerwaltung = new AutomatVerwaltung(5);
            Beobachter allergeneBeobachter = mock(AllergeneBeobachter.class);
            Beobachter capacityBeobachter = mock(KapazitaetBeobachter.class);
            automatVerwaltung.meldeAn(allergeneBeobachter);
            automatVerwaltung.meldeAn(capacityBeobachter);

            Assertions.assertTrue(automatVerwaltung.beobachterList.contains(allergeneBeobachter) && automatVerwaltung.beobachterList.contains(capacityBeobachter) );

        } catch (AutomatException e) {
            fail();
        }
    }

    @Test
    void beobachterAbmelden() {
        try {
            AutomatVerwaltung automatVerwaltung = new AutomatVerwaltung(5);
            Beobachter allergeneBeobachter = mock(AllergeneBeobachter.class);
            Beobachter capacityBeobachter = mock(KapazitaetBeobachter.class);
            automatVerwaltung.meldeAn(allergeneBeobachter);
            automatVerwaltung.meldeAn(capacityBeobachter);

            automatVerwaltung.meldeAb(allergeneBeobachter);

            Assertions.assertFalse(automatVerwaltung.beobachterList.contains(allergeneBeobachter));

        } catch (AutomatException e) {
            fail();
        }
    }

    @Test
    void beobachternBenachrichtigen() {
        try {
            AutomatVerwaltung automatVerwaltung = new AutomatVerwaltung(5);
            Beobachter beobachter = mock(AllergeneBeobachter.class);
            automatVerwaltung.meldeAn(beobachter);

            automatVerwaltung.benachrichtige();

            verify(beobachter, atLeastOnce()).aktualisiere();

        } catch (AutomatException e) {
            fail();
        }
    }
}
