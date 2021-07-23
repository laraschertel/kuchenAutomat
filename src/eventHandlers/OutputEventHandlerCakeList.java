package eventHandlers;

import eventListener.OutputEventListenerCakeList;
import eventListener.OutputEventListenerCollectionImpl;
import events.OutputEventCakeList;
import events.OutputEventCollection;

import java.util.LinkedList;
import java.util.List;

public class OutputEventHandlerCakeList {

    private final List<OutputEventListenerCakeList> listenerList = new LinkedList<>();
    public void add(OutputEventListenerCakeList listener) {
        this.listenerList.add(listener);
    }
    public void remove(OutputEventListenerCakeList listener) {
        this.listenerList.remove(listener);
    }
    public void handle(OutputEventCakeList event) { for (OutputEventListenerCakeList listener : listenerList) { listener.onOutputEvent(event); }
    }
}
