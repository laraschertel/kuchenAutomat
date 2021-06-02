package automat;

public class HerstellerImpl implements Hersteller {

    private String hesterllerName;

    public HerstellerImpl(String hesterllerName){
        this.hesterllerName = hesterllerName;
    }

    @Override
    public String getName() {
        return this.hesterllerName;
    }
}
