package control;

public class InputEventInteger extends InputEvent {
    private String modus;
    private Integer integer;


    public InputEventInteger(Object source, String modus, Integer integer) {

        super(source, modus);
        this.modus =modus;
        this.integer = integer;
    }


    public String getModus(){
        return this.modus;
    }

    public Integer getInteger(){
        return this.integer;
    }


}
