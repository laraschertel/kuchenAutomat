package consolePrinter;

import automat.*;
import events.OutputEventCollection;
import events.OutputEventString;

import java.io.Serializable;

public class ConsolePrinter implements Serializable {

    public void printHerstellerList(OutputEventCollection outputEventCollection) {

        Object[] objectsArray = outputEventCollection.getCollection().toArray();

        try {
            for (Object o : objectsArray) {
                Hersteller h = (Hersteller) o;
                System.out.println(h.getName());

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


    public void printKuchenList(OutputEventCollection outputEventCollection) {

        Object[] objectsArray = outputEventCollection.getCollection().toArray();

        try {
            for (Object o : objectsArray) {
               if(o != null){
                Cake cake = (Cake) o;
                printCake(cake);
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void printCake(Cake cake){

        System.out.println(cake.toString());


    }

    public void printString(OutputEventString outputEventString){
        System.out.println(outputEventString.getString());
    }

}
