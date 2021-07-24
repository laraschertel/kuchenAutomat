package automat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class KuchenBodenTest {

    private static final Kuchentyp obstkuchentTyp = Kuchentyp.OBSTKUCHEN;
    private static final Kuchentyp kremkuchenTyp = Kuchentyp.KREMKUCHEN;
    private static final String HERSTELLERNAME = "hersteller1";
    private static final BigDecimal preis1 =  BigDecimal.valueOf(3.5);
    private static final int naehrwert1 = 200;
    private static final Duration haltbarkeit1 =  Duration.ofDays(3);
    private static final EnumSet<Allergen> allergene1 =  EnumSet.of(Allergen.Gluten);
    KuchenKomponent obstkuchen;
    private static final Date einfuegedatum = new Date();
    Hersteller h1;

    @BeforeEach
    public void setUp() {
        h1 = new HerstellerImpl(HERSTELLERNAME);
        obstkuchen = new KuchenBoden(obstkuchentTyp, h1, preis1, naehrwert1, haltbarkeit1, allergene1);
        obstkuchen.setEinfuegeDatum(einfuegedatum);
        obstkuchen.setInspektionsdatum(einfuegedatum);
        obstkuchen.setFachnummer(0);
    }

    @Test
    void getKuchentyp() {
        Assertions.assertEquals(obstkuchentTyp, obstkuchen.getKuchentyp());
    }

    @Test
    void getEinfuegedatum() {
        Assertions.assertEquals(einfuegedatum, obstkuchen.getEinfuegedatum());
    }

    @Test
    void getName() {
        Assertions.assertEquals(null, obstkuchen.getName());
    }

    @Test
    void setInspektionsdatum() {
        Assertions.assertEquals(einfuegedatum, obstkuchen.getEinfuegedatum());
    }

    @Test
    void setFachnummer() {
        Assertions.assertEquals(0, obstkuchen.getFachnummer());
    }

    @Test
    void setEinfuegeDatum() {
        Assertions.assertEquals(einfuegedatum, obstkuchen.getEinfuegedatum());
    }

    @Test
    void getHersteller() {
        Assertions.assertEquals(h1, obstkuchen.getHersteller());
    }

    @Test
    void getAllergene() {
        Assertions.assertEquals(allergene1, obstkuchen.getAllergene());
    }

    @Test
    void getNaehrwert() {
        Assertions.assertEquals(naehrwert1, obstkuchen.getNaehrwert());
    }

    @Test
    void getHaltbarkeit() {
        Assertions.assertEquals(haltbarkeit1, obstkuchen.getHaltbarkeit());
    }

    @Test
    void getPreis() {
        Assertions.assertEquals(preis1, obstkuchen.getPreis());
    }

    @Test
    void getInspektionsdatum() {
        Assertions.assertEquals(einfuegedatum, obstkuchen.getEinfuegedatum());
    }

    @Test
    void getFachnummer() {
        Assertions.assertEquals(0, obstkuchen.getFachnummer());
    }

    @Test
    void getVerbliebeneHaltbarkeitGleicherTag() {
        Assertions.assertEquals(3, obstkuchen.getVerbliebeneHaltbarkeit().toDays());

    }

    @Test
    void testToString() {
        Date date = new GregorianCalendar(2021, Calendar.JULY, 20).getTime();
        KuchenKomponent kuchenBoden = new KuchenBoden(Kuchentyp.KREMKUCHEN, new HerstellerImpl("h1"), BigDecimal.valueOf(0), 0, Duration.ofDays(1000), new HashSet<>());

        kuchenBoden.setInspektionsdatum(date);
        kuchenBoden.setEinfuegeDatum(date);

        kuchenBoden.toString();
        String print =  "0. Kuchentyp: KREMKUCHEN Hersteller: h1, preis: 0, Naehrwert: 0, Allergene: [], Haltbarkeit in Tagen: 996, Inpektionsdatum:  Tue Jul 20 00:00:00 CEST 2021 Einfügedatum: Tue Jul 20 00:00:00 CEST 2021";

        Assertions.assertEquals(kuchenBoden.toString(),print);
    }
}