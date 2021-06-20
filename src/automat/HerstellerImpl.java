package automat;

import java.io.Serializable;

public class HerstellerImpl implements Hersteller, Serializable {

    private String hesterllerName;

    public HerstellerImpl(String hesterllerName){
        this.hesterllerName = hesterllerName;
    }

    @Override
    public String getName() {
        return this.hesterllerName;
    }
}
