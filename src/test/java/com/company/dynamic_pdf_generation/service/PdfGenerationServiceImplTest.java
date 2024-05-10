package com.company.dynamic_pdf_generation.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.company.dynamic_pdf_generation.dto.Item;
import com.company.dynamic_pdf_generation.dto.PdfGeneration;

public class PdfGenerationServiceImplTest {

    @Test
    public void testGenerateByteArray() {
	PdfGeneration request = createSampleRequest();

	PdfGenerationServiceImpl pdfGenerationService = new PdfGenerationServiceImpl();

	ByteArrayOutputStream outputStream = pdfGenerationService.generateByteArray(request);

	Assertions.assertNotNull(outputStream);
    }

    @Test
    public void testGenerateByteArray_InvalidData() {
	PdfGeneration request = mock(PdfGeneration.class);
	when(request.getSeller()).thenReturn("Seller Name");
	when(request.getSellerAddress()).thenReturn("Seller Address");
	when(request.getSellerGstin()).thenReturn("Seller GSTIN");
	when(request.getBuyer()).thenReturn("Buyer Name");
	when(request.getBuyerAddress()).thenReturn("Buyer Address");
	when(request.getBuyerGstin()).thenReturn("Buyer GSTIN");
	when(request.getItems()).thenReturn(Collections.emptyList());

	PdfGenerationServiceImpl pdfGenerationService = new PdfGenerationServiceImpl();

	ByteArrayOutputStream outputStream = pdfGenerationService.generateByteArray(request);

	Assertions.assertNotNull(outputStream);
    }

    private PdfGeneration createSampleRequest() {
	PdfGeneration request = new PdfGeneration();
	request.setSeller("Seller Name");
	request.setSellerAddress("Seller Address");
	request.setSellerGstin("Seller GSTIN");
	request.setBuyer("Buyer Name");
	request.setBuyerAddress("Buyer Address");
	request.setBuyerGstin("Buyer GSTIN");

	List<Item> items = new ArrayList<>();
	items.add(new Item("Item 1", "1", 100.0, 100.0));
	items.add(new Item("Item 2", "2", 200.0, 400.0));
	request.setItems(items);

	return request;
    }
}
