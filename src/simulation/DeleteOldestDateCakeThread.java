package simulation;

import automat.AutomatException;

public class DeleteOldestDateCakeThread extends Thread {

    AutomatVerwaltungSimulation automatSimulation;

    public DeleteOldestDateCakeThread(AutomatVerwaltungSimulation automatVerwaltungSimulation) {
        this.automatSimulation = automatVerwaltungSimulation;
    }

    public void run(){
         while (true) {
            synchronized (this.automatSimulation) {
                try {
                    if(!this.automatSimulation.isEmpty()) {
                        automatSimulation.deleteOldestInpectedKuchen();
                    }
                } catch (AutomatException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }



    }

}
