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
public class ErrorModel {

    private static ErrorModel em;

    private ErrorModel() {

    }

    public static ErrorModel getInstance() {
        if (em == null) {
            em = new ErrorModel();
        }
        return em;
    }

    public String getMessage(String error) {
        String message = "";
        switch (error) {
            case "Fejl i forbindelse til databasen.":
                message = "Der kunne ikke oprettet forbindelse til databasen. "
                        + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                        + "for få dette fixet<br/>(Husk at have maden klar;)!)!";
                break;
            case "Fejl i hentning af massagetyper.":
                message = "Der kunne ikke hentes massagetyper fra databasen. "
                        + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                        + "for få dette fixet.<br/>(Husk at have maden klar;)!)!";
                break;
            case "Fejl i hentning af kunder.":
                message = "Der kunne ikke hentes kunder fra databasen. "
                        + "<br/>Programmet kan godt bruges, men anbefales ikke.<br/> Kontakt Annette, "
                        + "for få dette fixet<br/>(Husk at have maden klar;)!)!";
                break;
            case "Fejl i gemme kunder.":
                message = "Kunden kunne ikke gemmes i databasen. "
                        + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                        + "for få dette fixet<br/>(Husk at have maden klar;)!)!";
                break;
            case "Fejl i ændring af kunder.":
                message = "Kunden kunne ikke ændres i databasen. "
                        + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                        + "for få dette fixet<br/>(Husk at have maden klar;)!)!";
                break;
            case "Fejl i redigering af aftaler.":
                message = "Aftalen kunne ikke redigeres. "
                        + "<br/> Kontakt Annette, for få dette fixet<br/>"
                        + "(Husk at have maden klar;)!)!";
                break;
            case "Fejl i hentning af aftaler for ugen.":
                message = "Der kunne ikke hentes aftaler for denne uge. "
                        + "<br/>Programmet kan godt bruges,men anbefales ikke.<br/> "
                        + "Kontakt Annette, for få dette fixet<br/>(Husk at have maden klar;)!)!";
                break;
            case "Fejl i sletning af aftalen.":
                message = "Aftalen kunne ikke slettes. <br/> Kontakt Annette, "
                        + "for få dette fixet<br/>(Husk at have maden klar;)!)!";
                break;
            case "Fejl i hentning af datoer.":
                message = "Der kunne ikke hentes datoer fra databasen. "
                        + "<br/>Programmet kan godt bruges, men anbefales ikke.<br/> Kontakt Annette, "
                        + "for få dette fixet<br/>(Husk at have maden klar;)!)!";
                break;
            case "Fejl i at printe.":
                message = "Exception occured in ReceiptPanel - JButton3 - print ";
                break;
            case "Fejl i hentning af categories.":
                message = "Der kunne ikke hentes categories fra databasen. "
                        + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                        + "for få dette fixet<br/>(Husk at have maden klar;)!)!";
                break;
            case "Fejl i transport parsing.":
                message = "Feltet km må kun indeholde tal";
                break;
            default:
                message = "Fejl kendes ikke."
                        + "<br/> Kontakt Annette, for få dette fixet<br/>"
                        + "(Husk at have maden klar;)!)!" + error;
                break;
        }
        return message;
    }

}
