package org.jluc.ctr.tools.calendrier.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Moniteur {

    public static final String UUID_COL_NAME = "UUID";
    public static final String FIRSTNAME_COL_NAME = "FirstName";
    public static final String LASTNAME_COL_NAME = "LastName";
    public static final String NIVEAU_COL_NAME = "Niveau";

    private String mUUID;
    private String mLastName;
    private String mFirstName;
    private NiveauMoniteur mNiveau;
    private boolean mIsModified = false;
    private boolean mIsNew = false;

    public Moniteur(String uuid, String lastName, String firstName, int idNiveau) {
        mUUID = uuid;
        mLastName = lastName;
        mFirstName = firstName;
        mNiveau = NiveauMoniteur.values()[idNiveau];
    }

    public Moniteur(String lastName, String firstName, int idNiveau) {
        mUUID = UUID.randomUUID().toString();
        mLastName = lastName;
        mFirstName = firstName;
        mNiveau = NiveauMoniteur.values()[idNiveau];
        mIsNew = true;
    }

    /**
     * @return the mLastName
     */
    public String getLastName() {
        return mLastName;
    }

    /**
     * @param mLastName
     *            the mLastName to set
     */
    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
        mIsModified = true;
    }

    /**
     * @return the mFirstName
     */
    public String getFirstName() {
        return mFirstName;
    }

    /**
     * @param mFirstName
     *            the mFirstName to set
     */
    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
        mIsModified = true;
    }

    /**
     * @return the mNiveau
     */
    public NiveauMoniteur getNiveau() {
        return mNiveau;
    }

    /**
     * @param mNiveau
     *            the mNiveau to set
     */
    public void setNiveaux(NiveauMoniteur mNiveau) {
        this.mNiveau = mNiveau;
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
     *            the mUUID to set
     */
    public void setUUID(String mUUID) {
        this.mUUID = mUUID;
        mIsModified = true;
        mIsNew = true;
    }

    public boolean isModified() {
        return mIsModified;
    }

    public boolean isNew() {
        return mIsNew;
    }

    public static String getAttributesFormSQL() {
        StringBuilder res = new StringBuilder();
        res.append("(");
        res.append(UUID_COL_NAME + " VARCHAR(100)");
        res.append(",");
        res.append(FIRSTNAME_COL_NAME + " VARCHAR(100)");
        res.append(",");
        res.append(LASTNAME_COL_NAME + " VARCHAR(100)");
        res.append(",");
        res.append(NIVEAU_COL_NAME + " INT");
        res.append(")");
        return res.toString();
    }

    public String getColumnNames() {
        StringBuilder res = new StringBuilder();
        res.append(UUID_COL_NAME);
        res.append(",");
        res.append(FIRSTNAME_COL_NAME);
        res.append(",");
        res.append(LASTNAME_COL_NAME);
        res.append(",");
        res.append(NIVEAU_COL_NAME);
        return res.toString();
    }

    public String getColumnNamesForUpdate() {
        StringBuilder res = new StringBuilder();
        res.append(UUID_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(FIRSTNAME_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(LASTNAME_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(NIVEAU_COL_NAME);
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
        return res.toString();
    }

    public List<Object> getValueForSQL() {
        List<Object> values = new ArrayList<Object>();
        values.add(mUUID);
        values.add(mFirstName);
        values.add(mLastName);
        values.add(mNiveau);
        return values;
    }

    public List<String> getTypesForSQL() {
        List<String> types = new ArrayList<String>();
        types.add("string");
        types.add("string");
        types.add("string");
        types.add("integer");
        return types;
    }

    @Override
    public String toString() {
        return mLastName + " " + mFirstName + " (" + mNiveau + ")";
    }
}
