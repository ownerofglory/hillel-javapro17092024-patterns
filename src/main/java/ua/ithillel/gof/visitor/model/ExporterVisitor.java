package ua.ithillel.gof.visitor.model;

import ua.ithillel.gof.visitor.exporter.DataExporter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class ExporterVisitor implements Visitor {
    private DataExporter dataExporter;

    public ExporterVisitor(DataExporter dataExporter) {
        this.dataExporter = dataExporter;
    }

    @Override
    public void visitPersonCollection(PersonCollection personCollection) {
        try {
            dataExporter.export(personCollection.getPersons().toArray(), new FileOutputStream("persons.xlsx"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void visitBookCollection(BookCollection bookCollection) {
        try {
            dataExporter.export(bookCollection.getBooks().toArray(), new FileOutputStream("books.xlsx"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
