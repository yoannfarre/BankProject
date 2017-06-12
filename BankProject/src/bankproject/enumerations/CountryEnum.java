package bankproject.enumerations;

public enum CountryEnum {
	BELGIUM("BE", "Belgium", "Belgique"), FRANCE("FR", "France", "France"), 
	BRITAIN("GB", "Britain", "Grande-Bretagne"), GERMANY("DE", "Germany", "Allemagne"), 
	NEDERLANDS("NL", "Nederlands", "Pays-Bas"), SPAIN("ES", "Spain", "Espagne"), 
	ITALY("IT", "Italy", "Italie");

	private String shortCode;
	private String longNameEnglish;
	private String longNameFrench;

	private CountryEnum(String _shortCode, String _nameEnglish, String _nameFrench) {
		shortCode = _shortCode;
		longNameEnglish = _nameEnglish;
		longNameFrench = _nameFrench;
	}

	/**
	 * Getter
	 * 
	 * @return
	 */
	public String getShortCode() {
		return shortCode;
	}

	public String getLongNameEnglish() {
		return longNameEnglish;
	}
	
	public String getLongNameFrench() {
		return longNameFrench;
	}

	public static CountryEnum getByShortCode(String s) {
		for (CountryEnum ce : CountryEnum.values()) {
			if (ce.getShortCode().toLowerCase().equals(s.toLowerCase())) {
				return ce;
			}
		}

		return null;
	}

	public static CountryEnum getByLongNameEnglish(String s) {
		for (CountryEnum ce : CountryEnum.values()) {
			if (ce.getLongNameEnglish().toLowerCase().equals(s.toLowerCase())) {
				return ce;
			}
		}

		return null;
	}
	
	public static CountryEnum getByLongNameFrench(String s) {
		for (CountryEnum ce : CountryEnum.values()) {
			if (ce.getLongNameFrench().toLowerCase().equals(s.toLowerCase())) {
				return ce;
			}
		}

		return null;
	}

}
