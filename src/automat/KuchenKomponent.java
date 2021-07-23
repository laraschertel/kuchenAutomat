package automat;


import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

public interface KuchenKomponent extends Kuchen, Verkaufsobjekt, Serializable {
    Kuchentyp getKuchentyp();
    Date getEinfuegedatum();
    String getName();
    Duration getVerbliebeneHaltbarkeit();
    void setInspektionsdatum(Date date);
    void setFachnummer(int fachnummer);
    void setEinfuegeDatum(Date date);
}
