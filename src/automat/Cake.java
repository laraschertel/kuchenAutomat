package automat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public abstract class Cake implements Verkaufsobjekt, Kuchen, Serializable {

    private final Hersteller hersteller;
    private final Collection<Allergen> allergen;
    private final int naehrwert;
    private final Duration haltbarkeit;
    private final BigDecimal preis;
    private Date inspektionsdatum;
    private int fachnummer;
    private final Date einfügedatum;

    public Cake(Hersteller hersteller, BigDecimal preis, int naehrwert, Duration haltbarkeit, Collection<Allergen> allergen, Date inspektionsdatum, int fachnummer, Date einfügedatum) {
        this.hersteller = hersteller;
        this.allergen = allergen;
        this.naehrwert = naehrwert;
        this.haltbarkeit = haltbarkeit;
        this.preis = preis;
        this.inspektionsdatum = inspektionsdatum;
        this.einfügedatum = einfügedatum;
    }

    @Override
    public Hersteller getHersteller() {
        return this.hersteller;
    }

    @Override
    public Collection<Allergen> getAllergene() {
        return this.allergen;
    }

    @Override
    public int getNaehrwert() {
        return this.naehrwert;
    }

    @Override
    public Duration getHaltbarkeit() {
        return this.haltbarkeit;
    }

    public Duration getVerbliebeneHaltbarkeit(){
        long diff = new Date().getTime() - einfügedatum.getTime();
        diff = (diff / (1000 * 60 * 60 * 24));
        return this.haltbarkeit.minusDays(diff);
    }

    @Override
    public BigDecimal getPreis() {
        return this.preis;
    }

    @Override
    public Date getInspektionsdatum() {
        return this.inspektionsdatum;
    }

    @Override
    public int getFachnummer() {
        return this.fachnummer;
    }

    void setFachnummer(int fachnummer) {
        this.fachnummer = fachnummer;

    }

    public void setInspektionsdatum(Date date) {
        this.inspektionsdatum = date;
    }

    public Date getEinfuegedatum() {
        return this.einfügedatum;
    }

    /*

    public String toString() {
        String hersteller = this.getHersteller().getName();
        String preis = this.getPreis().toString();
        int naehrwert = this.getNaehrwert();
        String allergene = this.getAllergene().toString();
        long duration = this.getVerbliebeneHaltbarkeit().toDays();
        String inspektion = this.getInspektionsdatum().toString();
        int fachnummer = this.getFachnummer();
        String einfügendatum = this.getEinfuegedatum().toString();

        String cakeString = fachnummer +  this.getFachnummer() + ". Hersteller: " + this.getHersteller().getName() + ", preis: " + this.getPreis().toString() + ", Naehrwert: " + this.getNaehrwert() + ", Allergene: " + this.getAllergene().toString() + ", Haltbarkeit in Tagen: " + duration + ", Inpektionsdatum:  " + inspektion + ", Fachnummer: " + fachnummer + ", Einfügedatum: " + einfügendatum;

        if (this instanceof ObstkuchenOldImpl) {
            String obstsorte = ((ObstkuchenOldImpl) this).getObstsort();
            cakeString += ", Obtsorte: " + obstsorte;
        } else if (this instanceof KremkuchenOldImpl) {
            String kremsorte = (((KremkuchenOldImpl) this).getKremsort());
            cakeString += ", Kremsorte:  " + kremsorte;
        } else if (this instanceof ObsttorteOldImpl) {
            String obstsorte = ((ObsttorteOldImpl) this).getObstsort();
            String kremsorte = (((ObsttorteOldImpl) this).getObstsort());
            cakeString += ", Obtsorte: " + obstsorte + ", Kremsorte:  " + kremsorte;
        }
        return cakeString;

    }

     */


}
