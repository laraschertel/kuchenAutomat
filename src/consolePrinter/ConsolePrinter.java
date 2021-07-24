package consolePrinter;

import automat.*;
import control.OutputEventCakeList;
import control.OutputEventCollection;
import control.OutputEventHerstellerMap;
import control.OutputEventString;

import java.io.Serializable;
import java.util.HashMap;

public class ConsolePrinter implements Serializable {

    public void printHerstellerHashMap(OutputEventHerstellerMap outputEventHerstellerMap) {

        HashMap<Hersteller, Integer> herstellerHashMap = outputEventHerstellerMap.getHerstellerHashMap();

        try {
            for (Hersteller hersteller : herstellerHashMap.keySet()) {
                String herstellername = hersteller.getName();
                String anzahlKuchen = herstellerHashMap.get(hersteller).toString();
                System.out.println(herstellername + ": " + anzahlKuchen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printAllergeneList(OutputEventCollection outputEventCollection){
        Object[] objectsArray = outputEventCollection.getCollection().toArray();

        try {
            for (Object o : objectsArray) {
                Allergen a = (Allergen) o;
                System.out.println(a.toString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void printKuchenList(OutputEventCakeList outputEventCakeList) {

        KuchenKomponent[] kuchenKomponents = outputEventCakeList.getKuchenKomponents();

        try {
            for (KuchenKomponent cake : kuchenKomponents) {
               if(cake != null){ System.out.println(cake.toString());
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void printString(OutputEventString outputEventString){
        System.out.println(outputEventString.getString());
    }

}
