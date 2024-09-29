package org.jluc.ctr.tools.calendrier.ihm;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jluc.ctr.tools.calendrier.model.Calendrier;
import org.jluc.ctr.tools.calendrier.model.ClubStructure;
import org.jluc.ctr.tools.calendrier.model.Demandeur;
import org.jluc.ctr.tools.calendrier.model.Evenement;
import org.jluc.ctr.tools.calendrier.model.Moniteur;
import org.jluc.ctr.tools.calendrier.model.Status;
import org.jluc.ctr.tools.calendrier.model.TypeEvenement;
import org.jluc.ctr.tools.calendrier.tools.bdd.DatabaseService;
import org.jluc.ctr.tools.calendrier.tools.google.calendar.CalendarServices;
import org.jluc.ctr.tools.calendrier.tools.mail.MailServices;

import javafx.application.Preloader.ProgressNotification;

public class CalendrierCTRController {

    private Logger mLogger = LogManager.getLogger(CalendrierCTRController.class);

    public static final SimpleDateFormat DATE_HEURE_FORMAT = new SimpleDateFormat("yyyyMMdd HH:mm");
    public static final SimpleDateFormat DATE_HEURE_FORMAT_TO_DISPLAY = new SimpleDateFormat("dd-MM-yyyy -- HH:mm");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat DATE_FORMAT_TO_DISPLAY = new SimpleDateFormat("dd-MM-yyyy");
    public static final SimpleDateFormat HEURE_FORMAT = new SimpleDateFormat("HHmmss");
    public static final String TABLE_EVENEMENTS = "Evenements";
    public static final String TABLE_DEMANDEURS = "Demandeurs";
    public static final String TABLE_MONITEURS = "Moniteurs";
    public static final String TABLE_TYPES = "TypesEvenements";
    public static final String TABLE_CLUBSTRUCTURE = "ClubStructures";

    public static final Date TODAY = new Date();

    private Calendrier mModel = Calendrier.getInstance();
    private CalendrierCTRFXController mFXController;

    private DatabaseService mDatabaseService;
    private String mBDDPath = CalendrierCTRApplication.DICO_PROPERTIES.getString("app.bdd.path");

    public CalendrierCTRController() throws ClassNotFoundException, SQLException, IOException {
        File dbFile = new File(mBDDPath);
        boolean needToCreateBDD = !dbFile.exists();
        mDatabaseService = new DatabaseService(mBDDPath);
        if (needToCreateBDD) {
            // Creation des tables
            // Table Evenements
            mDatabaseService.createTable(TABLE_EVENEMENTS, Evenement.getAttributesFormSQL());

            // Table Demandeurs
            mDatabaseService.createTable(TABLE_DEMANDEURS, Demandeur.getAttributesFormSQL());

            // Table Moniteurs
            mDatabaseService.createTable(TABLE_MONITEURS, Moniteur.getAttributesFormSQL());

            // Table TypesEvenement
            mDatabaseService.createTable(TABLE_TYPES, TypeEvenement.getAttributesFormSQL());

            // Table ClubStructure
            mDatabaseService.createTable(TABLE_CLUBSTRUCTURE, ClubStructure.getAttributesFormSQL());
        }
    }

    public void init(CalendrierCTRApplication app) {
        app.notifyPreloader(new ProgressNotification(0.65));
        LogManager.getLogger(CalendrierCTRApplication.class).debug("Initialisation de l'application...");
        if (mModel.loadFromBDD(mDatabaseService)) {
            int nbEventsAdded = mModel.updateBDD();
            mLogger.debug("Il y a eu " + nbEventsAdded + " evenements recuperes...");
            app.notifyPreloader(new ProgressNotification(0.75));
        } else {
            // C'est un probleme...'
            LogManager.getLogger(CalendrierCTRApplication.class).error("Probleme durant chargement de la BDD...");
        }
    }

    public void saveBDD() throws SQLException {
        // Sauvegarde de la BDD
        mLogger.debug("Sauvegarde de la BDD en cours...");
        mModel.saveData(mDatabaseService);

    }

    /**
     * @return the mFXController
     */
    public CalendrierCTRFXController getFXController() {
        return mFXController;
    }

    /**
     * @param mFXController
     *            the mFXController to set
     */
    public void setFXController(CalendrierCTRFXController mFXController) {
        this.mFXController = mFXController;
    }

    public void initIHM() {
        mFXController.init(this);
    }

    public Calendrier getModel() {
        return mModel;
    }

    public void validateEvenement(Evenement eventSelected) throws IOException {
        // TODO Faire la mise à jour de l'event + l'envoie du mail + la creation
        // du GoogleCalendar
        eventSelected.setDateValidation(TODAY);
        eventSelected.setStatut(Status.VALIDE);
        mLogger.debug("Préparation de l'eMail de validation");
        try {
            MailServices.sendValidationMessage(eventSelected);
        } catch (EmailException | MalformedURLException e) {
            mLogger.error("Pb d'envoie de mail", e);
        }

        mLogger.debug("eMail de validation envoyé");

        mLogger.debug("Préparation de l'ajout de l'evenement dans les calendriers");
        CalendarServices.getInstance().addEvent(eventSelected);
        mLogger.debug("evenement ajoute dans le calendrier");
    }

    public void manageErrors() {
        mFXController.manageErrors();
    }
}
