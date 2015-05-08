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
            case "Der kunne ikke oprettet forbindelse til databasen.":
                message = "Der kunne ikke oprettet forbindelse til databasen. "
                        + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                        + "for få dette fixet<br/>(Husk at have maden klar;)!)!";
                break;
            case "Der kunne ikke hentes massagetyper fra databasen.":
                message = "Der kunne ikke hentes massagetyper fra databasen. "
                        + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                        + "for få dette fixet.<br/>(Husk at have maden klar;)!)!";
                break;
            case "Der kunne ikke hentes kunder fra databasen.":
                message = "Der kunne ikke hentes kunder fra databasen. "
                        + "<br/>Programmet kan godt bruges, men anbefales ikke.<br/> Kontakt Annette, "
                        + "for få dette fixet<br/>(Husk at have maden klar;)!)!";
                break;
            case "Kunden kunne ikke gemmes i databasen.":
                message = "Kunden kunne ikke gemmes i databasen. "
                        + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                        + "for få dette fixet<br/>(Husk at have maden klar;)!)!";
                break;
            case "Kunden kunne ikke ændres i databasen.":
                message = "Kunden kunne ikke ændres i databasen. "
                        + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                        + "for få dette fixet<br/>(Husk at have maden klar;)!)!";
                break;
            case "Aftalen kunne ikke redigeres.":
                message = "Aftalen kunne ikke redigeres. "
                        + "<br/> Kontakt Annette, for få dette fixet<br/>"
                        + "(Husk at have maden klar;)!)!";
                break;
            case "Der kunne ikke hentes aftaler for denne uge.":
                message = "Der kunne ikke hentes aftaler for denne uge. "
                    + "<br/>Programmet kan godt bruges,men anbefales ikke.<br/> "
                    + "Kontakt Annette, for få dette fixet<br/>(Husk at have maden klar;)!)!";
                break;
                case "Aftalen kunne ikke slettes.":
                    message = "Aftalen kunne ikke slettes. <br/> Kontakt Annette, "
                    + "for få dette fixet<br/>(Husk at have maden klar;)!)!";
                    break;
            default:
                message = "Fejl kendes ikke "
                        + "<br/> Kontakt Annette, for få dette fixet<br/>"
                        + "(Husk at have maden klar;)!)!";
        }
        return message;
    }


}
