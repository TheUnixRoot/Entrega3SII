/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.toPDF;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import grupoj.prentrega1.Evento;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanp
 */
public class PdfCreator {
    
    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 16, Font.NORMAL);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

    private ByteArrayOutputStream baosPdf;
    
    public PdfCreator(Evento ev) {
        Document doc = new Document();
        try {
            baosPdf = new ByteArrayOutputStream();
            PdfWriter.getInstance(doc, baosPdf);
            doc.open();
            addEvent(doc, ev);
            doc.close();
        } catch (DocumentException ex) {
            Logger.getLogger(PdfCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PdfCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void addEvent(Document doc, Evento ev) throws DocumentException, BadElementException, IOException {
        doc.addTitle(ev.getNombre());
        Paragraph evento = new Paragraph();
        Paragraph title = new Paragraph(ev.getNombre(), catFont);
        title.add("  ");
        if(ev.getMultimedia() != null) {
            Image img = Image.getInstance(ev.getMultimedia());
            img.scaleToFit(300, 300);
            title.add(img);
        }
        Date d = new Date(ev.getFecha_inicio().getTime());
        d.setHours(ev.getHora().getHours());
        d.setMinutes(ev.getHora().getMinutes());
        evento.add(title);
        evento.add(new Paragraph(" "));
        evento.add(new Paragraph(ev.getDescripcion(), subFont));
        evento.add(new Paragraph(" "));
        evento.add(new Paragraph(ev.getOcurre_in().getNombre(), subFont));
        evento.add(new Paragraph(" "));
        evento.add(new Paragraph("Precio " + String.valueOf(ev.getPrecio()), subFont));
        evento.add(new Paragraph(" "));
        evento.add(new Paragraph(ev.getDescripcion(), subFont));
        evento.add(new Paragraph(" "));
        evento.add(new Paragraph("Fecha de inicio: " + new SimpleDateFormat("dd/MM/yyyy-HH:mm").format(d), subFont));
        evento.add(new Paragraph("Fecha de fin:" + new SimpleDateFormat("dd/MM/yyyy-HH:mm").format(ev.getFecha_fin()), subFont));
        evento.add(new Paragraph(" "));
        evento.add(new Paragraph(ev.getDonde_comprar(), smallBold));
        evento.add(new Paragraph(" "));
        doc.add(evento);
    }
    
    public byte[] getStream() {
        return baosPdf.toByteArray();
    }
}
