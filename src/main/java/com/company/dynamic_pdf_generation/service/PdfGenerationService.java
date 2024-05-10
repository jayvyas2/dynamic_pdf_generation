package com.company.dynamic_pdf_generation.service;

import com.company.dynamic_pdf_generation.dto.PdfGeneration;
import com.itextpdf.io.source.ByteArrayOutputStream;

public interface PdfGenerationService {

    public ByteArrayOutputStream generateByteArray(PdfGeneration request);
}
