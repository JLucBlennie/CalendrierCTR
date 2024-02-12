package org.jluc.ctr.tools.calendrier.ihm;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jluc.ctr.tools.calendrier.model.Calendrier;
import org.jluc.ctr.tools.calendrier.model.ClubStructure;
import org.jluc.ctr.tools.calendrier.model.Demandeur;
import org.jluc.ctr.tools.calendrier.model.Evenement;
import org.jluc.ctr.tools.calendrier.model.Moniteur;
import org.jluc.ctr.tools.calendrier.model.TypeEvenement;
import org.jluc.ctr.tools.calendrier.tools.bdd.DatabaseService;

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
		LogManager.getLogger(CalendrierCTRApplication.class)
				.debug("Initialisation de l'application ==> Load depuis FORMS...");
		if (mModel.loadFromBDD(mDatabaseService)) {
			int nbEventsAdded = mModel.updateBDD();
			mLogger.debug("Il y a eu " + nbEventsAdded + " evenements recuperes...");
			app.notifyPreloader(new ProgressNotification(0.75));
		} else {
			// C'est un probleme...
		}
	}

	public void saveBDD() {
		// TODO : Il faut ici faire une sauvegarde de la BDD
		mLogger.debug("Sauvegarde de la BDD en cours... Pas encore implémenté !!!");
	}

	/**
	 * @return the mFXController
	 */
	public CalendrierCTRFXController getFXController() {
		return mFXController;
	}

	/**
	 * @param mFXController the mFXController to set
	 */
	public void setFXController(CalendrierCTRFXController mFXController) {
		this.mFXController = mFXController;
	}

	public void initIHM() {
		mFXController.init(mModel);
	}

}
