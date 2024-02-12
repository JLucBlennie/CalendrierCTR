package org.jluc.ctr.tools.calendrier.model;

public class TypeEvenement {
	public static final String UUID_COL_NAME = "UUID";
	public static final String NAME_COL_NAME = "Name";
	public static final String VALEUR_COL_NAME = "Valeur";
	public static final String TYPE_COL_NAME = "Type";

	private String mUUID;
	private String mName;
	private TypeActivite mActivite;
	private String mValeurForms;

	public TypeEvenement(String uuid, String name, TypeActivite activite, String valeurForms) {
		mUUID = uuid;
		mName = name;
		mActivite = activite;
		mValeurForms = valeurForms;
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
	 * @return the mActivite
	 */
	public TypeActivite getActivite() {
		return mActivite;
	}

	/**
	 * @param mActivite the mActivite to set
	 */
	public void setActivite(TypeActivite mActivite) {
		this.mActivite = mActivite;
	}

	/**
	 * @return the mValeurForms
	 */
	public String getValeurForms() {
		return mValeurForms;
	}

	/**
	 * @param mValeurForms the mValeurForms to set
	 */
	public void setValeurForms(String mValeurForms) {
		this.mValeurForms = mValeurForms;
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
		res.append(NAME_COL_NAME + " VARCHAR(100)");
		res.append(",");
		res.append(VALEUR_COL_NAME + " VARCHAR(200)");
		res.append(",");
		res.append(TYPE_COL_NAME + " INT");
		res.append(")");
		return res.toString();
	}

	@Override
	public String toString() {
		return mName;
	}
}
