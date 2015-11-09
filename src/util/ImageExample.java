/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Mark
 */
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import java.awt.GraphicsEnvironment;
import java.io.File;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ImageExample {

    private Calendar date;
    private int settings;
    private ArrayList<String> courses;
    private String time;
    private String eventAddress;
    private int totalPrice;
    private String comments;
    private String fileName;

    public ImageExample(Calendar date, int settings, ArrayList<String> courses, String time, String eventAddress, int totalPrice, String comments, boolean kunde) throws Exception{
        this.date = date;
        this.settings = settings;
        this.courses = courses;
        this.time = time;
        this.eventAddress = eventAddress;
        this.totalPrice = totalPrice;
        this.comments = comments;
        this.fileName = "";
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.getAllFonts();
        FontFactory.register("C:/Windows/Fonts/ARLRDBD.TTF", "Arial Rounded");
        Font font = FontFactory.getFont("Arial Rounded", 22, Font.NORMAL, new BaseColor(51, 102, 102));
        Document document = new Document();

        System.out.println("Pdf creation startet");
        try {
            if (kunde) {
                fileName = "grill" + new SimpleDateFormat("dd. MMMMM yyyy hhmm").format(date.getTime()) + ".pdf";
            }else{
                fileName = "grill" + new SimpleDateFormat("dd. MMMMM yyyy hhmm").format(date.getTime()) + "Prisliste.pdf";
            }
            
            FileOutputStream file = new FileOutputStream(new File("C:/Users/Mark/Desktop", fileName));
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            
            Image img = Image.getInstance("src/pictures/cirkles.png");
            img.scaleToFit(277, 277);
            img.setAbsolutePosition(40, PageSize.A4.getHeight() - img.getHeight());
            document.add(img);
            Chunk chunk = new Chunk("Grillmester 'Frankie'", font);
            chunk.setCharacterSpacing(3);
            Paragraph header = new Paragraph(chunk);
            header.setAlignment(Element.ALIGN_RIGHT);
            header.setSpacingBefore(15);
            document.add(header);
            Paragraph title = new Paragraph("Tilbud angående d. " + new SimpleDateFormat("dd. MMMMM yyyy").format(date.getTime()) + ".", new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));
            title.setAlignment(Element.ALIGN_LEFT);
            title.setIndentationLeft(235);
            title.setSpacingBefore(100);
            title.setLeading(17);
            document.add(title);
            Paragraph subtitle = new Paragraph("(Arrangement til " + settings + " personer).", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.ITALIC));
            subtitle.setAlignment(Element.ALIGN_LEFT);
            subtitle.setIndentationLeft(235);
            subtitle.setLeading(17);
            document.add(subtitle);
            Paragraph body = new Paragraph("\n\nDer er aftalt:\n\n", new Font(Font.FontFamily.TIMES_ROMAN, 18));
            body.setAlignment(Element.ALIGN_LEFT);
            body.setIndentationLeft(235);
            body.setLeading(17);
            for (String course : courses) {
                body.add(course + "\n\n");
            }
            body.add("\nServeres klokken " + time + " i " + eventAddress + ".");
            document.add(body);
            Paragraph ending = new Paragraph("\n\n\nPris: " + totalPrice + ",-kr.", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD));
            ending.setAlignment(Element.ALIGN_LEFT);
            ending.setIndentationLeft(235);
            ending.setLeading(17);
            document.add(ending);
            Paragraph comment = new Paragraph("\n\n\n"+comments, new Font(Font.FontFamily.TIMES_ROMAN, 18));
            comment.setAlignment(Element.ALIGN_LEFT);
            comment.setIndentationLeft(235);
            comment.setLeading(17);
            document.add(comment);
            Image footerImg = Image.getInstance("src/pictures/grillmester.png");
            footerImg.scaleToFit(210, 210);
            footerImg.setAbsolutePosition(40, 40);
            document.add(footerImg);
            Paragraph contact = new Paragraph("Frank Børge Sund Petersen\nVallensved Bygade 11\nVallensved\n4700 Næstved\n\nMobil: 26236172", new Font(FontFactory.getFont("Gill Sans MT", 16, Font.NORMAL, new BaseColor(51, 102, 102))));
            contact.setAlignment(Element.ALIGN_CENTER);
            contact.setSpacingBefore(-255);
            contact.setLeading(17);
            contact.setIndentationLeft(-305);
            document.add(contact);
            document.close();
            
            System.out.println("Pdf finish");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
    }
    
   
    public String getFileName(){
        return fileName;
    }

}
