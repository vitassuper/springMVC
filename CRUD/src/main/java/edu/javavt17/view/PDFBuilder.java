package edu.javavt17.view;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.javavt17.model.BookAuthor;
import edu.javavt17.model.Book;

public class PDFBuilder extends AbstractTextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
                                    PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        // get data model which is passed by the Spring container
        Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        paragraphFont.setSize(14);

        List<BookAuthor> bookAuthors = (List<BookAuthor>) model.get("bookAuthor");
        Paragraph brandParagraph = new Paragraph("Book authors",paragraphFont);
        brandParagraph.setAlignment(Element.ALIGN_CENTER);
        doc.add(brandParagraph);
        PdfPTable carBrandsTable = getCarBrandTable(bookAuthors);
        doc.add(carBrandsTable);

        List<Book> books = (List<Book>) model.get("book");
        Paragraph modelParagraph = new Paragraph("Books",paragraphFont);
        modelParagraph.setAlignment(Element.ALIGN_CENTER);
        doc.add(modelParagraph);
        PdfPTable carModelsTable = getCarModelTable(books);
        doc.add(carModelsTable);
    }


    private PdfPTable getCarBrandTable(List<BookAuthor> bookAuthors) throws Exception {

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{0.4f, 2.0f, 1.0f, 4.0f});
        table.setSpacingBefore(10);
        table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA, "ISO-8859-5");
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(BaseColor.GRAY);
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        // write table header
        cell.setPhrase(new Phrase("#", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Brand name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Founded year", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Headquarter", font));
        table.addCell(cell);

        // write table row data
        int index = 1;
        for (BookAuthor bookAuthor : bookAuthors) {
            table.addCell("" + index);
            table.addCell(bookAuthor.getName());
            table.addCell("" + bookAuthor.getBirthYear());
            table.addCell(bookAuthor.getAlias());
            index++;
        }
        return table;
    }

    private PdfPTable getCarModelTable(List<Book> books) throws Exception {

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{0.4f, 1.5f, 1.5f, 2.0f, 0.7f, 0.7f, 0.7f, 0.7f});
        table.setSpacingBefore(5);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA, "ISO-8859-5");
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.GRAY);
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        // write table header
        cell.setPhrase(new Phrase("#", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Author", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Book", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Founded Year", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

        int index = 1;
        // write table row data
        for (Book book : books) {
            table.addCell("" + index);
            table.addCell(book.getBookAuthor().getName());
            table.addCell(book.getBookName());
            table.addCell(""+ book.getFoundedYear());
            table.addCell("" + book.getDescription());
            index++;
        }
        return table;
    }
}