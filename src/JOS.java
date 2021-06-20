import automat.*;
import io.JOSPersistence;

import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;

public class JOS {


    public static void main(String[] args) throws AutomatException, InterruptedException, IOException {

        AutomatVerwaltung automatSave= new AutomatVerwaltung(20);
        AutomatPlaceHolder automatPlaceHolder = new AutomatPlaceHolder(automatSave);


        JOSPersistence persistence = new JOSPersistence(automatPlaceHolder);


        Hersteller h1 = new HerstellerImpl("h1");
        Hersteller h2 = new HerstellerImpl("h2");

        automatSave.addHersteller(h1);
        automatSave.addHersteller(h2);

        Cake obstkuchen = new ObstkuchenImpl(h1, BigDecimal.valueOf(3), 210, Duration.ofDays(2), Collections.singleton(Allergen.Erdnuss), new Date(), -1, new Date(), "berry");
        Cake kremkuchen = new KremkuchenImpl(h2, BigDecimal.valueOf(3), 210, Duration.ofDays(2), Collections.singleton(Allergen.Erdnuss), new Date(), -1, new Date(), "quark");

        automatSave.addKuchen(obstkuchen);
        automatSave.addKuchen(kremkuchen);

        /////////////////////////////////////////////////// save automat ////////////////////////////////////////////////

        persistence.saveAutomatJOS();

        System.out.println(automatPlaceHolder.getAutomat());
        System.out.println(automatPlaceHolder.getAutomat().getCakeList());
        System.out.println(automatPlaceHolder.getAutomat().getCakeList()[0]);
        System.out.println(automatPlaceHolder.getAutomat().getCakeList()[1]);

        ///////////////////////////////////////////// load automat //////////////////////////////////////////////////////

       persistence.loadAutomatJOS();
        System.out.println(automatPlaceHolder.getAutomat());
        System.out.println(automatPlaceHolder.getAutomat().getCakeList());
        System.out.println(automatPlaceHolder.getAutomat().getCakeList()[0]);
        System.out.println(automatPlaceHolder.getAutomat().getCakeList()[1]);

    }

}
