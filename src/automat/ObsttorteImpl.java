package automat;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class ObsttorteImpl extends Cake implements Obsttorte{

    private String kremsorte;
    private String obstsorte;


    public ObsttorteImpl(Hersteller hersteller, BigDecimal preis, int naehrwert, Duration haltbarkeit, Collection<Allergen> allergen, Date inspektionsdatum, int fachnummer, Date einfügedatum, String obstsorte, String kremsorte) {
        super(hersteller, preis, naehrwert, haltbarkeit, allergen, inspektionsdatum, fachnummer,  einfügedatum);
        this.obstsorte = obstsorte;
        this.kremsorte = kremsorte;
    }

    @Override
    public String getKremsorte() {
        return this.obstsorte;
    }

    @Override
    public String getObstsorte() {
        return this.kremsorte;
    }
}
