package eventHandlers;

import automat.AutomatException;
import eventListener.InputEventListenerStringImpl;
import events.InputEventString;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class InputEventHandlerString {
    private List<InputEventListenerStringImpl> listenerList = new LinkedList<>();
    public void add(InputEventListenerStringImpl listener) {
        this.listenerList.add(listener);
    }
    public void remove(InputEventListenerStringImpl listener) {
        this.listenerList.remove(listener);
    }
    public void handle(InputEventString event) throws AutomatException, IOException, InterruptedException {for (InputEventListenerStringImpl listener : listenerList) listener.onInputEvent(event);}
}
