package org.jluc.ctr.tools.calendrier.model;

import java.util.Date;

import org.jluc.ctr.tools.calendrier.ihm.CalendrierCTRController;

import javafx.beans.property.SimpleStringProperty;

public class Evenement implements Comparable<Evenement> {
	public static final String UUID_COL_NAME = "UUID";
	public static final String DATEDEMANDE_COL_NAME = "DateDemande";
	public static final String DATEDEBUT_COL_NAME = "DateDebut";
	public static final String DATEFIN_COL_NAME = "DateFin";
	public static final String UUIDTYPE_COL_NAME = "idType";
	public static final String UUIDDEMANDEUR_COL_NAME = "idDemandeur";
	public static final String UUIDPARTENAIRE_COL_NAME = "idPartenaire";
	public static final String UUIDCLUBSTRUCTURE_COL_NAME = "idClubStructure";
	public static final String MAIL_COL_NAME = "MailContact";
	public static final String LIEU_COL_NAME = "Lieu";
	public static final String UUIDPRESIDENTJURY_COL_NAME = "idPresidentJury";
	public static final String UUIDDELEGUECTR_COL_NAME = "idDelegueCTR";
	public static final String UUIDREPCIBPL_COL_NAME = "idRepCIBPL";
	public static final String STATUT_COL_NAME = "Statut";
	public static final String DATEVALIDATION_COL_NAME = "DateValidation";

	private String mUUID;
	private Date mDateDemande;
	private Date mDateDebut;
	private Date mDateFin;
	private TypeEvenement mType;
	private Demandeur mDemandeur;
	private Demandeur mPartenaire;
	private String mMailContact;
	private String mLieu;
	private Moniteur mPresidentJury;
	private Moniteur mDelegueCTR;
	private Moniteur mRepCIBPL;
	private Status mStatut;
	private Date mDateValidation;
	private ClubStructure mOrganisateur;

	private SimpleStringProperty typeFX;
	private SimpleStringProperty demandeurFX;
	private SimpleStringProperty lieuFX;
	private SimpleStringProperty dateDebutFX;
	private SimpleStringProperty dateFinFX;

	public Evenement(Date dateDemande, Date dateDebut, Date dateFin, TypeEvenement type, Demandeur demandeur,
			Demandeur partenaire, String mail, String lieu) {
		mDateDemande = dateDemande;
		mDateDebut = dateDebut;
		mDateFin = dateFin;
		mType = type;
		mDemandeur = demandeur;
		mPartenaire = partenaire;
		mMailContact = mail;
		mLieu = lieu;
		demandeurFX = new SimpleStringProperty(mDemandeur.toString());
		typeFX = new SimpleStringProperty(mType.toString());
		dateFinFX = new SimpleStringProperty(CalendrierCTRController.DATE_FORMAT_TO_DISPLAY.format(mDateFin));
		dateDebutFX = new SimpleStringProperty(CalendrierCTRController.DATE_FORMAT_TO_DISPLAY.format(mDateDebut));
		lieuFX = new SimpleStringProperty(mLieu);

		mUUID = "evt-" + mDateDemande.getTime() + "-" + mType.getName();
	}

	public Evenement(String uuid, Date dateDemande, Date dateDebut, Date dateFin, TypeEvenement type,
			Demandeur demandeur, Demandeur partenaire, String mail, String lieu, ClubStructure organisateur) {
		mDateDemande = dateDemande;
		mDateDebut = dateDebut;
		mDateFin = dateFin;
		mType = type;
		mDemandeur = demandeur;
		mPartenaire = partenaire;
		mMailContact = mail;
		mLieu = lieu;
		mOrganisateur = organisateur;
		demandeurFX = new SimpleStringProperty(mDemandeur.toString());
		typeFX = new SimpleStringProperty(mType.toString());
		dateFinFX = new SimpleStringProperty(CalendrierCTRController.DATE_FORMAT_TO_DISPLAY.format(mDateFin));
		dateDebutFX = new SimpleStringProperty(CalendrierCTRController.DATE_FORMAT_TO_DISPLAY.format(mDateDebut));
		lieuFX = new SimpleStringProperty(mLieu);

		mUUID = uuid;
	}

	public Evenement(String uuid, Date dateDemande, Date dateDebut, Date dateFin, TypeEvenement type,
			Demandeur demandeur, Demandeur partenaire, String mail, String lieu, ClubStructure organisateur,
			Status status, Date dateValidation) {
		mDateDemande = dateDemande;
		mDateDebut = dateDebut;
		mDateFin = dateFin;
		mType = type;
		mDemandeur = demandeur;
		mPartenaire = partenaire;
		mMailContact = mail;
		mLieu = lieu;
		mStatut = status;
		mDateValidation = dateValidation;
		mOrganisateur = organisateur;
		demandeurFX = new SimpleStringProperty(mDemandeur.toString());
		typeFX = new SimpleStringProperty(mType.toString());
		dateFinFX = new SimpleStringProperty(CalendrierCTRController.DATE_FORMAT_TO_DISPLAY.format(mDateFin));
		dateDebutFX = new SimpleStringProperty(CalendrierCTRController.DATE_FORMAT_TO_DISPLAY.format(mDateDebut));
		lieuFX = new SimpleStringProperty(mLieu);

		mUUID = uuid;
	}

	public Evenement(Date dateDemande, Date dateDebut, Date dateFin, String type, String demandeur, String partenaire,
			String mail, String lieu, String organisateur) {
		mDateDemande = dateDemande;
		mDateDebut = dateDebut;
		mDateFin = dateFin;
		mType = Calendrier.getInstance().findTypeEvent(type);
		mDemandeur = Calendrier.getInstance().findDemandeur(demandeur);
		mPartenaire = Calendrier.getInstance().findDemandeur(partenaire);
		mMailContact = mail;
		mLieu = lieu;
		mOrganisateur = Calendrier.getInstance().findClubStructure(organisateur);
		demandeurFX = new SimpleStringProperty(mDemandeur.toString());
		typeFX = new SimpleStringProperty(mType.toString());
		dateFinFX = new SimpleStringProperty(CalendrierCTRController.DATE_FORMAT_TO_DISPLAY.format(mDateFin));
		dateDebutFX = new SimpleStringProperty(CalendrierCTRController.DATE_FORMAT_TO_DISPLAY.format(mDateDebut));
		lieuFX = new SimpleStringProperty(mLieu);

		mUUID = "evt-" + mDateDemande.getTime() + "-" + mType.getName();
	}

	/**
	 * @return the mDateDemande
	 */
	public Date getDateDemande() {
		return mDateDemande;
	}

	/**
	 * @param mDateDemande the mDateDemande to set
	 */
	public void setDateDemande(Date mDateDemande) {
		this.mDateDemande = mDateDemande;
	}

	/**
	 * @return the mDateDebut
	 */
	public Date getDateDebut() {
		return mDateDebut;
	}

	/**
	 * @param mDateDebut the mDateDebut to set
	 */
	public void setDateDebut(Date mDateDebut) {
		this.mDateDebut = mDateDebut;
	}

	/**
	 * @return the mDateFin
	 */
	public Date getDateFin() {
		return mDateFin;
	}

	/**
	 * @param mDateFin the mDateFin to set
	 */
	public void setDateFin(Date mDateFin) {
		this.mDateFin = mDateFin;
	}

	/**
	 * @return the mType
	 */
	public TypeEvenement getType() {
		return mType;
	}

	/**
	 * @param mType the mType to set
	 */
	public void setType(TypeEvenement mType) {
		this.mType = mType;
	}

	/**
	 * @return the mDemandeur
	 */
	public Demandeur getDemandeur() {
		return mDemandeur;
	}

	/**
	 * @param mDemandeur the mDemandeur to set
	 */
	public void setDemandeur(Demandeur mDemandeur) {
		this.mDemandeur = mDemandeur;
	}

	/**
	 * @return the mPartenaire
	 */
	public Demandeur getPartenaire() {
		return mPartenaire;
	}

	/**
	 * @param mPartenaire the mPartenaire to set
	 */
	public void setPartenaire(Demandeur mPartenaire) {
		this.mPartenaire = mPartenaire;
	}

	/**
	 * @return the mMailContact
	 */
	public String getContact() {
		return mMailContact;
	}

	/**
	 * @param mMailContact the mMailContact to set
	 */
	public void setContact(String mMailContact) {
		this.mMailContact = mMailContact;
	}

	/**
	 * @return the mLieu
	 */
	public String getLieu() {
		return mLieu;
	}

	/**
	 * @param mLieu the mLieu to set
	 */
	public void setLieu(String mLieu) {
		this.mLieu = mLieu;
	}

	/**
	 * @return the mPresidentJury
	 */
	public Moniteur getPresidentJury() {
		return mPresidentJury;
	}

	/**
	 * @param mPresidentJury the mPresidentJury to set
	 */
	public void setPresidentJury(Moniteur mPresidentJury) {
		this.mPresidentJury = mPresidentJury;
	}

	/**
	 * @return the mDelegueCTR
	 */
	public Moniteur getDelegueCTR() {
		return mDelegueCTR;
	}

	/**
	 * @param mDelegueCTR the mDelegueCTR to set
	 */
	public void setDelegueCTR(Moniteur mDelegueCTR) {
		this.mDelegueCTR = mDelegueCTR;
	}

	/**
	 * @return the mRepCIBPL
	 */
	public Moniteur getRepCIBPL() {
		return mRepCIBPL;
	}

	/**
	 * @param mRepCIBPL the mRepCIBPL to set
	 */
	public void setRepCIBPL(Moniteur mRepCIBPL) {
		this.mRepCIBPL = mRepCIBPL;
	}

	/**
	 * @return the mStatut
	 */
	public Status getStatut() {
		return mStatut;
	}

	/**
	 * @param mStatut the mStatut to set
	 */
	public void setStatut(Status mStatut) {
		this.mStatut = mStatut;
	}

	/**
	 * @return the mDateValidation
	 */
	public Date getDateValidation() {
		return mDateValidation;
	}

	/**
	 * @param mDateValidation the mDateValidation to set
	 */
	public void setDateValidation(Date mDateValidation) {
		this.mDateValidation = mDateValidation;
	}

	/**
	 * @return the mUUID
	 */
	public String getUUID() {
		return mUUID;
	}

	/**
	 * @param mUUID the mUUID to set
	 */
	public void setUUID(String mUUID) {
		this.mUUID = mUUID;
	}

	public ClubStructure getOrganisateur() {
		return mOrganisateur;
	}

	public void setOrganisateur(ClubStructure organisateur) {
		this.mOrganisateur = organisateur;
	}

	public String getTypeFX() {
		return typeFX.get();
	}

	public String getDemandeurFX() {
		return demandeurFX.get();
	}

	public String getLieuFX() {
		return lieuFX.get();
	}

	public String getDateDebutFX() {
		return dateDebutFX.get();
	}

	public String getDateFinFX() {
		return dateFinFX.get();
	}

	public void setTypeFX(String typeFX) {
		this.typeFX.set(typeFX);
	}

	public void setDemandeurFX(String demandeurFX) {
		this.demandeurFX.set(demandeurFX);
	}

	public void setLieuFX(String lieuFX) {
		this.lieuFX.set(lieuFX);
	}

	public void setDateDebutFX(String dateDebutFX) {
		this.dateDebutFX.set(dateDebutFX);
	}

	public void setDateFinFX(String dateFinFX) {
		this.dateFinFX.set(dateFinFX);
	}

	@Override
	public int compareTo(Evenement event) {
		return this.getDateDemande().compareTo(event.getDateDemande());
	}

	@Override
	public String toString() {
		StringBuilder repBuilder = new StringBuilder();
		repBuilder.append("Evenement :\n");
		repBuilder.append("--> Date de Demande :");
		repBuilder.append(getDateDemande());
		repBuilder.append("\n");
		repBuilder.append("--> ");

		return repBuilder.toString();
	}

	public static String getAttributesFormSQL() {
		StringBuilder res = new StringBuilder();
		res.append("(");
		res.append(UUID_COL_NAME + " VARCHAR(100)");
		res.append(",");
		res.append(DATEDEMANDE_COL_NAME + " INT");
		res.append(",");
		res.append(DATEDEBUT_COL_NAME + " INT");
		res.append(",");
		res.append(DATEFIN_COL_NAME + " INT");
		res.append(",");
		res.append(UUIDTYPE_COL_NAME + " VARCHAR(100)");
		res.append(",");
		res.append(UUIDDEMANDEUR_COL_NAME + " VARCHAR(100)");
		res.append(",");
		res.append(UUIDPARTENAIRE_COL_NAME + " VARCHAR(100)");
		res.append(",");
		res.append(UUIDCLUBSTRUCTURE_COL_NAME + " VARCHAR(100)");
		res.append(",");
		res.append(MAIL_COL_NAME + " VARCHAR(50)");
		res.append(",");
		res.append(LIEU_COL_NAME + " VARCHAR(100)");
		res.append(",");
		res.append(UUIDPRESIDENTJURY_COL_NAME + " VARCHAR(100)");
		res.append(",");
		res.append(UUIDDELEGUECTR_COL_NAME + " VARCHAR(100)");
		res.append(",");
		res.append(UUIDREPCIBPL_COL_NAME + " VARCHAR(100)");
		res.append(",");
		res.append(STATUT_COL_NAME + " INT");
		res.append(",");
		res.append(DATEVALIDATION_COL_NAME + " INT");
		res.append(")");
		return res.toString();
	}
}
