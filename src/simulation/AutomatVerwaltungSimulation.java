package simulation;

import automat.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class AutomatVerwaltungSimulation {
    private AutomatVerwaltung automat;
    private Hersteller[] randomHerstellerList = {new HerstellerImpl("h1"), new HerstellerImpl("h2"), new HerstellerImpl("h3")};
    private String[] randomObstsorteList = {"apple", "banane", "berry"};
    private String[] randomKremsorteList = {"butter", "quark", "chocolate"};


    public AutomatVerwaltungSimulation(AutomatVerwaltung automat) throws AutomatException {
         this.automat = automat;
    }

    public void addHerstellers() throws AutomatException {
        automat.addHersteller(new HerstellerImpl("h1"));
        automat.addHersteller(new HerstellerImpl("h2"));
        automat.addHersteller(new HerstellerImpl("h3"));

    }

    public synchronized void addRandomKuchen() throws AutomatException, InterruptedException {
       while(this.isFull()) wait();
        automat.addKuchen(getRandomCake());
        notifyAll();
    }


    public synchronized void deleteRandomKuchen() throws AutomatException, InterruptedException {
        while (this.isEmpty()) wait();
        int randomFachnummer = (int) (Math.random()*automat.getAnzahlKuchenImAutomat());
        if(this.automat.getCakeList()[randomFachnummer] != null) {
            automat.removeKuchen(randomFachnummer);
            System.out.println("(SIMULATION) Kuchen gelöscht");
        }
        notifyAll();
    }

    public synchronized void deleteOldestInpectedKuchen() throws AutomatException, InterruptedException, NullPointerException {
        while (this.isEmpty()) wait();
        Date oldestDate = new Date();
        int cakeOldestDate = 0;
        for(int i = 0; i < automat.getAnzahlKuchenImAutomat(); i++){
            if(this.automat.getCakeList()[i] != null && automat.getCakeList()[i].getInspektionsdatum().before(oldestDate)){
                oldestDate = automat.getCakeList()[i].getInspektionsdatum();
                cakeOldestDate = i;
            }
        }
        if(this.automat.getCakeList()[cakeOldestDate] != null) {
            automat.removeKuchen(cakeOldestDate);
            System.out.println("(SIMULATION) altester inspizierten Kuchen gelöscht");
        }

        notifyAll();
    }

    public synchronized void deleteBeliebigeKuchen() throws AutomatException, InterruptedException, NullPointerException {
        while (this.isEmpty()) wait();
        int anzahlKuchenZuLöschen = (int) (Math.random()*automat.getAnzahlKuchenImAutomat());
        for(int j = 0; j <= anzahlKuchenZuLöschen; j++){
        Date oldestDate = new Date();
        int cakeOldestDate = 0;
        for(int i = 0; i < automat.getAnzahlKuchenImAutomat(); i++){
            if(this.automat.getCakeList()[i] != null && automat.getCakeList()[i].getInspektionsdatum().before(oldestDate)){
                oldestDate = automat.getCakeList()[i].getInspektionsdatum();
                cakeOldestDate = i;
            }
        }
        if(this.automat.getCakeList()[cakeOldestDate] != null) {
            automat.removeKuchen(cakeOldestDate);
            System.out.println("(SIMULATION) " + j + ". Kuchen gelöscht");
        }
        }
        notifyAll();
    }

    public synchronized void inspectRandomKuchen() throws AutomatException,  InterruptedException {
       // while (this.isEmpty()) wait();
        int randomFachnummer = (int) (Math.random()*automat.getAnzahlKuchenImAutomat());
        if(this.automat.getCakeList()[randomFachnummer] != null) {
            automat.inspectCake(randomFachnummer);
            System.out.println("(SIMULATION) Kuchen inspected");
        }
        notifyAll();
    }


    public synchronized boolean isFull() throws AutomatException {
       return this.automat.getAnzahlKuchenImAutomat() == this.automat.getCakeList().length;
    }

    public synchronized boolean isEmpty() throws AutomatException {
       return this.automat.getAnzahlKuchenImAutomat() == 0;
    }


    private Cake getRandomCake() {
        int randomNum = 1 + (int) (Math.random() * (2));
        if(randomNum ==1) {
            return new ObstkuchenImpl(getRandomHersteller(), getRandomPreis(), getRandomNaehrwert(), getRandomHaltbarkeit(), getRandomAllergene(), getRandomDate(), -1, new Date(), getRandomObstsorte());
        }else {
            return new KremkuchenImpl(getRandomHersteller(), getRandomPreis(), getRandomNaehrwert(), getRandomHaltbarkeit(), getRandomAllergene(), getRandomDate(), -1, new Date(), getRandomKremsorte());
        }
    }

    private Hersteller getRandomHersteller() {
        return randomHerstellerList[(int) (randomHerstellerList.length * Math.random())];
    }

    private BigDecimal getRandomPreis(){
        return BigDecimal.valueOf(2 + (Math.random() * (3)));
    }

    private Collection<Allergen> getRandomAllergene() {
        return Collections.singleton(Allergen.Erdnuss);
    }

    private int getRandomNaehrwert(){
        return 150 + (int) (Math.random() * (200));

    }

    private Duration getRandomHaltbarkeit(){
        return Duration.ofDays(2 + (int) (Math.random() * (3)));

    }

    private String getRandomObstsorte() {
        return randomObstsorteList[(int) (randomObstsorteList.length * Math.random())];
    }

    private String getRandomKremsorte() {
        return randomKremsorteList[(int) (randomKremsorteList.length * Math.random())];
    }

    private Date getRandomDate(){
        Random rnd;
        long    ms;
        rnd = new Random();
        ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        return new Date(ms);
    }


}
