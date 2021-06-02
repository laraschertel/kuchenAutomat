package simulation;

import automat.AutomatException;

public class DeleteRandomCakeThread extends Thread {
    private AutomatVerwaltungSimulation automatSimulation;

    public DeleteRandomCakeThread(AutomatVerwaltungSimulation automatVerwaltungSimulation) {
        this.automatSimulation = automatVerwaltungSimulation;
    }

    public void run() {
        while (true) {
            synchronized (this.automatSimulation) {
                try {
                    if(!this.automatSimulation.isEmpty()) {
                        this.automatSimulation.deleteRandomKuchen();
                    }
                } catch (AutomatException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
