package automat;

import observerPattern.Beobachter;
import observerPattern.Subjekt;

import java.io.Serializable;
import java.util.*;


public class AutomatVerwaltung implements Subjekt, Serializable {

    private final KuchenKomponent[] cakeList;
    private final HashSet<Hersteller> herstellerList = new HashSet<>();
    private int capacity;
    transient List<Beobachter> beobachterList = new LinkedList<>();


    public AutomatVerwaltung(int capacity) throws AutomatException {
        this.capacity = capacity;

        if(capacity > 0) {
            this.cakeList = new KuchenKomponent[capacity];

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

    public HashSet<Hersteller> getHerstellerList(){

        HashSet<Hersteller> returnHerstellerList = this.herstellerList;

        return returnHerstellerList;
    }


    public KuchenKomponent[] getCakeList() {

        KuchenKomponent[] returnCakeList = this.cakeList;

        return returnCakeList;
    }

    public synchronized void addKuchen(KuchenKomponent cake) throws AutomatException, InterruptedException {

        boolean herstellerExistiert = false;

            for (Hersteller h : herstellerList) {
                if (cake.getHersteller().getName().equals(h.getName())) {
                    int fachnummer = getAvailableFachnummer();
                    cakeList[fachnummer] = cake;
                    cake.setFachnummer(fachnummer);
                    cake.setInspektionsdatum(new Date());
                    cake.setEinfuegeDatum(new Date());
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
                for (KuchenKomponent cake : cakeList) {
                    if (cake != null && cake.getHersteller().getName().equals(hersteller.getName())) {
                        counter++;
                    }
                }

                kuchenProHersteller.put(hersteller, counter);
            }
        return kuchenProHersteller;
    }


    public synchronized KuchenKomponent[] getAlleKuchenEinesTyps(String kuchenTyp){

        int counter = 0;

        for (KuchenKomponent cake : cakeList) {
            if (cake != null && cake.getKuchentyp().toString().equalsIgnoreCase(kuchenTyp)) {
                counter++;
            }
        }
        KuchenKomponent[] alleKuchenEinesTyps = new KuchenKomponent[counter];
        int j=0;
        for (int i=0; i < cakeList.length; i++) {
            if (cakeList[i] != null && cakeList[i].getKuchentyp().toString().equalsIgnoreCase(kuchenTyp)) {
                alleKuchenEinesTyps[j] = cakeList[i];
                j++;
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

        for(KuchenKomponent cake : cakeList){
            if(cake != null){
                cake.setInspektionsdatum(new Date());
            }
        }
    }

    public synchronized void inspectCake(int fachnummer) throws AutomatException {
        for(KuchenKomponent cake : cakeList){
            if(cake!= null && cake.getFachnummer() == fachnummer){
                cake.setInspektionsdatum(new Date());
                this.benachrichtige();
            }
        }
    }



    public EnumSet<Allergen> getVorhandeneAllergene() {
        EnumSet<Allergen> vorhandeneAllergenList = EnumSet.noneOf(Allergen.class);
        synchronized (this) {
            for (KuchenKomponent cake : this.cakeList) {
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
            for (KuchenKomponent cake : this.cakeList) {
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
    public void benachrichtige() throws AutomatException, NullPointerException {

            if (this.beobachterList != null) {
                for (Beobachter beobachter : this.beobachterList) {
                    beobachter.aktualisiere();
                }
            }



    }
}
