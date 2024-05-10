package com.company.dynamic_pdf_generation.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.dynamic_pdf_generation.dto.PdfGeneration;
import com.company.dynamic_pdf_generation.service.PdfGenerationService;
import com.itextpdf.io.source.ByteArrayOutputStream;

@RequestMapping("/pdf")
@RestController
public class PdfController {

    private static final Logger logger = LogManager.getLogger(PdfController.class.getName());

    @Autowired
    private PdfGenerationService pdfGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<Object> generatePdf(@RequestBody PdfGeneration request) throws Exception {

	logger.info("Request received for PDF generation");
	if (request == null || request.getItems() == null)
	    return new ResponseEntity<>("Missing request body", HttpStatus.BAD_REQUEST);

	ByteArrayOutputStream outputStream = pdfGeneratorService.generateByteArray(request);

	HttpHeaders headers = getHeaders();

	logger.info("Response sent for PDF generation request");
	return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    private HttpHeaders getHeaders() {
	HttpHeaders headers = new HttpHeaders();

	headers.setContentType(MediaType.APPLICATION_PDF);

	headers.setContentDisposition(ContentDisposition.builder("attachment").filename("invoice.pdf").build());
	return headers;
    }

}
