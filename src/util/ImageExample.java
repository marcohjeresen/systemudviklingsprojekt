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
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import java.awt.GraphicsEnvironment;
import java.io.File;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import javax.xml.soap.Text;

public class ImageExample {
    private Calendar date;
    private int settings;
    private ArrayList<String> courses;
    private String time;
    private String eventAddress;
    private int totalPrice;

    public ImageExample(Calendar date, int settings, ArrayList<String> courses, String time, String eventAddress, int totalPrice) {
        this.date = date;
        this.settings = settings;
        this.courses = courses;
        this.time = time;
        this.eventAddress = eventAddress;
        this.totalPrice = totalPrice;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.getAllFonts();
        FontFactory.register("C:/Windows/Fonts/ARLRDBD.TTF", "Arial Rounded");
        Font font = FontFactory.getFont("Arial Rounded", 22, Font.NORMAL, new BaseColor(51, 102, 102));
        Document document = new Document();
        ArrayList<String> list = new ArrayList<>();
        list.add("h");
        list.add("he");
        list.add("hej");
        list.add("hej m");
        list.add("hej me");
        list.add("hej med");
        list.add("hej med d");
        list.add("hej med di");
        list.add("hej med dig");

        System.out.println("hej");
        try {
            FileOutputStream file = new FileOutputStream(new File("mailfolder/", "grill" + date + ".pdf"));

            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();

            Image img = Image.getInstance("src/pictures/cirkles.png");
            if (img.getScaledWidth() > 300 || img.getScaledHeight() > 300) {
                img.scaleToFit(277, 277);
            }
            img.setAbsolutePosition(40, PageSize.A4.getHeight()-img.getHeight());
            document.add(img);
            Chunk chunk = new Chunk("Grillmester 'Frankie'", font);
            chunk.setCharacterSpacing(3);
            Paragraph paragraph = new Paragraph(chunk);
//            Paragraph paragraph = new Paragraph("Grillmester 'Frankie'");
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            paragraph.setSpacingBefore(15);
            document.add(paragraph);
//            image1.setAbsolutePosition(150, 500);
////            document.add(new Paragraph("A Hello World PDF document."));
//            document.add(image1);
//            int count = 0;
//            for (String list1 : list) {
//              Paragraph p = new Paragraph(list1); 
//              p.setLeading(1, count); /* den første er antal rækker og den næste er antal gange rækken skal tykkes*/
//              p.setIndentationLeft(count * 15); /* her bestemmer man hvor lang inde den skal skrive noget.*/
//              document.add(p);
//              count++;
//            }
//            
//            
//
//            
//            

            document.close();
            System.out.println("farvel");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
    }

    
}
