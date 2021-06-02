package eventHandlers;

import automat.AutomatException;
import eventListener.InputEventListenerCakeImpl;
import events.InputEventCake;

import java.util.LinkedList;
import java.util.List;

public class InputEventHandlerCake{

    List<InputEventListenerCakeImpl> listenerList = new LinkedList<>();
    public void add(InputEventListenerCakeImpl listener) {
        this.listenerList.add(listener);
    }
    public void remove(InputEventListenerCakeImpl listener) {
        this.listenerList.remove(listener);
    }
    public void handle(InputEventCake event) throws AutomatException {for (InputEventListenerCakeImpl listener : listenerList) listener.onInputEvent(event);}
}

