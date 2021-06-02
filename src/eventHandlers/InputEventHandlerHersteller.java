package eventHandlers;

import automat.AutomatException;
import eventListener.InputEventListenerHerstellerImpl;
import events.InputEventHersteller;

import java.util.LinkedList;
import java.util.List;

public class InputEventHandlerHersteller {

    private List<InputEventListenerHerstellerImpl> listenerList = new LinkedList<>();
    public void add(InputEventListenerHerstellerImpl listener) {
        this.listenerList.add(listener);
    }
    public void remove(InputEventListenerHerstellerImpl listener) {
        this.listenerList.remove(listener);
    }
    public void handle(InputEventHersteller event) throws AutomatException {for (InputEventListenerHerstellerImpl listener : listenerList) listener.onInputEvent(event);}
}
