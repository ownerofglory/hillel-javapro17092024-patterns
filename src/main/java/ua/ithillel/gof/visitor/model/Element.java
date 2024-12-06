package ua.ithillel.gof.visitor.model;

public interface Element {
    void accept(Visitor visitor);
}
