package com.mandiri.controllers;

import com.mandiri.PDFExporter.PDFExporterClass;
import com.mandiri.PDFExporter.TableClass;
import com.mandiri.PDFExporter.TextClass;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
@RestController
@RequestMapping("/api/PayLater")
public class PayLaterController {
    @GetMapping("/pdf")
    public String exportToPdf() throws IOException {
        PDFExporterClass pdfExporterClass = new PDFExporterClass();
        pdfExporterClass.exportToPdf();
        return "PDF Successfully Created.";
    }
}
