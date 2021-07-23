import automat.*;
import io.JOSPersistence;

import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;

public class JOS {


    public static void main(String[] args) throws AutomatException, InterruptedException, IOException {

        AutomatVerwaltung automatSave= new AutomatVerwaltung(20);
        AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automatSave);


        JOSPersistence persistence = new JOSPersistence(automatPlaceHolder);


        Hersteller h1 = new HerstellerImpl("h1");
        Hersteller h2 = new HerstellerImpl("h2");

        automatSave.addHersteller(h1);
        automatSave.addHersteller(h2);

        KuchenKomponent obstkuchen = new KuchenBoden(Kuchentyp.OBSTKUCHEN, h1,BigDecimal.valueOf(3.5),200, Duration.ofDays(3), EnumSet.of(Allergen.Gluten));
        KuchenKomponent kremkuchen = new KuchenBoden(Kuchentyp.KREMKUCHEN, h1,BigDecimal.valueOf(3.5),200, Duration.ofDays(3), EnumSet.of(Allergen.Gluten));

        automatSave.addKuchen(obstkuchen);
        automatSave.addKuchen(kremkuchen);

        /////////////////////////////////////////////////// save automat ////////////////////////////////////////////////

        persistence.serialize();

        System.out.println(automatPlaceHolder.getAutomat());
        System.out.println(automatPlaceHolder.getAutomat().getCakeList());
        System.out.println(automatPlaceHolder.getAutomat().getCakeList()[0]);
        System.out.println(automatPlaceHolder.getAutomat().getCakeList()[1]);

        ///////////////////////////////////////////// load automat //////////////////////////////////////////////////////

       persistence.deserialize();
        System.out.println(automatPlaceHolder.getAutomat());
        System.out.println(automatPlaceHolder.getAutomat().getCakeList());
        System.out.println(automatPlaceHolder.getAutomat().getCakeList()[0]);
        System.out.println(automatPlaceHolder.getAutomat().getCakeList()[1]);



    }



}
