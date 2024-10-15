package org.jluc.ctr.tools.calendrier.tools.mail;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
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

    public static void sendValidationMessage(Evenement event) throws EmailException, MalformedURLException, FileNotFoundException {
        String htmlValidationMsg = CalendrierCTRApplication.DICO_PROPERTIES.getString("app.mail.validation");
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.webmo.fr");
        email.setSmtpPort(587);
        // Récupération du compte
        List<StructPwd> comptes = PwdUtils.getInstance().readPwdFile();
        email.setAuthenticator(new DefaultAuthenticator(comptes.get(0).getLogin(), comptes.get(0).getPwd()));
        // email.setSSLOnConnect(true);
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
                DATE_FORMAT.format(event.getDateFin()), event.getOrganisateur().getName(),
                DATE_FORMAT.format(event.getDateValidation())));
        email.setTextMsg(MessageFormat.format("Validation de la demande de {0}", event.getType().getName()));
        email.addTo(event.getContact());
        // email.addTo("jluc.blennie@gmail.com");
        // if (event.getPartenaire() != null)
        email.addCc("pdtctr@ctrbpl.org");
        email.addCc("website@ctrbpl.org");
        email.send();
    }
}
