package ua.ithillel.gof.visitor.exporter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ua.ithillel.gof.visitor.anno.DataValue;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

public class ExcelExporter implements DataExporter {
    @Override
    public <T> void export(T[] data, OutputStream outputStream) {
        try {
            Class<?> componentType = data.getClass().getComponentType();


            String[] header = createHeader(componentType);

            Field[] declaredFields = componentType.getDeclaredFields();

            Workbook workbook = new XSSFWorkbook();
            String sheetName = componentType.getSimpleName();

            if (componentType.isAnnotationPresent(DataValue.class)) {
                DataValue dataValue = componentType.getAnnotation(DataValue.class);
                sheetName = dataValue.name();
            }

            Sheet sheet = workbook.createSheet(sheetName);

            // write header
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < header.length; i++) {
                headerRow.createCell(i).setCellValue(header[i]);
            }

            // write data
            int rowNum = 1;
            for (T item : data) {
                Row row = sheet.createRow(rowNum++);

                int cellNum = 0;
                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true); // because field is private
                    Object value = declaredField.get(item);

                    Cell cell = row.createCell(cellNum++);

                    Class<?> type = declaredField.getType();
                    if (type == String.class) {
                        cell.setCellValue(value.toString());
                    } else if (type == Integer.class || type == int.class) {
                        cell.setCellValue((int) value);
                    } else if (type == Double.class || type == double.class) {
                        cell.setCellValue((double) value);
                    } else if (type == Boolean.class || type == boolean.class) {
                        cell.setCellValue((boolean) value);
                    } else if (type == Long.class || type == long.class) {
                        cell.setCellValue((long) value);
                    } else if (type == Float.class || type == float.class) {
                        cell.setCellValue((float) value);
                    } else if (type == LocalDateTime.class) {
                        cell.setCellValue((LocalDateTime) value);
                    } else {
                        throw new RuntimeException("Unsupported field type: " + declaredField.getType());
                    }


                }
            }

            workbook.write(outputStream);

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> String[] createHeader(Class<T> type) {
        Field[] declaredFields = type.getDeclaredFields();
        String[] header = new String[declaredFields.length];

        for (int i = 0; i < declaredFields.length; i++) {
            String name = declaredFields[i].getName();
            if (declaredFields[i].isAnnotationPresent(DataValue.class)) {
                DataValue dataValue = declaredFields[i].getAnnotation(DataValue.class);
                name = dataValue.name();
            }
            header[i] = name;
        }

        return header;
    }
}
