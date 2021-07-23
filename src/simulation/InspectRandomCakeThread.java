package simulation;

import automat.AutomatException;
import automat.AutomatVerwaltung;

import java.util.Date;

public class InspectRandomCakeThread extends Thread {
    AutomatVerwaltungSimulation automatSimulation;

    public InspectRandomCakeThread(AutomatVerwaltungSimulation automatVerwaltungSimulation) {
        this.automatSimulation = automatVerwaltungSimulation;
    }

    public void run() {

        while (true) {
                try {
                    if(!this.automatSimulation.isEmpty()) {
                        automatSimulation.inspectRandomKuchen();
                    }

                } catch (AutomatException | InterruptedException e) {
                    e.printStackTrace();


            }
        }
    }

}
