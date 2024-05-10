package com.company.dynamic_pdf_generation.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.company.dynamic_pdf_generation.dto.PdfGeneration;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

@Service
public class PdfGenerationServiceImpl implements PdfGenerationService {

    private static final Logger logger = LogManager.getLogger(PdfGenerationServiceImpl.class);

    public ByteArrayOutputStream generateByteArray(PdfGeneration request) {
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	try {

	    logger.info("Started generating pdf");
	    PdfWriter pdfWriter = new PdfWriter(outputStream);

	    PdfDocument pdfDocument = new PdfDocument(pdfWriter);

	    PdfFont pdfFont = PdfFontFactory.createFont(StandardFonts.COURIER_BOLD);

	    Document document = new Document(pdfDocument).setFont(pdfFont);

	    float[] width = { 280F, 280F };

	    Table tableHeader = new Table(width);

	    String seller = "Seller:\n" + request.getSeller() + "\n" + request.getSellerAddress() + "\n GSTIN: "
		    + request.getSellerGstin();

	    tableHeader.addCell(new Cell().add(new Paragraph(seller)).setPadding(30));

	    String buyer = "Buyer:\n" + request.getBuyer() + "\n" + request.getBuyerAddress() + "\n GSTIN: "
		    + request.getBuyerGstin();

	    tableHeader.addCell(new Cell().add(new Paragraph(buyer)).setPadding(30));

	    float[] columnWidhths = { 140, 140, 140, 140 };

	    Table productTable = new Table(columnWidhths);

	    productTable.setTextAlignment(TextAlignment.CENTER);
	    productTable.addCell(new Cell().add(new Paragraph("Item")));
	    productTable.addCell(new Cell().add(new Paragraph("Quantity")));
	    productTable.addCell(new Cell().add(new Paragraph("Rate")));
	    productTable.addCell(new Cell().add(new Paragraph("Amount")));

	    List<com.company.dynamic_pdf_generation.dto.Item> items = request.getItems();
	    for (com.company.dynamic_pdf_generation.dto.Item item : items) {
		productTable.addCell(new Cell().add(new Paragraph(item.getName())));
		productTable.addCell(new Cell().add(new Paragraph(item.getQuantity())));
		productTable.addCell(new Cell().add(new Paragraph(String.valueOf(item.getRate()))));
		productTable.addCell(new Cell().add(new Paragraph(String.valueOf(item.getAmount()))));
	    }
	    
	    float[] extraColumnWidhths = { 560 };
	    Table extraBlankTable = new Table(extraColumnWidhths);
	    extraBlankTable.addCell(new Cell().add(new Paragraph()).setPadding(30));

	    document.add(tableHeader);
	    document.add(productTable);
	    document.add(extraBlankTable);
	    pdfWriter.close();
	    pdfDocument.close();
	    document.close();
	    logger.info("Generated PDF successfully");
	}

	catch (Exception e) {
	    logger.info("Failure while gnerating PDF");
	    e.printStackTrace();
	}

	return outputStream;
    }
}
