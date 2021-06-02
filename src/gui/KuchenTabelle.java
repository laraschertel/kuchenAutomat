package gui;

import javafx.beans.property.StringProperty;

public class KuchenTabelle {

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


    private String herstellerName;
    private Integer fachnummer;
    private String inspektionsdatum;
    private Long haltbarkeit;

    public KuchenTabelle(String herstellerName, Integer fachnummer, String inspektionsdatum, Long haltbarkeit){
        this.fachnummer = fachnummer;
        this.herstellerName = herstellerName;
        this.haltbarkeit = haltbarkeit;
        this.inspektionsdatum = inspektionsdatum;
    }

}
