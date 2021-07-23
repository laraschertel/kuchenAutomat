package gui;

public class HerstellerTabelle {

    public String getHerstellerName() {
        return herstellerName;
    }

    public Integer getKuchenAnzahl() {
        return kuchenAnzahl;
    }



    private String herstellerName;
    private Integer kuchenAnzahl;


    public HerstellerTabelle(String herstellerName, Integer kuchenAnzahl){
        this.kuchenAnzahl = kuchenAnzahl;
        this.herstellerName = herstellerName;

    }

}
