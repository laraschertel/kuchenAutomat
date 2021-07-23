package automat;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class KuchenBoden implements KuchenKomponent {

    private final Kuchentyp kuchentyp;
    private final Hersteller hersteller;
    private final Collection<Allergen> allergen;
    private final int naehrwert;
    private final Duration haltbarkeit;
    private final BigDecimal preis;
    private Date inspektionsdatum;
    private int fachnummer;
    private  Date einfügedatum;


    public KuchenBoden(Kuchentyp kuchentyp, Hersteller hersteller, BigDecimal preis, int naehrwert, Duration haltbarkeit, Collection<Allergen> allergen) {
        this.kuchentyp = kuchentyp;
        this.hersteller = hersteller;
        this.allergen = allergen;
        this.naehrwert = naehrwert;
        this.haltbarkeit = haltbarkeit;
        this.preis = preis;

    }

    @Override
    public Kuchentyp getKuchentyp() {
        return this.kuchentyp;
    }

    @Override
    public Date getEinfuegedatum() {
        return this.einfügedatum;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setInspektionsdatum(Date date) {
        this.inspektionsdatum = date;
    }

    @Override
    public void setFachnummer(int fachnummer) {
        this.fachnummer = fachnummer;
    }

    @Override
    public void setEinfuegeDatum(Date date) {
        this.einfügedatum = date;
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
        return this.fachnummer ;
    }

    @Override
    public Duration getVerbliebeneHaltbarkeit(){
        long diff = new Date().getTime() - this.einfügedatum.getTime();
        diff = (diff / (1000 * 60 * 60 * 24));
        return this.haltbarkeit.minusDays(diff);
    }

    public String toString(){
       return fachnummer +  this.getFachnummer() +". Kuchentyp: " + this.getKuchentyp() +" Hersteller: " + this.getHersteller().getName() + ", preis: " + this.getPreis().toString() + ", Naehrwert: " + this.getNaehrwert() + ", Allergene: " + this.getAllergene().toString() +  ", Haltbarkeit in Tagen: "
               + this.getVerbliebeneHaltbarkeit().toDays() + ", Inpektionsdatum:  " + this.getInspektionsdatum()+ " Einfügedatum: " + this.getEinfuegedatum();
    }
}
