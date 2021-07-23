package gui;

import javafx.beans.property.StringProperty;

public class KuchenTabelle {



    private String herstellerName;
    private Integer fachnummer;
    private String inspektionsdatum;
    private Long haltbarkeit;
    private String kuchentyp;

    public KuchenTabelle(String herstellerName, String kuchentyp, Integer fachnummer, String inspektionsdatum, Long haltbarkeit){
        this.fachnummer = fachnummer;
        this.kuchentyp = kuchentyp;
        this.herstellerName = herstellerName;
        this.haltbarkeit = haltbarkeit;
        this.inspektionsdatum = inspektionsdatum;
    }

    public String getHerstellerName() {
        return herstellerName;
    }

    public Integer getFachnummer() {
        return fachnummer;
    }

    public String getInspektionsdatum() {
        return inspektionsdatum;
    }

    public Long getHaltbarkeit() {
        return haltbarkeit;
    }

    public String getKuchentyp(){return kuchentyp;}

}
