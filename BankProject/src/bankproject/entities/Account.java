package bankproject.entities;

import java.util.Collection;

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
	private static Collection<Account> accountset; //TODO

	/********************************
	 ********** Test Main ***********
	 ********************************/

	public static void main(String[] args) {

		// Account account = new Account();
		// account.setCountry(CountryEnum.BELGIUM);
		// account.setCustomer_id(8);
		// System.out.println(account.getCountry());
		// System.out.println(account.getCustomer_id());
	}

	/******************************
	 ********** Methods **********
	 ******************************/

	public String buildNumber(CountryEnum country) {

		StringBuilder sb = new StringBuilder();
		int digit, i;
		
		String letters = country.getShortCode();
		sb.append(letters);

		for (i = 1; i < 7; i++) {

			digit = (int) (Math.random() * 10);
			sb.append(digit);

		}

		return sb.toString();
	}

	/******************************
	 ********** Getters **********
	 ******************************/

	public Integer getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public Double getSummary() {
		return summary;
	}

	public CountryEnum getCountry() {
		return country;
	}

	/******************************
	 ********** Setters **********
	 ******************************/

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setCustomer_id(Integer customer_) {
		this.customer_id = customer_;
	}

	public void setSummary(Double summary) {
		this.summary = summary;
	}

	public void setCountry(CountryEnum country_) {
		country = country_;
	}

	public void setCountry(String country_) {

		// TODO On doit pouvoir faire mieux en intégrant les différentes
		// possibilités à CountryEnum

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

	public void updateSummary(Double amount) {
		summary = getSummary() + amount;
	}

}
