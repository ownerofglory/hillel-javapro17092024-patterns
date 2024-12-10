package ua.ithillel.solid.segregation;

import java.io.OutputStream;

public interface IExcelExporter {
    <T>  void exportExcel(T[] data, OutputStream outputStream);
}
