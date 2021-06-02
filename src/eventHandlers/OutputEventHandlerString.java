package eventHandlers;


import eventListener.OutputEventListenerStringImpl;
import events.OutputEventString;

import java.util.LinkedList;
import java.util.List;

public class OutputEventHandlerString {

    private final List<OutputEventListenerStringImpl> listenerList = new LinkedList<>();
    public void add(OutputEventListenerStringImpl listener) {
        this.listenerList.add(listener);
    }
    public void remove(OutputEventListenerStringImpl listener) {
        this.listenerList.remove(listener);
    }
    public void handle(OutputEventString event) { for (OutputEventListenerStringImpl listener : listenerList) { listener.onOutputEvent(event); }
    }

}
