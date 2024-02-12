package org.jluc.ctr.tools.calendrier.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jluc.ctr.tools.calendrier.ihm.CalendrierCTRController;
import org.jluc.ctr.tools.calendrier.tools.bdd.DatabaseService;
import org.jluc.ctr.tools.calendrier.tools.forms.FormsAccessService;

public class Calendrier {

	private Logger mLogger = LogManager.getLogger(Calendrier.class);
	private List<Evenement> mEvents = new ArrayList<Evenement>();
	public List<Demandeur> DEMANDEURS = new ArrayList<Demandeur>();
	public List<Moniteur> MONITEURS = new ArrayList<Moniteur>();
	public List<TypeEvenement> TYPES_EVENEMENT = new ArrayList<TypeEvenement>();
	public List<ClubStructure> CLUBSTRUCTURES = new ArrayList<ClubStructure>();

	private static Calendrier mInstance;

	private Calendrier() {
		// Chargement des dicos depuis la BDD

	}

	public static Calendrier getInstance() {
		if (mInstance == null) {
			mInstance = new Calendrier();
		}
		return mInstance;
	}

	public List<Evenement> getEventsList() {
		return mEvents;
	}

	public int updateBDD() {
		int res = 0;

		// Récupération des évènements à partir de Forms
		mLogger.debug("Chargement depuis FORMS...");
		List<Evenement> events = FormsAccessService.getEventsFromGoogleForms();
		for (Evenement eventForms : events) {
			if (addEvent(eventForms)) {
				res++;
			}
		}
		return res;
	}

	public boolean addEvent(Evenement event) {
		if (!isEventInCalendrier(event.getUUID())) {
			mEvents.add(event);
			mLogger.debug("Evenement ajoute... " + event);
			return true;
		} else {
			mLogger.debug("Evenement existant..." + event);
		}
		return false;
	}

	private boolean isEventInCalendrier(String uuid) {
		for (Evenement event : mEvents) {
			if (event.getUUID().equals(uuid)) {
				return true;
			}
		}
		return false;
	}

	public void stockData() {
		// Sauvegarde en BDD de toutes les donnees : Event + Dicos

	}

	public TypeEvenement findTypeEvent(String type) {
		for (TypeEvenement typeEvent : TYPES_EVENEMENT) {
			if (typeEvent.getValeurForms().equalsIgnoreCase(type)) {
				return typeEvent;
			}
		}
		return null;
	}

	public Demandeur findDemandeur(String demandeur) {
		for (Demandeur demandeurItem : DEMANDEURS) {
			if (demandeur.contains(demandeurItem.getNumeroStructure())) {
				return demandeurItem;
			}
		}
		Demandeur newDemandeur = (demandeur.isEmpty() ? null
				: new Demandeur(demandeur.substring(0, demandeur.lastIndexOf("03")),
						demandeur.substring(demandeur.lastIndexOf("03"))));
		if (newDemandeur != null)
			DEMANDEURS.add(newDemandeur);
		return newDemandeur;
	}

	public ClubStructure findClubStructure(String organisateur) {
		for (ClubStructure organisateurItem : CLUBSTRUCTURES) {
			if (organisateur.equalsIgnoreCase(organisateurItem.getName())) {
				return organisateurItem;
			}
		}
		ClubStructure newOrganisateur = (organisateur.isEmpty() ? null : new ClubStructure(organisateur));
		if (newOrganisateur != null)
			CLUBSTRUCTURES.add(newOrganisateur);
		return newOrganisateur;
	}

	public TypeEvenement findTypeEventByUUID(String uuid) {
		for (TypeEvenement typeEvent : TYPES_EVENEMENT) {
			if (typeEvent.getUUID().equals(uuid)) {
				return typeEvent;
			}
		}
		return null;
	}

	public ClubStructure findClubStructureByUUID(String uuid) {
		for (ClubStructure organisteur : CLUBSTRUCTURES) {
			if (organisteur.getUUID().equals(uuid)) {
				return organisteur;
			}
		}
		return null;
	}

	public Demandeur findDemandeurByUUID(String uuid) {
		for (Demandeur demandeurItem : DEMANDEURS) {
			if (demandeurItem.getUUID().equals(uuid)) {
				return demandeurItem;
			}
		}
		return null;
	}

	public Moniteur findMoniteurByUUID(String uuid) {
		for (Moniteur moniteurItem : MONITEURS) {
			if (moniteurItem.getUUID().equals(uuid)) {
				return moniteurItem;
			}
		}
		return null;
	}

	public boolean loadFromBDD(DatabaseService mDatabaseService) {
		boolean result = true;
		try {
			// recuperation des moniteurs
			List<Map<String, Object>> moniteursrs = mDatabaseService
					.executeSelectFrom(CalendrierCTRController.TABLE_MONITEURS);
			for (Map<String, Object> moniteurrs : moniteursrs) {
				Moniteur moniteur = new Moniteur((String) moniteurrs.get(Moniteur.UUID_COL_NAME),
						(String) moniteurrs.get(Moniteur.LASTNAME_COL_NAME),
						(String) moniteurrs.get(Moniteur.FIRSTNAME_COL_NAME),
						(int) moniteurrs.get(Moniteur.NIVEAU_COL_NAME));
				MONITEURS.add(moniteur);
			}
			// recuperation des demandeurs
			List<Map<String, Object>> demandeursrs = mDatabaseService
					.executeSelectFrom(CalendrierCTRController.TABLE_DEMANDEURS);
			for (Map<String, Object> demandeurrs : demandeursrs) {
				Demandeur demandeur = new Demandeur((String) demandeurrs.get(Demandeur.UUID_COL_NAME),
						(String) demandeurrs.get(Demandeur.NAME_COL_NAME),
						(String) demandeurrs.get(Demandeur.NUMEROSTRUCTURE_COL_NAME));
				DEMANDEURS.add(demandeur);
			}
			// recuperation des types
			List<Map<String, Object>> typesrs = mDatabaseService.executeSelectFrom(CalendrierCTRController.TABLE_TYPES);
			for (Map<String, Object> typers : typesrs) {
				TypeEvenement typeEvent = new TypeEvenement((String) typers.get(TypeEvenement.UUID_COL_NAME),
						(String) typers.get(TypeEvenement.NAME_COL_NAME),
						TypeActivite.values()[(int) typers.get(TypeEvenement.TYPE_COL_NAME)],
						(String) typers.get(TypeEvenement.VALEUR_COL_NAME));
				TYPES_EVENEMENT.add(typeEvent);
			}
			// recuperation des evenements
			List<Map<String, Object>> eventsrs = mDatabaseService
					.executeSelectFrom(CalendrierCTRController.TABLE_EVENEMENTS);
			for (Map<String, Object> eventrs : eventsrs) {
				Evenement event = new Evenement((String) eventrs.get(Evenement.UUID_COL_NAME),
						new Date((int) eventrs.get(Evenement.DATEDEMANDE_COL_NAME)),
						new Date((int) eventrs.get(Evenement.DATEDEBUT_COL_NAME)),
						new Date((int) eventrs.get(Evenement.DATEFIN_COL_NAME)),
						findTypeEventByUUID((String) eventrs.get(Evenement.UUIDTYPE_COL_NAME)),
						findDemandeurByUUID((String) eventrs.get(Evenement.UUIDDEMANDEUR_COL_NAME)),
						findDemandeurByUUID((String) eventrs.get(Evenement.UUIDPARTENAIRE_COL_NAME)),
						(String) eventrs.get(Evenement.MAIL_COL_NAME), (String) eventrs.get(Evenement.LIEU_COL_NAME),
						findClubStructureByUUID((String) eventrs.get(Evenement.UUIDCLUBSTRUCTURE_COL_NAME)),
						Status.values()[(int) eventrs.get(Evenement.STATUT_COL_NAME)],
						new Date((int) eventrs.get(Evenement.DATEVALIDATION_COL_NAME)));
				mEvents.add(event);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}

		return result;
	}

}
