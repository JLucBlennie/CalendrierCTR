package org.jluc.ctr.tools.calendrier.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Demandeur {
    public static final String UUID_COL_NAME = "UUID";
    public static final String NAME_COL_NAME = "Name";
    public static final String NUMEROSTRUCTURE_COL_NAME = "NumeroStructure";

    private String mUUID;
    private String mName;
    private String mNumeroStructure;
    private boolean mIsModified = false;
    private boolean mIsNew = false;

    public Demandeur(String uuid, String name, String numeroStructure) {
        mUUID = uuid;
        mName = name;
        mNumeroStructure = numeroStructure;
    }

    public Demandeur(String name, String numeroStructure) {
        mUUID = UUID.randomUUID().toString();
        mName = name;
        mNumeroStructure = numeroStructure;
        mIsNew = true;
    }

    /**
     * @return the mName
     */
    public String getName() {
        return mName;
    }

    /**
     * @param mName
     *            the mName to set
     */
    public void setName(String mName) {
        this.mName = mName;
        mIsModified = true;
    }

    /**
     * @return the mNumeroStructure
     */
    public String getNumeroStructure() {
        return mNumeroStructure;
    }

    /**
     * @param mNumeroStructure
     *            the mNumeroStructure to set
     */
    public void setNumeroStructure(String mNumeroStructure) {
        this.mNumeroStructure = mNumeroStructure;
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

    @Override
    public String toString() {
        return mName + (mNumeroStructure.isEmpty() ? "" : " (" + mNumeroStructure + ")");
    }

    public static String getAttributesFormSQL() {
        StringBuilder res = new StringBuilder();
        res.append("(");
        res.append(UUID_COL_NAME + " VARCHAR(100)");
        res.append(",");
        res.append(NAME_COL_NAME + " VARCHAR(100)");
        res.append(",");
        res.append(NUMEROSTRUCTURE_COL_NAME + " VARCHAR(50)");
        res.append(")");
        return res.toString();
    }

    public String getColumnNames() {
        StringBuilder res = new StringBuilder();
        res.append(UUID_COL_NAME);
        res.append(",");
        res.append(NAME_COL_NAME);
        res.append(",");
        res.append(NUMEROSTRUCTURE_COL_NAME);
        return res.toString();
    }

    public String getColumnNamesForUpdate() {
        StringBuilder res = new StringBuilder();
        res.append(UUID_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(NAME_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(NUMEROSTRUCTURE_COL_NAME);
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
        return res.toString();
    }

    public List<Object> getValueForSQL() {
        List<Object> values = new ArrayList<Object>();
        values.add(mUUID);
        values.add(mName);
        values.add(mNumeroStructure);
        return values;
    }

    public List<String> getTypesForSQL() {
        List<String> types = new ArrayList<String>();
        types.add("string");
        types.add("string");
        types.add("string");
        return types;
    }
}
