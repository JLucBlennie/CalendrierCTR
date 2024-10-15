package org.jluc.ctr.tools.calendrier.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TypeEvenement {
    public static final String UUID_COL_NAME = "UUID";
    public static final String NAME_COL_NAME = "Name";
    public static final String VALEUR_COL_NAME = "Valeur";
    public static final String TYPE_COL_NAME = "Type";

    private String mUUID;
    private String mName;
    private TypeActivite mActivite;
    private String mValeurForms;
    private boolean mIsModified = false;
    private boolean mIsNew = false;

    public TypeEvenement(String uuid, String name, TypeActivite activite, String valeurForms) {
        mUUID = uuid;
        mName = name;
        mActivite = activite;
        mValeurForms = valeurForms;
    }

    public TypeEvenement(String name, TypeActivite activite, String valeurForms) {
        mUUID = UUID.randomUUID().toString();
        mName = name;
        mActivite = activite;
        mValeurForms = valeurForms;
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
     * @return the mActivite
     */
    public TypeActivite getActivite() {
        return mActivite;
    }

    /**
     * @param mActivite
     *            the mActivite to set
     */
    public void setActivite(TypeActivite mActivite) {
        this.mActivite = mActivite;
        mIsModified = true;
    }

    /**
     * @return the mValeurForms
     */
    public String getValeurForms() {
        return mValeurForms;
    }

    /**
     * @param mValeurForms
     *            the mValeurForms to set
     */
    public void setValeurForms(String mValeurForms) {
        this.mValeurForms = mValeurForms;
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
        res.append(NAME_COL_NAME + " VARCHAR(100)");
        res.append(",");
        res.append(VALEUR_COL_NAME + " VARCHAR(200)");
        res.append(",");
        res.append(TYPE_COL_NAME + " INT");
        res.append(")");
        return res.toString();
    }

    public String getColumnNames() {
        StringBuilder res = new StringBuilder();
        res.append(UUID_COL_NAME);
        res.append(",");
        res.append(NAME_COL_NAME);
        res.append(",");
        res.append(VALEUR_COL_NAME);
        res.append(",");
        res.append(TYPE_COL_NAME);
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
        res.append(VALEUR_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(TYPE_COL_NAME);
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
        values.add(mName);
        values.add(mValeurForms);
        values.add(mActivite.ordinal());
        return values;
    }

    public List<String> getTypesForSQL() {
        List<String> types = new ArrayList<String>();
        types.add("string");
        types.add("string");
        types.add("string");
        types.add("int");
        return types;
    }

    @Override
    public String toString() {
        return mName;
    }
}
