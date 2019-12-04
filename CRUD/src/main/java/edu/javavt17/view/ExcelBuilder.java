package edu.javavt17.view;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.javavt17.model.Book;
import edu.javavt17.model.BookAuthor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelBuilder extends AbstractPOIExcelView {
    @Override
    protected XSSFWorkbook createWorkbook() {
        return new XSSFWorkbook();
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model, XSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring container
        List<BookAuthor> bookAuthors = (List<BookAuthor>) model.get("carBrands");
        List<Book> books = (List<Book>) model.get("carModels");

        generateAuthorSheet(workbook, bookAuthors);
        generateModelsSheet(workbook, books);
    }

    private void generateAuthorSheet(XSSFWorkbook workbook, List<BookAuthor> bookAuthors){

        // create a new Excel sheet
        Sheet sheet = workbook.createSheet("Brands");
        sheet.setColumnWidth(0,2000);
        sheet.setColumnWidth(1,5000);
        sheet.setColumnWidth(2,3000);
        sheet.setColumnWidth(3,10000);

        Font font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        font.setFontName("Helvetica");

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setFont(font);
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);

        // create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("№");
        headerRow.getCell(0).setCellStyle(headerStyle);

        headerRow.createCell(1).setCellValue("Brand name");
        headerRow.getCell(1).setCellStyle(headerStyle);

        headerRow.createCell(2).setCellValue("Founded year");
        headerRow.getCell(2).setCellStyle(headerStyle);

        headerRow.createCell(3).setCellValue("Headquarter");
        headerRow.getCell(3).setCellStyle(headerStyle);

        CellStyle rowCellStyle = workbook.createCellStyle();
        rowCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        // create data rows
        int rowCount = 1;
        for (BookAuthor bookAuthor : bookAuthors) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(rowCount-1);
            row.getCell(0).setCellStyle(rowCellStyle);

            row.createCell(1).setCellValue(bookAuthor.getName());
            row.getCell(1).setCellStyle(rowCellStyle);

            row.createCell(2).setCellValue(bookAuthor.getBirthYear());
            row.getCell(2).setCellStyle(rowCellStyle);

            row.createCell(3).setCellValue(bookAuthor.getAlias());
            row.getCell(3).setCellStyle(rowCellStyle);
        }
    }

    private void generateModelsSheet(XSSFWorkbook workbook, List<Book> books){

        // create a new Excel sheet
        Sheet sheet = workbook.createSheet("Models");
        sheet.setColumnWidth(0,2000);
        sheet.setColumnWidth(1,5000);
        sheet.setColumnWidth(2,5000);
        sheet.setColumnWidth(3,7000);
        sheet.setColumnWidth(4,2500);
        sheet.setColumnWidth(5,2000);
        sheet.setColumnWidth(6,2000);
        sheet.setColumnWidth(7,2500);

        Font font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        font.setFontName("Helvetica");

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setFont(font);
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);

        // create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("№");
        headerRow.getCell(0).setCellStyle(headerStyle);

        headerRow.createCell(1).setCellValue("Author");
        headerRow.getCell(1).setCellStyle(headerStyle);

        headerRow.createCell(2).setCellValue("Book");
        headerRow.getCell(2).setCellStyle(headerStyle);

        headerRow.createCell(3).setCellValue("Founded Year");
        headerRow.getCell(3).setCellStyle(headerStyle);

        headerRow.createCell(4).setCellValue("Description");
        headerRow.getCell(4).setCellStyle(headerStyle);


        CellStyle rowCellStyle = workbook.createCellStyle();
        rowCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        // create data rows
        int rowCount = 1;
        for (Book book : books) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(rowCount-1);
            row.getCell(0).setCellStyle(rowCellStyle);

            row.createCell(1).setCellValue(book.getBookAuthor().getName());
            row.getCell(1).setCellStyle(rowCellStyle);

            row.createCell(2).setCellValue(book.getBookName());
            row.getCell(2).setCellStyle(rowCellStyle);

            row.createCell(3).setCellValue(book.getFoundedYear());
            row.getCell(3).setCellStyle(rowCellStyle);

            row.createCell(4).setCellValue(book.getDescription());
            row.getCell(4).setCellStyle(rowCellStyle);

        }
    }

}