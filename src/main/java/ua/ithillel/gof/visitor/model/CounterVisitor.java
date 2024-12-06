package ua.ithillel.gof.visitor.model;

public class CounterVisitor implements Visitor {
    @Override
    public void visitPersonCollection(PersonCollection personCollection) {
        System.out.println("Person collection: " + personCollection.getPersons().size());
    }

    @Override
    public void visitBookCollection(BookCollection bookCollection) {
        System.out.println("Book collection: " + bookCollection.getBooks().size());
    }
}
