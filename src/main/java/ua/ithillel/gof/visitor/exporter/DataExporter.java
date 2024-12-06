package ua.ithillel.gof.visitor.exporter;

import java.io.OutputStream;

public interface DataExporter {
    <T> void export(T[] data, OutputStream outputStream);
}
