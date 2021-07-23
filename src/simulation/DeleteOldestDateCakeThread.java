package simulation;

import automat.AutomatException;
import automat.AutomatVerwaltung;
import automat.Cake;

import java.util.Date;

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
