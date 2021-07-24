package simulation;

import automat.AutomatException;
import automat.AutomatPlaceHolder;
import automat.Beobachter;

public class KuchenBeobachter implements Beobachter {
    private AutomatPlaceHolder automatPlaceHolder;

    private int alterZustand = 0;

    public KuchenBeobachter(AutomatPlaceHolder automatPlaceHolder) {
        this.automatPlaceHolder = automatPlaceHolder;
        this.automatPlaceHolder.getAutomat().meldeAn(this);
    }

    @Override
    public void aktualisiere(){
        try {
            int neuerZustand = automatPlaceHolder.getAutomat().getAnzahlKuchenImAutomat();
            if (neuerZustand > alterZustand) {
                System.out.println("kuchen hingefügt");
            } else if (neuerZustand < alterZustand) {
                System.out.println("kuchen gelöscht");

            }
            alterZustand = neuerZustand;
        }catch(AutomatException e){
            System.out.println("Fehler bei Aktualisieren");;
        }

    }
}
