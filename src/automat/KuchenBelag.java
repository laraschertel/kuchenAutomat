package automat;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class KuchenBelag implements KuchenKomponent {

    private KuchenKomponent kuchen;
    private Collection<Allergen> allergen;
    private int naehrwert;
    private Duration haltbarkeit;
    private BigDecimal preis;
    private String name;

    public KuchenBelag(KuchenKomponent kuchenDekorator, BigDecimal preis, int naehrwert, Duration haltbarkeit, Collection<Allergen> allergen, String name) {
        this.allergen = allergen;
        this.naehrwert = naehrwert;
        this.haltbarkeit = haltbarkeit;
        this.preis = preis;
        this.name = name;
        this.kuchen = kuchenDekorator;
    }

    @Override
    public Kuchentyp getKuchentyp() {
        return this.kuchen.getKuchentyp();
    }

    @Override
    public Date getEinfuegedatum() {
        return this.kuchen.getEinfuegedatum();
    }

    @Override
    public void setInspektionsdatum(Date date) {
        this.kuchen.setInspektionsdatum(date);
    }

    @Override
    public void setFachnummer(int fachnummer) {
        this.kuchen.setFachnummer(fachnummer);
    }

    @Override
    public void setEinfuegeDatum(Date date) {
        this.kuchen.setEinfuegeDatum(date);
    }

    @Override
    public Hersteller getHersteller() {
        return this.kuchen.getHersteller();
    }

    @Override
    public Collection<Allergen> getAllergene() {
        this.allergen.addAll(this.kuchen.getAllergene());
        return this.allergen;
    }

    @Override
    public int getNaehrwert() {
        return this.kuchen.getNaehrwert() + this.naehrwert;
    }

    @Override
    public Duration getHaltbarkeit() {
        if(this.haltbarkeit.compareTo(this.kuchen.getHaltbarkeit()) < 0){
            return this.haltbarkeit;
        } else{
            return this.kuchen.getHaltbarkeit();
        }
    }

    @Override
    public Duration getVerbliebeneHaltbarkeit(){
        long diff = new Date().getTime() - this.kuchen.getEinfuegedatum().getTime();
        diff = (diff / (1000 * 60 * 60 * 24));
        return this.haltbarkeit.minusDays(diff);
    }

    @Override
    public BigDecimal getPreis() {
        return this.preis.add(this.kuchen.getPreis());
    }

    @Override
    public Date getInspektionsdatum() {
        return this.kuchen.getInspektionsdatum();
    }

    @Override
    public int getFachnummer() {
        return this.kuchen.getFachnummer();
    }

    public String getName(){
        if(this.kuchen.getName() == null){
            return this.name;
        }else {
            return this.kuchen.getName() + " " + this.name;
        }

    }


    public String toString(){
        return  this.getFachnummer() +". Kuchentyp: " + this.getKuchentyp() +" Hersteller: " + this.getHersteller().getName() + ", preis: " + this.getPreis().toString() + ", Naehrwert: " + this.getNaehrwert() + ", Allergene: " + this.getAllergene().toString() +  ", Haltbarkeit in Tagen: "
                + this.getVerbliebeneHaltbarkeit().toDays() + ", Inpektionsdatum:  " + this.getInspektionsdatum()+ " Einfügedatum: " + this.getEinfuegedatum() + " Belage: " + this.getName();
    }
}
