package ua.ithillel.gof.visitor.model;

import java.util.Collection;
import java.util.List;

public class PersonCollection implements Element {
    private List<Person> persons;

    public PersonCollection(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitPersonCollection(this);
    }
}
