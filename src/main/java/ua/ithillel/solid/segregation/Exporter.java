package ua.ithillel.solid.segregation;

import java.io.OutputStream;

public interface Exporter {
     <T> void exportExcel(T[] data, OutputStream outputStream);
     <T> void exportHTML(T[] data, OutputStream outputStream);
}
