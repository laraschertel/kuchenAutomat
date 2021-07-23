package events;

import automat.Cake;
import automat.KuchenKomponent;

public class InputEventCake extends InputEvent {
    private KuchenKomponent kuchen;

    public InputEventCake(Object source, String modus, KuchenKomponent kuchen) {

        super(source, modus);
        this.kuchen = kuchen;

    }

    public KuchenKomponent getKuchen() {
        return this.kuchen;
    }
}
