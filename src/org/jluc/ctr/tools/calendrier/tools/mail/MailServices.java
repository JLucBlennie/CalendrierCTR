package org.jluc.ctr.tools.calendrier.tools.mail;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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

    public static void sendValidationMessage(Evenement event) throws EmailException, MalformedURLException {
        String htmlValidationMsg = CalendrierCTRApplication.DICO_PROPERTIES.getString("app.mail.validation");
        String logoPath = SplashView.SPLASH_IMAGE_PATH;
        String signPath = "./resources/images/sign.png";
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.webmo.fr");
        email.setSmtpPort(587);
        // Récupération du compte
        List<StructPwd> comptes = PwdUtils.getInstance().readPwdFile();
        email.setAuthenticator(new DefaultAuthenticator(comptes.get(0).getLogin(), comptes.get(0).getPwd()));
        // email.setSSLOnConnect(true);
        email.setFrom(comptes.get(0).getLogin());
        email.setSubject(MessageFormat.format("Validation de la demande de {0}", event.getType().getName()));
        File logoFile = new File(logoPath);
        URL url = logoFile.toURI().toURL();
        String cid = email.embed(url, "Logo CTR");
        File signFile = new File(signPath);
        URL signURL = signFile.toURI().toURL();
        String cidSign = email.embed(signURL, "Signature");

        // set the html message
        email.setHtmlMsg(MessageFormat.format(htmlValidationMsg, cid, event.getType().getName(), DATE_FORMAT.format(event.getDateDemande()), DATE_FORMAT.format(event.getDateDebut()),
                DATE_FORMAT.format(event.getDateFin()), event.getOrganisateur().getName(), DATE_FORMAT.format(event.getDateValidation()), cidSign));
        email.setTextMsg(MessageFormat.format("Validation de la demande de {0}", event.getType().getName()));
        email.addTo(event.getContact());
        // if (event.getPartenaire() != null)
        email.addCc("pdtctr@ctrbpl.org");
        email.addCc("website@ctrbpl.org");
        email.send();
    }
}
