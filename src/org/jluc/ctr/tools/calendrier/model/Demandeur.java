package org.jluc.ctr.tools.calendrier.model;

import java.util.UUID;

public class Demandeur {
	public static final String UUID_COL_NAME = "UUID";
	public static final String NAME_COL_NAME = "Name";
	public static final String NUMEROSTRUCTURE_COL_NAME = "NumeroStructure";

	private String mUUID;
	private String mName;
	private String mNumeroStructure;

	public Demandeur(String uuid, String name, String numeroStructure) {
		mUUID = uuid;
		mName = name;
		mNumeroStructure = numeroStructure;
	}

	public Demandeur(String name, String numeroStructure) {
		mUUID = UUID.randomUUID().toString();
		mName = name;
		mNumeroStructure = numeroStructure;
	}

	/**
	 * @return the mName
	 */
	public String getName() {
		return mName;
	}

	/**
	 * @param mName the mName to set
	 */
	public void setName(String mName) {
		this.mName = mName;
	}

	/**
	 * @return the mNumeroStructure
	 */
	public String getNumeroStructure() {
		return mNumeroStructure;
	}

	/**
	 * @param mNumeroStructure the mNumeroStructure to set
	 */
	public void setNumeroStructure(String mNumeroStructure) {
		this.mNumeroStructure = mNumeroStructure;
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
}
