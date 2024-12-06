package ua.ithillel.gof.visitor.model;

import java.util.List;

public class BookCollection implements Element {
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public BookCollection(List<Book> books) {
        this.books = books;
    }


    @Override
    public void accept(Visitor visitor) {
        visitor.visitBookCollection(this);
    }
}
