package automat;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public abstract class Cake implements Verkaufsobjekt, Kuchen {

    private Hersteller hersteller;
    private Collection<Allergen> allergen;
    private int naehrwert;
    private Duration haltbarkeit;
    private BigDecimal preis;
    private Date inspektionsdatum;
    private int fachnummer;
    private Date einfügedatum;

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

    public void setFachnummer(int fachnummer) {
        this.fachnummer = fachnummer;

    }

    public void setInspektionsdatum(Date date) {
        this.inspektionsdatum = date;
    }

    public Date getEinfuegedatum() {
        return this.einfügedatum;
    }

    public String toString() {
        String hersteller = this.getHersteller().getName();
        String preis = this.getPreis().toString();
        int naehrwert = this.getNaehrwert();
        String allergene = this.getAllergene().toString();
        long duration = this.getHaltbarkeit().toDays();
        String inspektion = this.getInspektionsdatum().toString();
        int fachnummer = this.getFachnummer();
        String einfügendatum = this.getEinfuegedatum().toString();

        String cakeString = fachnummer + 1 + ". Hersteller: " + hersteller + ", preis: " + preis + ", Naehrwert: " + naehrwert + ", Allergene: " + allergene + ", Haltbarkeit in Tagen: " + duration + ", Inpektionsdatum:  " + inspektion + ", Fachnummer: " + fachnummer + ", Einfügedatum: " + einfügendatum;

        if (this instanceof ObstkuchenImpl) {
            String obstsorte = ((ObstkuchenImpl) this).getObstsorte();
            cakeString += ", Obtsorte: " + obstsorte;
        } else if (this instanceof KremkuchenImpl) {
            String kremsorte = (((KremkuchenImpl) this).getKremsorte());
            cakeString += ", Kremsorte:  " + kremsorte;
        } else if (this instanceof ObsttorteImpl) {
            String obstsorte = ((ObsttorteImpl) this).getObstsorte();
            String kremsorte = (((ObsttorteImpl) this).getKremsorte());
            cakeString += ", Obtsorte: " + obstsorte + ", Kremsorte:  " + kremsorte;
        }
        return cakeString;

    }
}
