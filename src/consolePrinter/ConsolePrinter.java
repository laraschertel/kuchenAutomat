package consolePrinter;

import automat.*;
import events.OutputEventCollection;
import events.OutputEventString;

public class ConsolePrinter {

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

        String hersteller = cake.getHersteller().getName();
        String preis = cake.getPreis().toString();
        int naehrwert = cake.getNaehrwert();
        String allergene = cake.getAllergene().toString();
        long duration = cake.getHaltbarkeit().toDays();
        String inspektion = cake.getInspektionsdatum().toString();
        int fachnummer = cake.getFachnummer();
        String einfügendatum = cake.getEinfuegedatum().toString();

        System.out.print("Hersteller: "  + hersteller + ", preis: " +  preis + ", Naehrwert: " + naehrwert +   ", Allergene: " + allergene + ", Haltbarkeit: " + duration + ", Inpektionsdatum:  " + inspektion + ", Fachnummer: " + fachnummer + ", Einfügedatum: " + einfügendatum);

        if(cake instanceof ObstkuchenImpl){
            String obstsorte = ((ObstkuchenImpl) cake).getObstsorte();
            System.out.print(", Obtsorte: " + obstsorte);
        } else if(cake instanceof KremkuchenImpl){
            String kremsorte = (((KremkuchenImpl) cake).getKremsorte());
            System.out.print( ", Kremsorte:  " + kremsorte);
        } else if(cake instanceof ObsttorteImpl) {
            String obstsorte = ((ObsttorteImpl) cake).getObstsorte();
            String kremsorte = (((ObsttorteImpl) cake).getKremsorte());
            System.out.print(", Obtsorte: " + obstsorte + ", Kremsorte:  " + kremsorte);
        }

        System.out.println();

    }

    public void printString(OutputEventString outputEventString){
        System.out.println(outputEventString.getString());
    }

}
