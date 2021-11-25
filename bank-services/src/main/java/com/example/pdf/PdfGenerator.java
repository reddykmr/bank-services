package com.example.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.model.TransactionLogs;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
@Component("pdfgenerator")
public class PdfGenerator {
	 static Font COURIER_SMALL_FOOTER = new Font(Font.FontFamily.COURIER,12,Font.BOLD);
	 static Font COURIER_SMALL_HEADER = new Font(Font.FontFamily.COURIER,10,Font.BOLD);
	
	public static ByteArrayInputStream getLogs(List<TransactionLogs> logs) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
       

        try {

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{10, 10, 10,10,10,10});

            Font headFont = FontFactory.getFont(FontFactory.COURIER);
            headFont.setColor(BaseColor.BLUE);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("TX ID", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("ACC NO", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("TX DATE", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("OPENING BALANCE", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("DEBIT/CREDIT", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("UPDATED BALANCE", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

           for(TransactionLogs log:logs) {

                PdfPCell cell;
                Font font2=FontFactory.getFont(FontFactory.COURIER_BOLD);
                font2.setColor(BaseColor.BLACK);
                font2.setSize(9.0f);

                cell = new PdfPCell(new Phrase(log.getTxid(),font2));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(log.getAccno(),font2));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(log.getDate(),font2));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(String.valueOf(log.getOpeningbalance()),font2));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(log.getAmount()),font2));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(log.getUpdatedBalance()),font2));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);
           }

            PdfWriter.getInstance(document, out);
            document.open();
            addDocTitle(document,logs.get(0).getAccno());
            document.add(table);
            addFooter(document);
            document.close();

        } catch (DocumentException ex) {

            System.out.print(ex.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
	private static void addDocTitle(Document document,String accno) throws DocumentException {
		Paragraph p1 = new Paragraph();
		p1.setAlignment(Element.ALIGN_CENTER);
		p1.add(new Paragraph("                                                                         "+
		                     "                                                                         "+
				             "HDFC BANK",COURIER_SMALL_HEADER));
		p1.add(new Paragraph("                                                                         "+
                             "                                                                         "+
				             "USER NAME :"+"KARNA MAHESH",COURIER_SMALL_HEADER));
		p1.add(new Paragraph("                                                                         "+
                             "                                                                         "+
	                         "ACC NO    :"+accno,COURIER_SMALL_HEADER));
		leaveEmptyLine(p1, 2);

		document.add(p1);

	}
	private static void addFooter(Document document) throws DocumentException {
		Paragraph p1 = new Paragraph();
		p1.setAlignment(Element.ALIGN_MIDDLE);
		leaveEmptyLine(p1, 2);
		p1.add(new Paragraph("-----------------------------Thank You---------------------------------",COURIER_SMALL_FOOTER));
		document.add(p1);

	}
	private static void leaveEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
