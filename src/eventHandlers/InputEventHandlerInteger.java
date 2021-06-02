package eventHandlers;

import automat.AutomatException;
import eventListener.InputEventListenerIntegerImpl;
import events.InputEventInteger;

import java.util.LinkedList;
import java.util.List;

public class InputEventHandlerInteger {
    private List<InputEventListenerIntegerImpl> listenerList = new LinkedList<>();
    public void add(InputEventListenerIntegerImpl listener) {
        this.listenerList.add(listener);
    }
    public void remove(InputEventListenerIntegerImpl listener) {
        this.listenerList.remove(listener);
    }
    public void handle(InputEventInteger event) throws AutomatException {for (InputEventListenerIntegerImpl listener : listenerList) listener.onInputEvent(event);}
}
