package control;

import automat.KuchenKomponent;

public class OutputEventCakeList extends OutputEvent {

    private final KuchenKomponent[] kuchenKomponents;

    public OutputEventCakeList(Object source, String text, KuchenKomponent[] kuchenKomponents) {
        super(source, text);
        this.kuchenKomponents = kuchenKomponents;
    }

   public KuchenKomponent[] getKuchenKomponents(){
        return this.kuchenKomponents;
   }
}
