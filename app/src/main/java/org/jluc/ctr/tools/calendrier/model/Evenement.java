package org.jluc.ctr.tools.calendrier.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public static final String COMMENT_COL_NAME = "Comment";
    public static final String CALENDAR_EVENT_ID_COL_NAME = "CalendarEventId";

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
    private Status mStatut = Status.DEMANDE;
    private Date mDateValidation;
    private ClubStructure mOrganisateur;
    private boolean mIsModified = false;
    private boolean mIsNew = false;
    private boolean mIsSelected = false;
    private String mComment;
    private String mCalendarEventId;

    private SimpleStringProperty typeFX;
    private SimpleStringProperty demandeurFX;
    private SimpleStringProperty lieuFX;
    private SimpleStringProperty dateDebutFX;
    private SimpleStringProperty dateFinFX;

    public Evenement(Date dateDemande, Date dateDebut, Date dateFin, TypeEvenement type, Demandeur demandeur,
            Demandeur partenaire, String mail, String lieu, String comment) {
        mDateDemande = dateDemande;
        mDateDebut = dateDebut;
        mDateFin = dateFin;
        mType = type;
        mDemandeur = demandeur;
        mPartenaire = partenaire;
        mMailContact = mail;
        mLieu = lieu;
        mComment = comment;
        demandeurFX = new SimpleStringProperty(mDemandeur.toString());
        typeFX = new SimpleStringProperty(mType.toString());
        dateFinFX = new SimpleStringProperty(CalendrierCTRController.DATE_FORMAT_TO_DISPLAY.format(mDateFin));
        dateDebutFX = new SimpleStringProperty(CalendrierCTRController.DATE_FORMAT_TO_DISPLAY.format(mDateDebut));
        lieuFX = new SimpleStringProperty(mLieu);

        mUUID = "evt-" + mDateDemande.getTime() + "-" + mType.getName().replace(" ", "");
        mIsNew = true;
    }

    public Evenement(String uuid, Date dateDemande, Date dateDebut, Date dateFin, TypeEvenement type,
            Demandeur demandeur, Demandeur partenaire, String mail, String lieu, ClubStructure organisateur,
            String comment) {
        mDateDemande = dateDemande;
        mDateDebut = dateDebut;
        mDateFin = dateFin;
        mType = type;
        mDemandeur = demandeur;
        mPartenaire = partenaire;
        mMailContact = mail;
        mLieu = lieu;
        mComment = comment;
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
            Status status, Date dateValidation, String comment) {
        mDateDemande = dateDemande;
        mDateDebut = dateDebut;
        mDateFin = dateFin;
        mType = type;
        mDemandeur = demandeur;
        mPartenaire = partenaire;
        mMailContact = mail;
        mLieu = lieu;
        mStatut = status;
        mComment = comment;
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
            String mail, String lieu, String organisateur, String comment) {
        mDateDemande = dateDemande;
        mDateDebut = dateDebut;
        mDateFin = dateFin;
        mType = Calendrier.getInstance().findTypeEvent(type);
        mDemandeur = Calendrier.getInstance().findDemandeur(demandeur);
        mPartenaire = Calendrier.getInstance().findDemandeur(partenaire);
        mMailContact = mail;
        mLieu = lieu;
        mComment = comment;
        mOrganisateur = Calendrier.getInstance().findClubStructure(organisateur);
        demandeurFX = new SimpleStringProperty(mDemandeur.toString());
        typeFX = new SimpleStringProperty(mType.toString());
        dateFinFX = new SimpleStringProperty(CalendrierCTRController.DATE_FORMAT_TO_DISPLAY.format(mDateFin));
        dateDebutFX = new SimpleStringProperty(CalendrierCTRController.DATE_FORMAT_TO_DISPLAY.format(mDateDebut));
        lieuFX = new SimpleStringProperty(mLieu);

        mUUID = "evt-" + mDateDemande.getTime() + "-" + mType.getName().replace(" ", "");
        mIsNew = true;
    }

    /**
     * @return the mDateDemande
     */
    public Date getDateDemande() {
        return mDateDemande;
    }

    /**
     * @param mDateDemande
     *                     the mDateDemande to set
     */
    public void setDateDemande(Date mDateDemande) {
        this.mDateDemande = mDateDemande;
        mIsModified = true;
    }

    /**
     * @return the mDateDebut
     */
    public Date getDateDebut() {
        return mDateDebut;
    }

    /**
     * @param mDateDebut
     *                   the mDateDebut to set
     */
    public void setDateDebut(Date mDateDebut) {
        this.mDateDebut = mDateDebut;
        mIsModified = true;
    }

    /**
     * @return the mDateFin
     */
    public Date getDateFin() {
        return mDateFin;
    }

    /**
     * @param mDateFin
     *                 the mDateFin to set
     */
    public void setDateFin(Date mDateFin) {
        this.mDateFin = mDateFin;
        mIsModified = true;
    }

    /**
     * @return the mType
     */
    public TypeEvenement getType() {
        return mType;
    }

    /**
     * @param mType
     *              the mType to set
     */
    public void setType(TypeEvenement mType) {
        this.mType = mType;
        mIsModified = true;
    }

    /**
     * @return the mDemandeur
     */
    public Demandeur getDemandeur() {
        return mDemandeur;
    }

    /**
     * @param mDemandeur
     *                   the mDemandeur to set
     */
    public void setDemandeur(Demandeur mDemandeur) {
        this.mDemandeur = mDemandeur;
        mIsModified = true;
    }

    /**
     * @return the mPartenaire
     */
    public Demandeur getPartenaire() {
        return mPartenaire;
    }

    /**
     * @param mPartenaire
     *                    the mPartenaire to set
     */
    public void setPartenaire(Demandeur mPartenaire) {
        this.mPartenaire = mPartenaire;
        mIsModified = true;
    }

    /**
     * @return the mMailContact
     */
    public String getContact() {
        return mMailContact;
    }

    /**
     * @param mMailContact
     *                     the mMailContact to set
     */
    public void setContact(String mMailContact) {
        this.mMailContact = mMailContact;
        mIsModified = true;
    }

    /**
     * @return the mLieu
     */
    public String getLieu() {
        return mLieu;
    }

    /**
     * @param mLieu
     *              the mLieu to set
     */
    public void setLieu(String mLieu) {
        this.mLieu = mLieu;
        mIsModified = true;
    }

    /**
     * @return the mPresidentJury
     */
    public Moniteur getPresidentJury() {
        return mPresidentJury;
    }

    /**
     * @param mPresidentJury
     *                       the mPresidentJury to set
     */
    public void setPresidentJury(Moniteur mPresidentJury) {
        this.mPresidentJury = mPresidentJury;
        mIsModified = true;
    }

    /**
     * @return the mDelegueCTR
     */
    public Moniteur getDelegueCTR() {
        return mDelegueCTR;
    }

    /**
     * @param mDelegueCTR
     *                    the mDelegueCTR to set
     */
    public void setDelegueCTR(Moniteur mDelegueCTR) {
        this.mDelegueCTR = mDelegueCTR;
        mIsModified = true;
    }

    /**
     * @return the mRepCIBPL
     */
    public Moniteur getRepCIBPL() {
        return mRepCIBPL;
    }

    /**
     * @param mRepCIBPL
     *                  the mRepCIBPL to set
     */
    public void setRepCIBPL(Moniteur mRepCIBPL) {
        this.mRepCIBPL = mRepCIBPL;
        mIsModified = true;
    }

    /**
     * @return the mStatut
     */
    public Status getStatut() {
        return mStatut;
    }

    /**
     * @param mStatut
     *                the mStatut to set
     */
    public void setStatut(Status mStatut) {
        this.mStatut = mStatut;
        mIsModified = true;
    }

    /**
     * @return the mDateValidation
     */
    public Date getDateValidation() {
        return mDateValidation;
    }

    /**
     * @param mDateValidation
     *                        the mDateValidation to set
     */
    public void setDateValidation(Date mDateValidation) {
        this.mDateValidation = mDateValidation;
        mIsModified = true;
    }

    /**
     * @return the mUUID
     */
    public String getUUID() {
        return mUUID;
    }

    /**
     * @param mUUID
     *              the mUUID to set
     */
    public void setUUID(String mUUID) {
        this.mUUID = mUUID;
        mIsModified = true;
        mIsNew = true;
    }

    public ClubStructure getOrganisateur() {
        return mOrganisateur;
    }

    public void setOrganisateur(ClubStructure organisateur) {
        this.mOrganisateur = organisateur;
        mIsModified = true;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
        mIsModified = true;
    }

    public boolean isModified() {
        return mIsModified;
    }

    public boolean isNew() {
        return mIsNew;
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

    public boolean isSelected() {
        return mIsSelected;
    }

    public void setSelected(boolean mIsSelected) {
        this.mIsSelected = mIsSelected;
    }

    public String getCalendarEventId() {
        return mCalendarEventId;
    }

    public void setCalendarEventId(String calendarEventId) {
        this.mCalendarEventId = calendarEventId;
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
        res.append(",");
        res.append(COMMENT_COL_NAME + " VARCHAR(200)");
        res.append(",");
        res.append(CALENDAR_EVENT_ID_COL_NAME + " VARCHAR(64)");
        res.append(")");
        return res.toString();
    }

    public String getColumnNames() {
        StringBuilder res = new StringBuilder();
        res.append(UUID_COL_NAME);
        res.append(",");
        res.append(DATEDEMANDE_COL_NAME);
        res.append(",");
        res.append(DATEDEBUT_COL_NAME);
        res.append(",");
        res.append(DATEFIN_COL_NAME);
        res.append(",");
        res.append(UUIDTYPE_COL_NAME);
        res.append(",");
        res.append(UUIDDEMANDEUR_COL_NAME);
        res.append(",");
        res.append(UUIDPARTENAIRE_COL_NAME);
        res.append(",");
        res.append(UUIDCLUBSTRUCTURE_COL_NAME);
        res.append(",");
        res.append(MAIL_COL_NAME);
        res.append(",");
        res.append(LIEU_COL_NAME);
        res.append(",");
        res.append(UUIDPRESIDENTJURY_COL_NAME);
        res.append(",");
        res.append(UUIDDELEGUECTR_COL_NAME);
        res.append(",");
        res.append(UUIDREPCIBPL_COL_NAME);
        res.append(",");
        res.append(STATUT_COL_NAME);
        res.append(",");
        res.append(DATEVALIDATION_COL_NAME);
        res.append(",");
        res.append(COMMENT_COL_NAME);
        res.append(",");
        res.append(CALENDAR_EVENT_ID_COL_NAME);
        return res.toString();
    }

    public String getColumnNamesForUpdate() {
        StringBuilder res = new StringBuilder();
        res.append(UUID_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(DATEDEMANDE_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(DATEDEBUT_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(DATEFIN_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(UUIDTYPE_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(UUIDDEMANDEUR_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(UUIDPARTENAIRE_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(UUIDCLUBSTRUCTURE_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(MAIL_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(LIEU_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(UUIDPRESIDENTJURY_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(UUIDDELEGUECTR_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(UUIDREPCIBPL_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(STATUT_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(DATEVALIDATION_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(COMMENT_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(CALENDAR_EVENT_ID_COL_NAME);
        res.append("=?");
        return res.toString();
    }

    public String getValuesForSQL() {
        StringBuilder res = new StringBuilder();
        res.append("?");
        res.append(",");
        res.append("?");
        res.append(",");
        res.append("?");
        res.append(",");
        res.append("?");
        res.append(",");
        res.append("?");
        res.append(",");
        res.append("?");
        res.append(",");
        res.append("?");
        res.append(",");
        res.append("?");
        res.append(",");
        res.append("?");
        res.append(",");
        res.append("?");
        res.append(",");
        res.append("?");
        res.append(",");
        res.append("?");
        res.append(",");
        res.append("?");
        res.append(",");
        res.append("?");
        res.append(",");
        res.append("?");
        res.append(",");
        res.append("?");
        res.append(",");
        res.append("?");
        return res.toString();
    }

    public List<Object> getValueForSQL() {
        List<Object> values = new ArrayList<Object>();
        values.add(mUUID);
        values.add(mDateDemande);
        values.add(mDateDebut);
        values.add(mDateFin);
        values.add(mType.getUUID());
        values.add(mDemandeur.getUUID());
        values.add(mPartenaire != null ? mPartenaire.getUUID() : "");
        values.add(mOrganisateur != null ? mOrganisateur.getUUID() : "");
        values.add(mMailContact);
        values.add(mLieu);
        values.add(mPresidentJury != null ? mPresidentJury.getUUID() : "");
        values.add(mDelegueCTR != null ? mDelegueCTR.getUUID() : "");
        values.add(mRepCIBPL != null ? mRepCIBPL.getUUID() : "");
        values.add(mStatut != null ? mStatut.ordinal() : Status.DEMANDE);
        values.add(mDateValidation);
        values.add(mComment);
        values.add(mCalendarEventId);
        return values;
    }

    public List<String> getTypesForSQL() {
        List<String> types = new ArrayList<String>();
        types.add("string");
        types.add("date");
        types.add("date");
        types.add("date");
        types.add("string");
        types.add("string");
        types.add("string");
        types.add("string");
        types.add("string");
        types.add("string");
        types.add("string");
        types.add("string");
        types.add("string");
        types.add("integer");
        types.add("date");
        types.add("string");
        types.add("string");
        return types;
    }
}
