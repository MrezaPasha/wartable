/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr.web.main._core.tools.ixporter;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sadr._core._type.TtIxporterFileType;
import org.sadr._core._type.TtIxporterModeSection;
import org.sadr._core._type.TtSecurityTag;
import org.sadr._core.meta.annotation.ExporterMode;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.IxporterParams;
import org.sadr._core.utils.*;
import org.sadr.web.config.WebConfigHandler;
import org.sadr.web.main._core._type.TtTaskAccessLevel;
import org.sadr.web.main._core.tools._type.*;
import org.sadr.web.main._core.utils.Ison;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils._type.TtIsonStatus;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.archive._type.TtRepoDirectory;
import org.sadr.web.main.archive.directory.DirectoryService;
import org.sadr.web.main.archive.file.file.FileService;
import org.sadr.web.main.system._type.TtRegisteryKey;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
import org.sadr.web.main.system.registery.Registery;
import org.sadr.web.main.system.registery.RegisteryService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.CipherOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

    private Ixporter(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static Ixporter init(Class<?> clazz) {
        return new Ixporter(clazz);
    }

    /////////////////     * EXPORT
    public void exportExcelToFile(List<T> objectList, boolean isTitleMode, HttpServletResponse response) throws IOException {
        if (objectList == null) {
            OutLog.pl();
            response.sendError(HttpServletResponse.SC_NO_CONTENT);
        }

        java.io.File dir = new java.io.File(Environment.getInstance().getProjectRepositoryAP() + Environment.FILE_SEPARATOR + TtRepoDirectory.Export.getKey());
        String relPath = Environment.getInstance().getContextPath() + Environment.getInstance().getRepositoryAddress() + Environment.FILE_SEPARATOR + TtRepoDirectory.Export.getKey();
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
                    OutLog.p("continue...");
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


    //////////////////////////  GB

    public ResponseEntity<String> exportToFileInList(List<T> objectList, HttpServletResponse response, GB gb, TtIxportTtStrategy ttStrategy, TtIxportSubStrategy ttIxportSubStrategy, TtIxportRowIndex ttRowIndex, TtIxporterDownloadMode downloadMode, String ixporterParams) throws IOException {

        IxporterParams ixParams = IxporterParams.getObject(ixporterParams);

        switch (ixParams.getFileType()) {
            case Excel:
                String s = exportExcelToFile(objectList, response, gb, ttStrategy, ttIxportSubStrategy, ttRowIndex, downloadMode, ixParams);
                return Ison.init(TtTaskActionSubType.Export_File, TtTaskActionStatus.Success)
                        .setStatus(TtIsonStatus.Ok)
                        .setMessages(new Notice2("N1.ixporter.export.excel.success", TtNotice.Success, s))
                        .toResponse();

            default:
                response.addHeader("Content-Disposition", "attachment; filename= " + "");
                response.addHeader("file-path", "");
                response.getOutputStream().flush();
                return Ison.init(TtTaskActionSubType.Export_File, TtTaskActionStatus.Success)
                        .setStatus(TtIsonStatus.Ok)
                        .setMessages(new Notice2("N1.ixporter.export.not.found", TtNotice.Danger))
                        .toResponse();
        }

    }

    public String exportExcelToFile(List<?> objectList, HttpServletResponse response, GB gb, TtIxportTtStrategy ttStrategy, TtIxportSubStrategy ttIxportSubStrategy, TtIxportRowIndex ttRowIndex, TtIxporterDownloadMode downloadMode, IxporterParams ixporterParams) {
        if (objectList == null) {
            try {
                response.sendError(HttpServletResponse.SC_NO_CONTENT);
            } catch (IOException e) {
                return null;
            }
        }

        java.io.File dir;
        XSSFWorkbook workbook;
        XSSFSheet sheet;
        XSSFRow aRow;
        int colCount, rowCount, colIdx, ix;
        List<String[]> rowList;
        String[] rowValues;
        String relPath;

        dir = new File(Environment.getInstance().getProjectRepositoryAP() + Environment.FILE_SEPARATOR + TtRepoDirectory.Export.getKey());
        relPath = Environment.getInstance().getContextPath() + Environment.getInstance().getRepositoryAddress() + Environment.FILE_SEPARATOR + TtRepoDirectory.Export.getKey();
        if (!dir.exists()) {
            OutLog.p("dir create on server file directory");
            dir.mkdirs();
        }
        int headRows = ixporterParams.getSecurityTag() == TtSecurityTag.WithoutTag ? 1 : 2;
        workbook = new XSSFWorkbook();
        try {
            sheet = workbook.createSheet(clazz.getAnnotation(PersianName.class).value());
        } catch (Exception ex) {
            sheet = workbook.createSheet(clazz.getSimpleName());
        }
        colCount = initializeSheet(workbook, sheet, gb, ttIxportSubStrategy, ttRowIndex, ixporterParams);

//        setSheetHelp(workbook, sheet, fields, isTitleMode);

        sheet.createFreezePane(0, headRows);
        // right to left layout
        sheet.setRightToLeft(true);
        // column width
        sheet.setDefaultColumnWidth(12);
        ////////////
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Tahoma");
        style.setFont(font);
        ////////////

        rowList = new ArrayList<>();

        ix = 0;
        for (Object o : objectList) {
            colIdx = 0;
            rowValues = new String[colCount];
            if (ttRowIndex == TtIxportRowIndex.On) {
                rowValues[0] = ++ix + "";
                colIdx++;
            }
            fillRow(clazz, o, rowValues, gb, ttStrategy, ttIxportSubStrategy, colIdx);
            rowList.add(rowValues);
        }
        rowCount = headRows;
        for (String[] ss : rowList) {
            colIdx = 0;
            aRow = sheet.createRow(rowCount++);
            for (String s : ss) {
                aRow.createCell(colIdx++).setCellValue(s);
            }
            aRow.setRowStyle(style);
        }

        return generateFile(workbook, dir, relPath, downloadMode, response, ixporterParams);
    }

    private String generateFile(XSSFWorkbook workbook, java.io.File dir, String relPath, TtIxporterDownloadMode downloadMode, HttpServletResponse response, IxporterParams ixporterParams) {
        RegisteryService registeryService = null;

        String exportName = ParsCalendar.getInstance().getShortDate().replaceAll("/", "") + "-" + clazz.getSimpleName();
        String exportFileName, contentType;
        Path fpath;

        if (ixporterParams.getIsEncrypt()) {
            contentType = "Encrypted";
            exportFileName = exportName + ".crypt";
            try (FileOutputStream outputStream = new FileOutputStream(dir.getAbsolutePath() + Environment.FILE_SEPARATOR + exportFileName)) {
                registeryService = WebConfigHandler.getWebApplicationContext().getBean(RegisteryService.class);
                if (registeryService == null) {
                    return null;
                }
                Registery registery = registeryService.findByKey(TtRegisteryKey.IxportCryptKey);
                if (registery == null) {
                    return null;
                }
                CipherOutputStream out = new CipherOutputStream(outputStream, Cryptor.getEncryptCipher(Cryptor.decrypt(registery.getValue())));
                workbook.write(out);
            } catch (IOException e) {
                return null;
            }
        } else {
            contentType = "Excel";
            exportFileName = exportName + ".xlsx";
            try (FileOutputStream outputStream = new FileOutputStream(dir.getAbsolutePath() + Environment.FILE_SEPARATOR + exportFileName)) {
                workbook.write(outputStream);
            } catch (IOException e) {
                return null;
            }
        }

        FileService fileService = WebConfigHandler.getWebApplicationContext().getBean(FileService.class);
        DirectoryService directoryService = WebConfigHandler.getWebApplicationContext().getBean(DirectoryService.class);

        fpath = Paths.get(dir + Environment.FILE_SEPARATOR + exportFileName);

        if (ixporterParams.getIsSign()) {
            if (registeryService == null) {
                registeryService = WebConfigHandler.getWebApplicationContext().getBean(RegisteryService.class);
                if (registeryService == null) {
                    return null;
                }
            }
            Registery registery = registeryService.findByKey(TtRegisteryKey.IxportPrivateKey);
            if (registery == null) {
                return null;
            }

            Path fpathSign = Paths.get(dir.getAbsolutePath() + Environment.FILE_SEPARATOR + exportName + ".sign");
            try {
                byte[] fileContent = Files.readAllBytes(fpath);
                byte[] sign = Cryptor.sign(fileContent, registery.getValueByte());
                try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fpathSign.toString()))) {
                    stream.write(sign);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            exportFileName = exportName + ".zip";
            contentType = "Zip";

            File file = FileUtil.zipFiles(dir.getAbsolutePath() + Environment.FILE_SEPARATOR + exportFileName, fpath.toFile(), fpathSign.toFile());

            try {
                Files.delete(fpath);
                Files.delete(fpathSign);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fpath = Paths.get(file.getAbsolutePath());
        }

        relPath = relPath + Environment.FILE_SEPARATOR + exportFileName;
        if (Files.exists(fpath)) {
            if (downloadMode == TtIxporterDownloadMode.FileControllerAddress) {
                if (fileService != null && directoryService != null) {
                    org.sadr.web.main.archive.file.file.File dbFile = new org.sadr.web.main.archive.file.file.File();
                    dbFile.setTitle(exportFileName);
                    dbFile.setIsContainOrginal(true);
                    dbFile.setOrginalName(exportFileName);
                    dbFile.setContentType(contentType);
                    dbFile.setAccessLevel(TtTaskAccessLevel.Free4Users);
                    dbFile.setDirectory(directoryService.getDirectory(TtRepoDirectory.Export));

                    fileService.save(dbFile);

                    relPath = Environment.getInstance().getContextPath() + downloadMode.getAddress() + Environment.FILE_SEPARATOR + dbFile.getIdi();
                }
            }

            response.addHeader("Content-Disposition", "attachment; filename= " + exportFileName);
            try {
                response.addHeader("file-path", relPath);
                response.getOutputStream().flush();
            } catch (IOException ex) {
                OutLog.pl(ex.getMessage());
                try {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return exportFileName;
    }

    private int initializeSheet(XSSFWorkbook workbook, XSSFSheet sheet, GB gb, TtIxportSubStrategy ttIxportSubStrategy, TtIxportRowIndex ttRowIndex, IxporterParams ixporterParams) {
        CellStyle style;
        Font font;
        XSSFRow header;
        int colIndex;

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

        // create SecurityTag row
        if (ixporterParams.getSecurityTag() != TtSecurityTag.WithoutTag) {
            header = sheet.createRow(0);
            header.createCell(0).setCellValue("برچسب امنیتی: " + ixporterParams.getSecurityTag().getTitle());
            header = sheet.createRow(1);
        } else {
            header = sheet.createRow(0);
        }

        // create header row
        colIndex = 0;
        if (ttRowIndex == TtIxportRowIndex.On) {
            header.createCell(0).setCellValue("ردیف");
            colIndex++;
        }

        int i = fillHeader(clazz, header, gb, ttIxportSubStrategy, colIndex);

        if (ixporterParams.getSecurityTag() != TtSecurityTag.WithoutTag && i > 1) {
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, i - 1));
        }
        return i;
    }

    private int fillRow(Class subClazz, Object srcObj, String[] dstValues, GB gb, TtIxportTtStrategy ttStrategy, TtIxportSubStrategy subStrategy, int colIndex) {
        Object v;
        for (String f : gb.getColumnsWithCheck()) {
            if (f == null) {
                colIndex++;
                continue;
            }
            try {
                v = subClazz.getMethod("get" + f.substring(0, 1).toUpperCase() + f.substring(1)).invoke(srcObj);

                if (v != null) {
                    OutLog.pl(v.toString());
                }
                //==============  null objects
                if (v == null) {
                }
                //==============  Integer, Double, Float, Boolean   OR   SecretNote
                else if (v instanceof Integer || v instanceof Double || v instanceof Float || v instanceof Boolean || "secretNote".equals(f)) {
                    dstValues[colIndex] = v.toString();
                }
                //==============  Tt Types
                else if (v.getClass().getSimpleName().startsWith("Tt")) {
                    switch (ttStrategy) {
                        case TitleMode:
                            try {
                                dstValues[colIndex] = v.getClass().getMethod("getTitle").invoke(v).toString();
                            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                dstValues[colIndex] = v.toString();
                            }
                            break;
                        case OrdinalMode:
                            try {
                                dstValues[colIndex] = v.getClass().getMethod("ordinal").invoke(v).toString();
                            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                dstValues[colIndex] = v.toString();
                            }
                            break;
                        case TitleThenKeyMode:
                            try {
                                dstValues[colIndex] = v.getClass().getMethod("getTitle").invoke(v).toString();
                            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                try {
                                    dstValues[colIndex] = v.getClass().getMethod("getKey").invoke(v).toString();
                                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ee) {
                                    dstValues[colIndex] = v.toString();
                                }
                            }
                            break;
                        default:
                            dstValues[colIndex] = v.toString();
                            break;
                    }
                }
                //==============  Others (String)
                else {
                    dstValues[colIndex] = v.toString();
                }
            } catch (NoSuchMethodException ex) {
                try {
                    if (subClazz.getDeclaredField(f).getType().equals(boolean.class)) {
                        v = subClazz.getMethod("is" + f.substring(0, 1).toUpperCase() + f.substring(1)).invoke(this);
                        dstValues[colIndex] = v.toString();
                    }
                } catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex1) {
                    dstValues[colIndex] = "EXP_b";

                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                dstValues[colIndex] = "EXP";
            }
            colIndex++;
        }
        //==============  SubClasses Relations
        if (subStrategy == TtIxportSubStrategy.IncludeSubs && gb.getGbs() != null && gb.getGbs().length > 0) {
            for (GB g : gb.getGbs()) {
                try {
                    v = subClazz.getMethod("get" + g.getName().substring(0, 1).toUpperCase() + g.getName().substring(1)).invoke(srcObj);
                    if (v != null) {
                        colIndex = fillRow(g.getClazz(), v, dstValues, g, ttStrategy, subStrategy, colIndex);
                    } else {
                        colIndex += g.getColumnsCountInDepth();///////
                    }
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }


        return colIndex;
    }

    private int fillHeader(Class subClazz, XSSFRow header, GB gb, TtIxportSubStrategy subStrategy, int colIndex) {
        if (gb != null) {
            Field field;
            if (gb.getColumnsWithCheck() != null) {
                for (String f : gb.getColumns()) {
                    if (f == null) {
                        colIndex++;
                        continue;
                    }
                    try {
                        field = subClazz.getDeclaredField(f);
                    } catch (NoSuchFieldException e) {
                        try {
                            field = subClazz.getSuperclass().getDeclaredField(f);
                        } catch (NoSuchFieldException e1) {
                            field = null;
                            colIndex++;
                            continue;
                        }

                    }
                    try {
                        String export = field.getAnnotation(PersianName.class).export();

                        header.createCell(colIndex).setCellValue(export.isEmpty() ? field.getAnnotation(PersianName.class).value() : export);
                    } catch (Exception e) {
                        header.createCell(colIndex).setCellValue((field.getName()));
                    }
                    colIndex++;
                }
            }
            if (subStrategy == TtIxportSubStrategy.IncludeSubs && gb.getGbs() != null && gb.getGbs().length > 0) {
                for (GB g : gb.getGbs()) {
                    colIndex = fillHeader(g.getClazz(), header, g, subStrategy, colIndex);
                }
            }
        }
        return colIndex;
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
