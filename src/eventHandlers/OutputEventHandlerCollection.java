package eventHandlers;


import eventListener.OutputEventListenerCollectionImpl;
import events.OutputEventCollection;

import java.util.LinkedList;
import java.util.List;

public class OutputEventHandlerCollection {

    private final List<OutputEventListenerCollectionImpl> listenerList = new LinkedList<>();
    public void add(OutputEventListenerCollectionImpl listener) {
        this.listenerList.add(listener);
    }
    public void remove(OutputEventListenerCollectionImpl listener) {
        this.listenerList.remove(listener);
    }
    public void handle(OutputEventCollection event) { for (OutputEventListenerCollectionImpl listener : listenerList) { listener.onOutputEvent(event); }
    }

}
