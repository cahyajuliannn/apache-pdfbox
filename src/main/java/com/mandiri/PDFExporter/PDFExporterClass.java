package com.mandiri.PDFExporter;

import java.awt.*;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PDFExporterClass {
  public void exportToPdf() throws IOException {
    PDDocument document = new PDDocument();
    PDPage firstPage = new PDPage(PDRectangle.A4);
    document.addPage(firstPage);

    Integer totalTransaction = 1;
    String datePay = "15 Sep - 14 Okt";

    Format d_Fortmat = new SimpleDateFormat("dd/MM/yyyy");

    int pageWidth = (int) firstPage.getTrimBox().getWidth();
    int pageHeight = (int) firstPage.getTrimBox().getHeight();

    PDPageContentStream contentStream = new PDPageContentStream(document, firstPage);
    TextClass textClass = new TextClass(document, contentStream);

    PDFont font = PDType1Font.TIMES_ROMAN;
    PDFont italicFont = PDType1Font.TIMES_ITALIC;

    topSidePDF(
        document,
        totalTransaction,
        datePay,
        d_Fortmat,
        pageWidth,
        pageHeight,
        contentStream,
        textClass,
        font);

    tableAddCell(document, pageHeight, contentStream, font);

    // Payment Methods
    String[] paymentMethod = {
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit, ",
      "labore et dolore magna aliqua. Ut enim ad minim veniam, ",
      "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
    };

    textClass.addMultiLineText(
        paymentMethod, 15, 25, 180, italicFont, 10, new Color(122, 122, 122));

    String bottomLine = "Terdepan, Terpercaya, Tumbuh Bersama Anda";
    float bottomLineWidth = textClass.getTextWidth(bottomLine, font, 20);

    textClass.addSingleLineText(
        bottomLine, (int) (pageWidth - bottomLineWidth) / 2, 50, italicFont, 20, Color.DARK_GRAY);

    contentStream.close();
    document.save("C:\\PDF\\result\\mandiriPayLater.pdf");
    document.close();
  }

  private void topSidePDF(
      PDDocument document,
      Integer totalTransaction,
      String datePay,
      Format d_Fortmat,
      int pageWidth,
      int pageHeight,
      PDPageContentStream contentStream,
      TextClass textClass,
      PDFont font)
      throws IOException {
    PDImageXObject headImage =
        PDImageXObject.createFromFile("src/main/resources/static/img/livin' logo.png", document);
    contentStream.drawImage(headImage, 12, pageHeight - 98, pageWidth - 482, 88);

    String titleLine = "MANDIRI PAYLATER";
    float titleLineWidth = textClass.getTextWidth(titleLine, font, 20);
    textClass.addSingleLineText(
        titleLine, (int) (pageWidth - titleLineWidth) / 2, pageHeight - 150, font, 20, Color.BLACK);
    textClass.addSingleLineText(
        "Total Transaksi: " + totalTransaction, 25, pageHeight - 274, font, 16, Color.BLACK);

    float dateTextWidth = textClass.getTextWidth("Date: " + d_Fortmat.format(new Date()), font, 16);
    textClass.addSingleLineText(
        "Date: " + datePay,
        (int) (pageWidth - 67 - dateTextWidth),
        pageHeight - 274,
        font,
        16,
        Color.BLACK);
  }

  private void tableAddCell(
      PDDocument document, int pageHeight, PDPageContentStream contentStream, PDFont font)
      throws IOException {
    TableClass tableClass = new TableClass(document, contentStream);

    int[] cellWidths = {250, 290};
    tableClass.setTable(cellWidths, 30, 25, pageHeight - 350);
    tableClass.setTableFont(font, 16, Color.BLACK);

    Color tableHeadColor = new Color(255, 204, 0);
    Color tableBodyColor = new Color(219, 218, 198);

    tableClass.addCell("Total Tagihan", tableHeadColor);
    tableClass.addCell("+Rp51.671", tableBodyColor);

    tableClass.addCell("Total Pengembalian Dana", tableHeadColor);
    tableClass.addCell("Rp0", tableBodyColor);

    tableClass.addCell("Total Pembayaran", tableHeadColor);
    tableClass.addCell("-Rp51.671", tableBodyColor);

    tableClass.addCell("Cicilan", null);
    tableClass.addCell("", null);

    tableClass.addCell("[Promo] Paket Simpati", tableHeadColor);
    tableClass.addCell("+Rp51.671", tableBodyColor);
  }
}
