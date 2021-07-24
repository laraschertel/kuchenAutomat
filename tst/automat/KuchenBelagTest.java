package automat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

class KuchenBelagTest {

    private static final Kuchentyp obstkuchentTyp = Kuchentyp.OBSTKUCHEN;
    private static final Kuchentyp kremkuchenTyp = Kuchentyp.KREMKUCHEN;
    private static final String HERSTELLERNAME = "hersteller1";
    private static final BigDecimal preis1 =  BigDecimal.valueOf(3.5);
    private static final int naehrwert1 = 200;
    private static final Duration haltbarkeit1 =  Duration.ofDays(3);
    private static final EnumSet<Allergen> allergene1 =  EnumSet.of(Allergen.Gluten);
    private static final BigDecimal preis2 =  BigDecimal.valueOf(0.5);
    private static final int naehrwert2 = 50;
    private static final Duration haltbarkeit2 =  Duration.ofDays(5);
    private static final EnumSet<Allergen> allergene2=  EnumSet.of(Allergen.Erdnuss);
    private static final String BANANA = "Banana";
    KuchenKomponent obstkuchen;
    KuchenKomponent o1;
    private static final Date datum = new Date();
    Hersteller h1;

    @BeforeEach
    public void setUp() {
        h1 = new HerstellerImpl(HERSTELLERNAME);
        obstkuchen = new KuchenBoden(obstkuchentTyp, h1, preis1, naehrwert1, haltbarkeit1, allergene1);
        obstkuchen.setEinfuegeDatum(datum);
        obstkuchen.setInspektionsdatum(datum);
        obstkuchen.setFachnummer(0);
        o1 = new KuchenBelag(obstkuchen, preis2, naehrwert2, haltbarkeit2, allergene2, BANANA);

    }

    @Test
    void getKuchentyp() {
        Assertions.assertEquals(obstkuchentTyp, o1.getKuchentyp());
    }

    @Test
    void getEinfuegedatum() {
        Assertions.assertEquals(datum, o1.getEinfuegedatum());
    }

    @Test
    void getName() {
        Assertions.assertEquals(BANANA, o1.getName());
    }

    @Test
    void setInspektionsdatum()  {
        Date date = new GregorianCalendar(2021, Calendar.JULY, 20).getTime();
        o1.setInspektionsdatum(date);

        Assertions.assertEquals(date, o1.getInspektionsdatum());
    }

    @Test
    void setFachnummer() {
        o1.setFachnummer(0);

        Assertions.assertEquals(0, o1.getFachnummer());
    }

    @Test
    void setEinfuegeDatum() {
        Date date = new GregorianCalendar(2021, Calendar.JULY, 20).getTime();
        o1.setEinfuegeDatum(date);

        Assertions.assertEquals(date, o1.getEinfuegedatum());
    }

    @Test
    void getHersteller() {
        Assertions.assertEquals(h1, o1.getHersteller());
    }

    @Test
    void getAllergene() {
        EnumSet<Allergen> allergens = EnumSet.noneOf(Allergen.class);
        allergens.addAll(allergene1);
        allergens.addAll(allergene2);

        Assertions.assertEquals(allergens, o1.getAllergene());
    }

    @Test
    void getNaehrwert() {
        Assertions.assertEquals(naehrwert1 + naehrwert2, o1.getNaehrwert());
    }

    @Test
    void getHaltbarkeit() {
        Assertions.assertEquals(haltbarkeit1, o1.getHaltbarkeit());
    }

    @Test
    void getPreis() {
        Assertions.assertEquals(preis1.add(preis2), o1.getPreis());
    }

    @Test
    void getInspektionsdatum() {
        Assertions.assertEquals(datum, o1.getInspektionsdatum());
    }

    @Test
    void getFachnummer() {
        Assertions.assertEquals(0, o1.getFachnummer());
    }

    @Test
    void testToString() {

        EnumSet<Allergen> allergens = EnumSet.noneOf(Allergen.class);
        allergens.add(Allergen.Gluten);
        Date date = new GregorianCalendar(2021, Calendar.JULY, 20).getTime();
        KuchenKomponent kuchenBoden = new KuchenBoden(Kuchentyp.KREMKUCHEN, new HerstellerImpl("h1"), BigDecimal.valueOf(0), 0, Duration.ofDays(1000), new HashSet<>());
        KuchenKomponent kuchenBelag = new KuchenBelag(kuchenBoden, BigDecimal.valueOf(2.40), 200, Duration.ofDays(10), allergens, "Butter" );
        kuchenBelag.setEinfuegeDatum(date);
        kuchenBelag.setInspektionsdatum(date);
        kuchenBelag.toString();
        String print =  "0. Kuchentyp: KREMKUCHEN Hersteller: h1, preis: 2.4, Naehrwert: 200, Allergene: [Gluten], Haltbarkeit in Tagen: 6, Inpektionsdatum:  Tue Jul 20 00:00:00 CEST 2021 Einfügedatum: Tue Jul 20 00:00:00 CEST 2021 Belage: Butter";

        Assertions.assertEquals(kuchenBelag.toString(),print);

    }
}