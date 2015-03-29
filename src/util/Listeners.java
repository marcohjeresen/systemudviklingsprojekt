package util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Listeners {

    private static Listeners listeners;
    private ArrayList<ActionListener> listenersList;

    /**
     * Constructor, creates a new object of the class.
     */
    private Listeners() {
        listenersList = new ArrayList<>();
    }

    /**
     * Method, creates a singleton of Listeners if it doesn't exist already.
     *
     * @return listeners.
     */
    public static Listeners getList() {
        if (listeners == null) {
            listeners = new Listeners();
        }
        return listeners;
    }

    public void addListener(ActionListener listener) {
        listenersList.add(listener);

    }

    public void notifyListeners(String event) {
        for (int i = 0; i < listenersList.size(); i++) {
            listenersList.get(i).actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, event));
        }
    }

}
