package bankproject.entities;

import bankproject.enumerations.CountryEnum;

public class Account extends AbstractEntity {

	/********************************
	 ********** Attributes **********
	 ********************************/
	
	private Integer id;
	private String number;
	private Integer customer_id;
	private Double summary;
	private CountryEnum country;
	
	/********************************
	 ********** Test Main  **********
	 ********************************/

	public static void main(String[] args) {

		Account account = new Account();
		account.setCountry(CountryEnum.BELGIUM);
		account.setCustomer_id(8);
		System.out.println(account.getCountry());
		System.out.println(account.getCustomer_id());
	}

	public String buildNumber(CountryEnum country) {

		StringBuilder sb = new StringBuilder();
		int digit, i;

		switch (country) {

		case SPAIN:
			sb.append("ES");
			break;
		case FRANCE:
			sb.append("FR");
			break;
		case BRITAIN:
			sb.append("GB");
			break;
		case GERMANY:
			sb.append("DE");
			break;
		case BELGIUM:
			sb.append("BE");
			break;
		case NEDERLANDS:
			sb.append("NL");
			break;
		}

		for (i = 1; i < 7; i++) {

			digit = (int) (Math.random() * 10);
			sb.append(digit);

		}

		return sb.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_) {
		this.customer_id = customer_;
	}

	public Double getSummary() {
		return summary;
	}

	public void setSummary(Double summary) {
		this.summary = summary;
	}

	public CountryEnum getCountry() {
		return country;
	}

	public void setCountry(CountryEnum country_) {
		country = country_;
	}

	public void setCountry(String country_) {
		
		// TODO On doit pouvoir faire mieux en intégrant les différentes possibilités à CountryEnum 

		switch (country_) {

		case "SPAIN":
			this.country = CountryEnum.SPAIN;
			break;
		case "FRANCE":
			this.country = CountryEnum.FRANCE;
			break;
		case "NEDERLANDS":
			this.country = CountryEnum.NEDERLANDS;
			break;
		case "PAYS-BAS":
			this.country = CountryEnum.NEDERLANDS;
			break;
		case "GERMANY":
			this.country = CountryEnum.GERMANY;
			break;
		case "ALLEMAGNE":
			this.country = CountryEnum.GERMANY;
			break;
		case "BELGIUM":
			this.country = CountryEnum.BELGIUM;
			break;
		case "BELGIQUE":
			this.country = CountryEnum.BELGIUM;
			break;
		case "BRITAIN":
			this.country = CountryEnum.BRITAIN;
			break;
		case "GRANDE-BRETAGNE":
			this.country = CountryEnum.BRITAIN;
			break;
		}

	}

}
