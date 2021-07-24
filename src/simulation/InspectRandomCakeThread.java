package simulation;

import automat.AutomatException;


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
