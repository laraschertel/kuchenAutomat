package control;

public class InputEventString extends InputEvent {

    private String modus;
    private String string;
    private Integer integer;


    public InputEventString(Object source, String modus, String string) {
        super(source, modus);
        this.modus =modus;
        this.string =string;
    }


    public String getModus(){
        return this.modus;
    }

    public String getString(){
        return this.string=string;
    }


}
