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
	private static Collection<Account> accountset; // TODO

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

		CountryEnum countryenum = CountryEnum.getByLongNameEnglish(country_);

		if (countryenum == null) {
			countryenum = CountryEnum.getByLongNameFrench(country_);
		}

		this.country = countryenum;

	}

	public void updateSummary(Double amount) {
		summary = getSummary() + amount;
	}

}
