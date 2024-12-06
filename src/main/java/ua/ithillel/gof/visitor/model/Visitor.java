package ua.ithillel.gof.visitor.model;

public interface Visitor {
   void visitPersonCollection(PersonCollection personCollection);
   void visitBookCollection(BookCollection bookCollection);
}
