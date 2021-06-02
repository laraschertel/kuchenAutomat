package observerPattern;

import automat.AutomatException;

public interface Subjekt {
    void meldeAn(Beobachter beobachter);
    void meldeAb(Beobachter beobachter);
    void benachrichtige() throws AutomatException;
}
