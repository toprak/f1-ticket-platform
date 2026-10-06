package com.f1istanbul.util;

import com.f1istanbul.model.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Component
public class PdfGenerator {

    public byte[] generateTicketPdf(Ticket ticket) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        
        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24);
            Font infoFont = FontFactory.getFont(FontFactory.HELVETICA, 14);
            Font barcodeFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 16);

            Paragraph title = new Paragraph("F1 TURKISH GRAND PRIX", titleFont);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            title.setSpacingAfter(30);
            document.add(title);

            document.add(new Paragraph("Ad Soyad: " + ticket.getCustomer().getFirstName() + " " + ticket.getCustomer().getLastName(), infoFont));
            document.add(new Paragraph("Tribun: " + ticket.getGrandstand(), infoFont));
            document.add(new Paragraph("Koltuk: " + ticket.getSeatNumber(), infoFont));
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            document.add(new Paragraph("Satin Alma Tarihi: " + ticket.getPurchaseDate().format(formatter), infoFont));
            
            Paragraph barcodePara = new Paragraph("BARKOD: " + ticket.getBarcode(), barcodeFont);
            barcodePara.setAlignment(Paragraph.ALIGN_CENTER);
            barcodePara.setSpacingBefore(40);
            document.add(barcodePara);

            // QR Kod Ekleme (Bilet numarası/barkod verisini içeren)
            com.google.zxing.qrcode.QRCodeWriter qrCodeWriter = new com.google.zxing.qrcode.QRCodeWriter();
            com.google.zxing.common.BitMatrix bitMatrix = qrCodeWriter.encode("https://f1istanbul.com/verify/" + ticket.getBarcode(), com.google.zxing.BarcodeFormat.QR_CODE, 150, 150);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            com.google.zxing.client.j2se.MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            
            com.lowagie.text.Image qrImage = com.lowagie.text.Image.getInstance(pngOutputStream.toByteArray());
            qrImage.setAlignment(com.lowagie.text.Image.ALIGN_CENTER);
            qrImage.setSpacingBefore(20);
            document.add(qrImage);

            document.close();
        } catch (Exception e) {
            throw new RuntimeException("PDF uretilirken hata olustu: " + e.getMessage());
        }

        return out.toByteArray();
    }
}
