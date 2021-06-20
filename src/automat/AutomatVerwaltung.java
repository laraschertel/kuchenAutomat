package automat;

import observerPattern.Beobachter;
import observerPattern.Subjekt;

import java.io.Serializable;
import java.util.*;


public class AutomatVerwaltung implements Subjekt, Serializable {

    private Cake[] cakeList;
    private HashSet<Hersteller> herstellerList = new HashSet<>();
    private int capacity;
    private final List<Beobachter> beobachterList = new LinkedList<>();


    public AutomatVerwaltung(int capacity) throws AutomatException {
        this.capacity = capacity;

        if(capacity > 0) {
            this.cakeList = new Cake[capacity];

        } else{
            throw new AutomatException("Capacity muss großer als 0 sein");
        }
    }

    public synchronized void addHersteller(Hersteller hersteller) throws AutomatException {

            if (herstellerExistiert(hersteller.getName())) {
                throw new AutomatException("Hersteller kannt nicht zwei mal eingefügt werden");
            } else {
                herstellerList.add(hersteller);
            }
            this.benachrichtige();

    }

    public synchronized void removeHersteller(String herstellerName) throws AutomatException {
            for (Hersteller her : herstellerList) {
                if (her.getName().equalsIgnoreCase(herstellerName)) {
                    herstellerList.remove(her);
                    break;
                }
        }
        this.benachrichtige();
    }

    //TODO: original liste nicht rausgeben!!
    public HashSet<Hersteller> getHerstellerList(){
        return this.herstellerList;
    }


    //TODO: original liste nicht rausgeben!!
    public Cake[] getCakeList() {
        return this.cakeList;
    }

    public synchronized void addKuchen(Cake cake) throws AutomatException, InterruptedException {

        boolean herstellerExistiert = false;

            for (Hersteller h : herstellerList) {
                if (cake.getHersteller().getName().equals(h.getName())) {
                    int fachnummer = getAvailableFachnummer();
                    cakeList[fachnummer] = cake;
                    cake.setFachnummer(fachnummer);
                    herstellerExistiert = true;
                    this.benachrichtige();
                }

            }
            if (!herstellerExistiert) {
                throw new AutomatException("Kuchen kann nicht eingefügt werden, da der eingegebene Hersteller nicht bekannt ist");
            }

        }


    public synchronized void removeKuchen(int fachnummer) throws AutomatException, InterruptedException {
        if (cakeList[fachnummer] == null) {
            throw new AutomatException("Kuchen nicht gefunden");
        } else {
            cakeList[fachnummer] = null;
            this.benachrichtige();
        }
    }

    public synchronized HashMap<Hersteller, Integer> getHerstellerListMitKuchenAnzahl() {

        HashMap<Hersteller, Integer> kuchenProHersteller = new HashMap<>();

            for (Hersteller hersteller : herstellerList) {
                int counter = 0;
                for (Cake cake : cakeList) {
                    if (cake != null && cake.getHersteller().equals(hersteller)) {
                        counter++;
                    }
                }

                kuchenProHersteller.put(hersteller, counter);
            }
        return kuchenProHersteller;
    }

    public synchronized List<Cake> getAlleKuchenEinesTyps(String kuchenTyp) throws AutomatException {

        List<Cake> alleKuchenEinesTyps = new LinkedList<>();
        {
            if (kuchenTyp == "Kremkuchen") {
                for (Cake cake : cakeList) {
                    if (cake instanceof KremkuchenImpl) {
                        alleKuchenEinesTyps.add(cake);
                    }
                }
            } else if (kuchenTyp == "Obstkuchen") {
                for (Cake cake : cakeList) {
                    if (cake instanceof ObstkuchenImpl) {
                        alleKuchenEinesTyps.add(cake);
                    }
                }
            } else if (kuchenTyp == "Obsttorte") {
                for (Cake cake : cakeList) {
                    if (cake instanceof ObsttorteImpl) {
                        alleKuchenEinesTyps.add(cake);
                    }
                }
            } else {
                throw new AutomatException("Der eingegebene Kuchentyp existiert nicht");
            }
        }
        return alleKuchenEinesTyps;

    }

   public synchronized int getAvailableFachnummer() throws AutomatException {
       int fachnummer = -1;
       for (int i = 0; i < this.cakeList.length; i++) {
           if (this.cakeList[i] == null) {
               fachnummer = i;
               break;
           }
       }
       if (fachnummer > -1) {
           return fachnummer;
       } else {
           throw new AutomatException("Automat is full");
       }

    }

    public synchronized int getAnzahlKuchenImAutomat() throws AutomatException {
        int kuchen = 0;

        for (int i = 0; i < cakeList.length; i++) {
            if (this.cakeList[i] != null) {
                kuchen++;
            }
        }
        return kuchen;

    }

   public void inspectAutomat() {

        for(Cake cake : cakeList){
            if(cake != null){
                cake.setInspektionsdatum(new Date());
            }
        }
    }

    public synchronized void inspectCake(int fachnummer) throws AutomatException {
        for(Cake cake : cakeList){
            if(cake!= null && cake.getFachnummer() == fachnummer){
                cake.setInspektionsdatum(new Date());
                this.benachrichtige();
            }
        }
    }



    public EnumSet<Allergen> getVorhandeneAllergene() {
        EnumSet<Allergen> vorhandeneAllergenList = EnumSet.noneOf(Allergen.class);
        synchronized (this) {
            for (Cake cake : this.cakeList) {
                if (cake != null) {
                    for (Allergen allergene : cake.getAllergene()) {
                        vorhandeneAllergenList.add(allergene);
                    }
                }
            }
        }

        return vorhandeneAllergenList;
    }

    public EnumSet<Allergen> getNichtVorhandeneAllergene() {
        EnumSet<Allergen> nichtVorhandeneAllergenList = EnumSet.allOf(Allergen.class);

        synchronized (this) {
            for (Cake cake : this.cakeList) {
                if (cake != null) {
                    for (Allergen allergene : cake.getAllergene()) {
                        nichtVorhandeneAllergenList.remove(allergene);
                    }
                }
            }
        }

        return nichtVorhandeneAllergenList;
    }


    private boolean herstellerExistiert(String herstellerName) {

        boolean herstellerExistiert = false;

        for (Hersteller her : herstellerList) {
            if (her.getName().equalsIgnoreCase(herstellerName)) {
                herstellerExistiert = true;
                break;
            }
        }
        return herstellerExistiert;
    }

    @Override
    public void meldeAn(Beobachter beobachter) {
        this.beobachterList.add(beobachter);
    }

    @Override
    public void meldeAb(Beobachter beobachter) {
        this.beobachterList.remove(beobachter);
    }

    @Override
    public void benachrichtige() throws AutomatException {
        for (Beobachter beobachter : this.beobachterList) {
            beobachter.aktualisiere();
        }
    }


}
