package simulation;

import automat.AutomatException;

public class AddCakeThread extends Thread {
    AutomatVerwaltungSimulation automatSimulation;

    public AddCakeThread(AutomatVerwaltungSimulation automatVerwaltungSimulation) {
        this.automatSimulation = automatVerwaltungSimulation;
    }

    public void run() {
        while (true) {
            synchronized (this.automatSimulation) {
                try {
                    if(!this.automatSimulation.isFull()) {
                        automatSimulation.addRandomKuchen();
                        System.out.println("(SIMULATION) kuchen hingefügt");
                    }

                } catch (AutomatException | InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }
    }
}
