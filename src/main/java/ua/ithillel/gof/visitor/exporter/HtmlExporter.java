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

public class HtmlExporter implements DataExporter {
    private static String HTML_TEMPLATE = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>HEAD_TITLE</title>
            </head>
            <body>
                <h1>HEAD_TITLE</h1>
                <table>
                    <thead>
                        <tr>
                            HEAD_ROW
                        </tr>
                    </thead>
                    <tbody>
                        DATA_ROWS
                    </tbody>
                </table>
            </body>
            </html>
            """;

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

            String htmlDoc = HTML_TEMPLATE.replaceAll("HEAD_TITLE", sheetName);

            Sheet sheet = workbook.createSheet(sheetName);



            // write header
            Row headerRow = sheet.createRow(0);
            String headRow = "";
            for (int i = 0; i < header.length; i++) {
               headRow += String.format("<th>%s</th>", header[i]);
            }

            htmlDoc = htmlDoc.replaceAll("HEAD_ROW", headRow);

            // write data
            int rowNum = 1;
            String strData = "";
            for (T item : data) {
                String dataRow = "<tr>\n";
                Row row = sheet.createRow(rowNum++);

                int cellNum = 0;
                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true); // because field is private
                    Object value = declaredField.get(item);

                    Cell cell = row.createCell(cellNum++);

//                    dataRow += String.format("<td>%s</td>", value);

                    Class<?> type = declaredField.getType();
                    if (type == String.class) {
                        dataRow += String.format("<td>%s</td>", value);
                    } else if (type == Integer.class || type == int.class) {
                        dataRow += String.format("<td>%s</td>", value);
                    } else if (type == Double.class || type == double.class) {
                        dataRow += String.format("<td>%s</td>",  value);
                    } else if (type == Boolean.class || type == boolean.class) {
                        dataRow += String.format("<td>%s</td>",  value);
                    } else if (type == Long.class || type == long.class) {
                        dataRow += String.format("<td>%s</td>",value);
                    } else if (type == Float.class || type == float.class) {
                        dataRow += String.format("<td>%s</td>",  value);
                    } else if (type == LocalDateTime.class) {
                        dataRow += String.format("<td>%s</td>",  value);
                    } else {
                        throw new RuntimeException("Unsupported field type: " + declaredField.getType());
                    }


                }
                dataRow += "</tr>\n";

                strData += dataRow;
            }

            htmlDoc = htmlDoc.replaceAll("DATA_ROWS", strData);
            outputStream.write(htmlDoc.getBytes());
//            workbook.write(outputStream);

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
