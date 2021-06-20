package automat;

import io.JOSPersistence;

import java.io.Serializable;

public class AutomatPlaceHolder implements Serializable {

    private AutomatVerwaltung automat;

    public AutomatPlaceHolder(AutomatVerwaltung automat){
        this.automat = automat;
    }

    public AutomatVerwaltung getAutomat() {
        return automat;
    }

    public void setAutomat(AutomatVerwaltung automat) {

        this.automat = automat;
    }



}
