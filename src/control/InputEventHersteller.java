package control;

import automat.Hersteller;

public class InputEventHersteller extends InputEvent {
    private String modus;
    private Hersteller hersteller;

    public InputEventHersteller(Object source, String modus, Hersteller hersteller) {
        super(source, modus);
        this.modus =modus;
        this.hersteller = hersteller;
    }

    public String getModus(){
        return this.modus;
    }

    public Hersteller getHersteller() {
        return this.hersteller;
    }


}
