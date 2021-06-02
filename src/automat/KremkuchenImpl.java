package automat;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class KremkuchenImpl extends Cake implements Kremkuchen {

    private String kremsorte;

    public KremkuchenImpl(Hersteller hersteller, BigDecimal preis, int naehrwert, Duration haltbarkeit, Collection<Allergen> allergen, Date inspektionsdatum, int fachnummer, Date einfügedatum, String kremsorte ) {
        super(hersteller, preis, naehrwert, haltbarkeit, allergen, inspektionsdatum, fachnummer, einfügedatum);

        this.kremsorte = kremsorte;
    }

    @Override
    public String getKremsorte() {
        return this.kremsorte;
    }
}
