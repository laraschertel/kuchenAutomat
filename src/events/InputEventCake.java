package events;

import automat.Cake;

public class InputEventCake extends InputEvent {
    private Cake kuchen;

    public InputEventCake(Object source, String modus, Cake kuchen) {

        super(source, modus);
        this.kuchen = kuchen;

    }

    public Cake getKuchen() {
        return this.kuchen;
    }
}
