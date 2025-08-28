package org.jluc.ctr.tools.calendrier.tools.mail;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.FileNotFoundException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.jluc.ctr.tools.calendrier.ihm.CalendrierCTRApplication;
import org.jluc.ctr.tools.calendrier.ihm.SplashView;
import org.jluc.ctr.tools.calendrier.model.Evenement;
import org.jluc.ctr.tools.calendrier.tools.mail.pwd.PwdUtils;
import org.jluc.ctr.tools.calendrier.tools.mail.pwd.StructPwd;

public class MailServices {
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy");

    public static void sendValidationMessage(Evenement event)
            throws EmailException, MalformedURLException, FileNotFoundException {
        String htmlValidationMsg = CalendrierCTRApplication.DICO_PROPERTIES.getString("app.mail.validation");
        String smtpServer = CalendrierCTRApplication.DICO_PROPERTIES.getString("app.mail.smtp");
        HtmlEmail email = new HtmlEmail();
        email.setHostName(smtpServer);
        email.setSmtpPort(587);
        // Récupération du compte
        List<StructPwd> comptes = PwdUtils.getInstance().readPwdFile();
        email.setAuthenticator(new DefaultAuthenticator(comptes.get(0).getLogin(), comptes.get(0).getPwd()));
        email.setStartTLSRequired(true);

        email.setFrom(comptes.get(0).getLogin());
        email.setSubject(MessageFormat.format("Validation de la demande de {0}", event.getType().getName()));
        URL url = MailServices.class.getResource(SplashView.SPLASH_IMAGE_PATH);
        if (url == null) {
            String msg = "Problème de chargement du logo";
            throw new FileNotFoundException(msg);
        }
        String cid = email.embed(url, "Logo CTR");

        // set the html message
        email.setHtmlMsg(MessageFormat.format(htmlValidationMsg, cid, event.getType().getName(),
                DATE_FORMAT.format(event.getDateDemande()), DATE_FORMAT.format(event.getDateDebut()),
                DATE_FORMAT.format(event.getDateFin()), event.getDemandeur().getName(),
                DATE_FORMAT.format(event.getDateValidation())));
        email.setTextMsg(MessageFormat.format("Validation de la demande de {0}", event.getType().getName()));
        email.addTo(event.getContact());
        // if (event.getPartenaire() != null)
        email.addCc("presidente-technique@cibpl.fr");
        email.addCc("webmaster-technique@cibpl.fr");
        email.send();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            String htmlValidationMsg = CalendrierCTRApplication.DICO_PROPERTIES.getString("app.mail.validation");
            String smtpServer = CalendrierCTRApplication.DICO_PROPERTIES.getString("app.mail.smtp");
            HtmlEmail email = new HtmlEmail();
            email.setHostName(smtpServer);
            email.setSmtpPort(587);
            // Récupération du compte
            List<StructPwd> comptes = PwdUtils.getInstance().readPwdFile();
            System.out.println("login : " + comptes.get(0).getLogin() + " - password : " + comptes.get(0).getPwd());
            email.setAuthenticator(new DefaultAuthenticator(comptes.get(0).getLogin(), comptes.get(0).getPwd()));
            // email.setSSLOnConnect(true);
            email.setStartTLSRequired(true);
            try {
                email.setFrom(comptes.get(0).getLogin());
            } catch (EmailException e) {
                e.printStackTrace();
            }
            email.setSubject(MessageFormat.format("Validation de la demande de {0}", "TEST"));
            URL url = MailServices.class.getResource(SplashView.SPLASH_IMAGE_PATH);
            if (url == null) {
                String msg = "Problème de chargement du logo";
                System.out.println(msg);
                return;
            }
            String cid = "";
            try {
                cid = email.embed(url, "Logo CTR");
            } catch (EmailException e) {
                e.printStackTrace();
            }

            // set the html message
            try {
                email.setHtmlMsg(MessageFormat.format(htmlValidationMsg, cid, "TEST",
                        DATE_FORMAT.format(new Date()), DATE_FORMAT.format(new Date()),
                        DATE_FORMAT.format(new Date()), "JLuc",
                        DATE_FORMAT.format(new Date())));
            } catch (EmailException e) {
                e.printStackTrace();
            }
            try {
                email.setTextMsg(MessageFormat.format("Validation de la demande de {0}", "TEST"));
            } catch (EmailException e) {
                e.printStackTrace();
            }
            try {
                email.addTo("jluc.blennie@gmail.com");
            } catch (EmailException e) {
                e.printStackTrace();
            }
            // if (event.getPartenaire() != null)
            try {
                email.addCc("webmaster-technique@cibpl.fr");
            } catch (EmailException e) {
                e.printStackTrace();
            }
            try {
                email.send();
            } catch (EmailException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Pas le bon nombre d'arguments : 0");
        }
    }
}
