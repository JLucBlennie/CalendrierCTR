package org.jluc.ctr.tools.calendrier.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClubStructure {
    public static final String UUID_COL_NAME = "UUID";
    public static final String NAME_COL_NAME = "Name";

    private String mUUID;
    private String mName;
    private boolean mIsModified = false;
    private boolean mIsNew = false;

    public ClubStructure(String uuid, String name) {
        mUUID = uuid;
        mName = name;
    }

    public ClubStructure(String name) {
        mUUID = UUID.randomUUID().toString();
        mName = name;
        mIsNew = true;
    }

    public String getUUID() {
        return mUUID;
    }

    public void setUUID(String uuid) {
        this.mUUID = uuid;
        mIsModified = true;
        mIsNew = true;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
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
        return mName;
    }

    public static String getAttributesFormSQL() {
        StringBuilder res = new StringBuilder();
        res.append("(");
        res.append(UUID_COL_NAME + " VARCHAR(100)");
        res.append(",");
        res.append(NAME_COL_NAME + " VARCHAR(100)");
        res.append(")");
        return res.toString();
    }

    public String getColumnNames() {
        StringBuilder res = new StringBuilder();
        res.append(UUID_COL_NAME);
        res.append(",");
        res.append(NAME_COL_NAME);
        return res.toString();
    }

    public String getColumnNamesForUpdate() {
        StringBuilder res = new StringBuilder();
        res.append(UUID_COL_NAME);
        res.append("=?");
        res.append(",");
        res.append(NAME_COL_NAME);
        res.append("=?");
        return res.toString();
    }

    public String getValuesForSQL() {
        StringBuilder res = new StringBuilder();
        res.append("?");
        res.append(",");
        res.append("?");
        return res.toString();
    }

    public List<Object> getValueForSQL() {
        List<Object> values = new ArrayList<Object>();
        values.add(mUUID);
        values.add(mName);
        return values;
    }

    public List<String> getTypesForSQL() {
        List<String> types = new ArrayList<String>();
        types.add("string");
        types.add("string");
        return types;
    }
}
