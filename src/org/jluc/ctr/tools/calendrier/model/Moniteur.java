package org.jluc.ctr.tools.calendrier.model;

public class Moniteur {

	public static final String UUID_COL_NAME = "UUID";
	public static final String FIRSTNAME_COL_NAME = "FirstName";
	public static final String LASTNAME_COL_NAME = "LastName";
	public static final String NIVEAU_COL_NAME = "Niveau";

	private String mUUID;
	private String mLastName;
	private String mFirstName;
	private NiveauMoniteur mNiveau;

	public Moniteur(String uuid, String lastName, String firstName, int idNiveau) {
		mUUID = uuid;
		mLastName = lastName;
		mFirstName = firstName;
		mNiveau = NiveauMoniteur.values()[idNiveau];
	}

	/**
	 * @return the mLastName
	 */
	public String getLastName() {
		return mLastName;
	}

	/**
	 * @param mLastName the mLastName to set
	 */
	public void setLastName(String mLastName) {
		this.mLastName = mLastName;
	}

	/**
	 * @return the mFirstName
	 */
	public String getFirstName() {
		return mFirstName;
	}

	/**
	 * @param mFirstName the mFirstName to set
	 */
	public void setFirstName(String mFirstName) {
		this.mFirstName = mFirstName;
	}

	/**
	 * @return the mNiveau
	 */
	public NiveauMoniteur getNiveau() {
		return mNiveau;
	}

	/**
	 * @param mNiveau the mNiveau to set
	 */
	public void setNiveaux(NiveauMoniteur mNiveau) {
		this.mNiveau = mNiveau;
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

	@Override
	public String toString() {
		return mLastName + " " + mFirstName + " (" + mNiveau + ")";
	}
}
