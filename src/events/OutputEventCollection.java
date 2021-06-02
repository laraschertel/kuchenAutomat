package events;

import java.util.Collection;

public class OutputEventCollection extends OutputEvent {
    private final Collection collection;

    public OutputEventCollection(Object source, String text, Collection collection) {
        super(source, text);
        this.collection = collection;
    }

    public Collection getCollection(){
        return this.collection;
    }
}
