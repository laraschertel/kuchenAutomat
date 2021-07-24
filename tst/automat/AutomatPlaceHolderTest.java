package automat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AutomatPlaceHolderTest {

    @Test
    void getAutomat() {
        AutomatVerwaltung automatVerwaltungMock = mock(AutomatVerwaltung.class);
        AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automatVerwaltungMock);

        Assertions.assertEquals(automatVerwaltungMock, automatPlaceHolder.getAutomat());
    }

    @Test
    void setAutomat() {
        AutomatVerwaltung automatVerwaltungMock = mock(AutomatVerwaltung.class);
        AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(null);

        automatPlaceHolder.setAutomat(automatVerwaltungMock);

        Assertions.assertEquals(automatVerwaltungMock, automatPlaceHolder.getAutomat());
    }
}