package automat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HerstellerImplTest {

    public final String HERSTELLERNAME = "hersteller";

    @Test
    void getName() {

        Hersteller hersteller = new HerstellerImpl(HERSTELLERNAME);
        assertEquals(HERSTELLERNAME, hersteller.getName());
    }
}