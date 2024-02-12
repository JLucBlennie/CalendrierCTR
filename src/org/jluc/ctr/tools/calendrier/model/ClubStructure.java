package org.jluc.ctr.tools.calendrier.model;

import java.util.UUID;

public class ClubStructure {
	public static final String UUID_COL_NAME = "UUID";
	public static final String NAME_COL_NAME = "Name";

	private String mUUID;
	private String mName;

	public ClubStructure(String uuid, String name) {
		mUUID = uuid;
		mName = name;
	}

	public ClubStructure(String name) {
		mUUID = UUID.randomUUID().toString();
		mName = name;
	}

	public String getUUID() {
		return mUUID;
	}

	public void setUUID(String uuid) {
		this.mUUID = uuid;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
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
}
