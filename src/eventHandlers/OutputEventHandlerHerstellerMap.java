package eventHandlers;

import eventListener.OutputEventListenerCollectionImpl;
import eventListener.OutputEventListenerHerstellerMap;
import events.OutputEventCollection;
import events.OutputEventHerstellerMap;

import java.util.LinkedList;
import java.util.List;

public class OutputEventHandlerHerstellerMap {

    private final List<OutputEventListenerHerstellerMap> listenerList = new LinkedList<>();
    public void add(OutputEventListenerHerstellerMap listener) {
        this.listenerList.add(listener);
    }
    public void remove(OutputEventListenerHerstellerMap listener) {
        this.listenerList.remove(listener);
    }
    public void handle(OutputEventHerstellerMap event) { for (OutputEventListenerHerstellerMap listener : listenerList) { listener.onOutputEvent(event); }
    }
}
