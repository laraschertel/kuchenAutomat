package simulation;

import automat.AutomatException;

public class DeleteMoreCakesThread extends Thread {

    AutomatVerwaltungSimulation automatSimulation;

    public DeleteMoreCakesThread(AutomatVerwaltungSimulation automatVerwaltungSimulation) {
        this.automatSimulation = automatVerwaltungSimulation;
    }

    public void run(){
        while (true) {
            synchronized (this.automatSimulation) {
                try {
                    if(!this.automatSimulation.isEmpty()) {
                        automatSimulation.deleteBeliebigeKuchen();
                    }
                } catch (AutomatException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
