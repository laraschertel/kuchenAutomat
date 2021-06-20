package observerPattern;

import automat.AutomatException;

import java.io.Serializable;

public interface Beobachter extends Serializable{
    void aktualisiere() throws AutomatException;
}
