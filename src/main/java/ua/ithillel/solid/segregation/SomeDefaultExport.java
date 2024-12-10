package ua.ithillel.solid.segregation;

import ua.ithillel.gof.visitor.exporter.ExcelExporter;

import java.io.OutputStream;

public class SomeDefaultExport implements Exporter {
    @Override
    public <T> void exportExcel(T[] data, OutputStream outputStream) {
        new ExcelExporter().export(data, outputStream);
    }

    @Override
    public <T> void exportHTML(T[] data, OutputStream outputStream) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
