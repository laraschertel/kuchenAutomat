package automat;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class ObstkuchenImpl extends Cake implements Obstkuchen {

    private String obstsorte;

    public ObstkuchenImpl(Hersteller hersteller, BigDecimal preis, int naehrwert, Duration haltbarkeit, Collection<Allergen> allergen, Date inspektionsdatum, int fachnummer, Date einfügedatum, String obstsorte) {
        super(hersteller, preis, naehrwert, haltbarkeit, allergen, inspektionsdatum, fachnummer,  einfügedatum );
        this.obstsorte = obstsorte;
    }

    @Override
    public String getObstsorte() {
        return this.obstsorte;
    }
}
