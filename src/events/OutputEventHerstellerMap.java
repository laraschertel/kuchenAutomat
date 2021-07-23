package events;

import automat.Hersteller;

import java.util.Collection;
import java.util.HashMap;

public class OutputEventHerstellerMap extends OutputEvent {

    private final HashMap<Hersteller, Integer> herstellerHashMap ;

    public OutputEventHerstellerMap(Object source, String text,  HashMap<Hersteller, Integer> herstellerHashMap) {
        super(source, text);
        this.herstellerHashMap = herstellerHashMap;
    }

     public HashMap<Hersteller, Integer> getHerstellerHashMap(){
            return this.herstellerHashMap;
    }

}
