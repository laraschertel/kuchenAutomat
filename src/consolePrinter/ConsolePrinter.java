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
        //TODO
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

        cake.toString();


    }

    public void printString(OutputEventString outputEventString){
        System.out.println(outputEventString.getString());
    }

}
