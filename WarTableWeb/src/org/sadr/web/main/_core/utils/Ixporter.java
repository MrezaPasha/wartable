/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr.web.main._core.utils;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sadr._core._type.TtIxporterHeader;
import org.sadr._core._type.TtIxporterModeSection;
import org.sadr._core.meta.annotation.ExporterMode;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.utils.Environment;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr.web.main.archive._type.TtRepoDirectory;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Transactional
//@PropertySource({"/WEB-INF/conf/message/messageAlef_fa.properties"})
public class Ixporter<T extends Serializable> {

    private final Class<T> clazz;
    private final String _DB_ID_prefix = "_dbId_";
    private final String _DB_TITLE_perfix = "_dbTitle_";
    private final String _Tt_prefix = "_type_";
    private final String _NUM_prefix = "_number_";
    private final String _FLOAT_prefix = "_float_";
    private final String _Text_prefix = "_text_";

    public Ixporter(Class<T> clazz) {
        this.clazz = clazz;
    }

    /////////////////     * EXPORT
    public void exportToFile(List<T> objectList, boolean isTitleMode, HttpServletResponse response) throws IOException {
        if (objectList == null) {
            OutLog.pl();
            response.sendError(HttpServletResponse.SC_NO_CONTENT);
        }

        java.io.File dir = new java.io.File(Environment.getInstance().getProjectRepositoryAP() + Environment.FILE_SEPARATOR + TtRepoDirectory.ExportExcel.getKey());
        String relPath = Environment.getInstance().getContextPath() + Environment.getInstance().getRepositoryAddress() + Environment.FILE_SEPARATOR + TtRepoDirectory.ExportExcel.getKey();
        if (!dir.exists()) {
            OutLog.p("dir create on server file directory");
            dir.mkdirs();
        }

        Field[] fields = clazz.getDeclaredFields();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = initializeSheet(workbook, fields, isTitleMode);

//        setSheetHelp(workbook, sheet, fields, isTitleMode);
        // lock two top rows
        sheet.createFreezePane(0, 2);
        // right to left layout
        sheet.setRightToLeft(true);
        // coulmn width      
        sheet.setDefaultColumnWidth(12);

        ////////////
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Tahoma");
        style.setFont(font);
        ////////////

        int rowCount = 2;
        int col;
        XSSFRow aRow;
        for (Object o : objectList) {
            aRow = sheet.createRow(rowCount++);
            col = 0;
            for (Field field : fields) {
                if (java.lang.reflect.Modifier.isStatic(field.getModifiers())
                    || field.getType().getModifiers() == 1537
                    || (field.isAnnotationPresent(ExporterMode.class)
                    && field.getAnnotation(ExporterMode.class).value() == TtIxporterModeSection.NoInExcel)) {
                    OutLog.p("continiue...");
                    continue;
                }

                try {
                    String _fieldTypeFull = field.getType().getName();
                    String _fieldName = field.getName();
                    int _fieldModifier = field.getType().getModifiers();

                    OutLog.pl("** >>>| " + _fieldName + " - " + _fieldModifier + "  " + _fieldTypeFull);

                    /**
                     * 1537 Set<?>
                     * 16401 Tt
                     *
                     */
                    if (_fieldModifier == 1537 || "entityState".equals(_fieldName)) {
                        OutLog.p("continued...");
                        continue;
                    }

                    field.setAccessible(true);
                    /**
                     * free fields
                     */
                    if (field.get(o) == null) {
                        OutLog.p("free...");
                        col++;
                        continue;
                    }
                    /**
                     * 16401 Tt <>
                     * 1537 Set<>
                     * 1041 int <>
                     * 17 String <>
                     * 1 Custom Classes<>
                     * 1 Date
                     */
                    switch (_fieldModifier) {
                        case 1:
                            try {
                                aRow.createCell(col++).setCellValue(field.getType().getMethod("getExportTitle").invoke(field.get(o)).toString());
                            } catch (Exception exx) {
                                aRow.createCell(col++).setCellValue("");
                                exx.printStackTrace();
                            }
                            break;

                        case 16401:
                            try {
                                aRow.createCell(col++).setCellValue(field.getType().getMethod("getTitle").invoke(field.get(o)).toString());
                            } catch (Exception xx) {
                                try {
                                    aRow.createCell(col++).setCellValue(field.getType().getMethod("getTitle").invoke(field.get(o)).toString());
                                } catch (Exception x) {
                                    x.printStackTrace();
                                    try {
                                        aRow.createCell(col++).setCellValue(field.get(o).toString());
                                    } catch (Exception xo) {
                                        aRow.createCell(col++).setCellValue("");
                                        xo.printStackTrace();
                                    }
                                }
                                OutLog.pl(xx.toString());
                            }
                            break;
                        default:
                            OutLog.pl("^^^ > " + field.get(o).toString());
                            aRow.createCell(col++).setCellValue(field.get(o).toString());
                            break;
                    }
                } catch (SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(Ixporter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            aRow.setRowStyle(style);
        }
        String fn = ParsCalendar.getInstance().getShortDate().replaceAll("/", "") + "-" + clazz.getSimpleName() + ".xlsx";
        try (FileOutputStream outputStream = new FileOutputStream(dir + Environment.FILE_SEPARATOR + fn)) {
            workbook.write(outputStream);

            Path fpath = Paths.get(dir + Environment.FILE_SEPARATOR + fn);
            if (Files.exists(fpath)) {
                OutLog.pl("");
//                  response.setContentType();
                response.addHeader("Content-Disposition", "attachment; filename= " + fn);
                try {
                    response.addHeader("file-path", relPath + Environment.FILE_SEPARATOR + fn);
                    Files.copy(fpath, response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    OutLog.pl(ex.getMessage());
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ixporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private XSSFSheet initializeSheet(XSSFWorkbook workbook, Field[] fields, boolean isTitleMode) {

        XSSFSheet sheet;
        CellStyle style;
        Font font;
        XSSFRow header;
        int idx = 0;

        try {
            sheet = workbook.createSheet(clazz.getAnnotation(PersianName.class).value());
        } catch (Exception ex) {
            sheet = workbook.createSheet(clazz.getSimpleName());
        }
        // create style for header cells
        style = workbook.createCellStyle();
        font = workbook.createFont();
        font.setFontName("Tahoma");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        style.setLocked(true);
        // create header row
        header = sheet.createRow(0);

        for (Field field : fields) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())
                || field.getType().getModifiers() == 1537
                || (field.isAnnotationPresent(ExporterMode.class)
                && field.getAnnotation(ExporterMode.class).value() == TtIxporterModeSection.NoInExcel)) {
                OutLog.p("continiue...");
                continue;
            }
            OutLog.pl();
            /**
             * 16401 Tt <>
             * 1537 Set<>
             * 1041 int <>
             * 17 String <>
             * 1 Custom Classes<>
             * 1 Date
             */
            switch (field.getType().getModifiers()) {
                case 1:
                    OutLog.pl();
                    if (isTitleMode) {
                        header.createCell(idx).setCellValue(field.getName() + _DB_TITLE_perfix);
                    } else {
                        header.createCell(idx).setCellValue(field.getName() + _DB_ID_prefix);
                    }
                    break;
                case 16401:
                    header.createCell(idx).setCellValue(field.getName() + _Tt_prefix);
                    break;
                case 1041:
                    if (field.getType().getSimpleName().trim().toLowerCase().contains("double")) {
                        header.createCell(idx).setCellValue(field.getName() + _FLOAT_prefix);
                    } else {
                        header.createCell(idx).setCellValue(field.getName() + _NUM_prefix);
                    }
                    break;
                case 17:
                    header.createCell(idx).setCellValue(field.getName() + _Text_prefix);
                    break;
                default:
                    header.createCell(idx).setCellValue(field.getName());
            }
            header.getCell(idx++).setCellStyle(style);
        }
        font.setColor(HSSFColor.BLACK.index);
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        idx = 0;
        header = sheet.createRow(1);
        for (Field field : fields) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())
                || field.getType().getModifiers() == 1537
                || (field.isAnnotationPresent(ExporterMode.class)
                && field.getAnnotation(ExporterMode.class).value() == TtIxporterModeSection.NoInExcel)) {
                OutLog.p("continiue...");
                continue;
            }
            OutLog.pl();
            try {
                header.createCell(idx).setCellValue((field.getAnnotation(PersianName.class).value()));
            } catch (Exception e) {
                header.createCell(idx).setCellValue((field.getName()));
            }
            header.getCell(idx++).setCellStyle(style);
        }
        return sheet;
    }

    /////////////////     * IMPORT
    public List<Cell[]> importFromFile(org.sadr.web.main.archive.file.file.File file, TtIxporterHeader ixporterHeader) {

        try (FileInputStream inputStream = new FileInputStream(file.getSourceFile())) {
            Iterator<Row> rowIterator;

            try {
                Workbook workbook = new XSSFWorkbook(inputStream);
                Sheet firstSheet = workbook.getSheetAt(0);
                rowIterator = firstSheet.iterator();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            switch (ixporterHeader) {
                case MetaHeader:
                case TitleHeader:
                    OutLog.pl();
                    if (rowIterator.hasNext()) {
                        rowIterator.next();
                    }
                    break;
                case TitleAndMetaHeader:
                    OutLog.pl();
                    if (rowIterator.hasNext()) {
                        rowIterator.next();
                    }
                    if (rowIterator.hasNext()) {
                        rowIterator.next();
                    }
                    break;
            }
            Row row;
            int cellCount;
            Cell[] ro;
            List<Cell[]> objectList = new ArrayList<>();

            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                cellCount = row.getLastCellNum();
                ro = new Cell[cellCount];
                for (int i = 0; i < cellCount; i++) {
                    if (row.getCell(i) != null) {
                        row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                        ro[i] = row.getCell(i);
                    }
                }
                objectList.add(ro);
            }
            OutLog.pl();
            return objectList;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
