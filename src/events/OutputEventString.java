package events;

public class OutputEventString extends OutputEvent {
    private String string;

    public OutputEventString(Object source,String text) {
        super(source, text);
        this.string = text;

    }
    public String getString(){
        return this.string = string;
    }

}
