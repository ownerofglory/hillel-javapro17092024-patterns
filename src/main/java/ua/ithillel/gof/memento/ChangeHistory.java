package ua.ithillel.gof.memento;

import java.util.Stack;

public class ChangeHistory {
    private Stack<Persistable> history = new Stack<>();

    public void save(Persistable persistable) {
        history.push(persistable);
    }

    public Persistable undo() {
        return history.pop();
    }
}
