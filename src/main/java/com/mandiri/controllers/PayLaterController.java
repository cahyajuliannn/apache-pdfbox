package com.mandiri.controllers;

import com.mandiri.PDFExporter.PDFExporterClass;
import java.awt.*;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
