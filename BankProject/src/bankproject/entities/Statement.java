package bankproject.entities;

import bankproject.enumerations.CountryEnum;
import bankproject.enumerations.TypeOperationEnum;

public class Statement extends AbstractEntity {

	/********************************
	 ********** Attributes **********
	 ********************************/

	private String date;
	private Integer operation_id;
	private TypeOperationEnum type_operation;
	private Double amount;
	private String number;
	private Double summary;
	private CountryEnum country;
	private String firstname;
	private String lastname;

	/******************************
	 ********** Getters ***********
	 ******************************/

	public String getDate() {
		return date;
	}

	public Integer getOperation_id() {
		return operation_id;
	}

	public TypeOperationEnum getType_operation() {
		return type_operation;
	}

	public Double getAmount() {
		return amount;
	}

	public String getNumber() {
		return number;
	}

	public Double getSummary() {
		return summary;
	}

	public CountryEnum getCountry() {
		return country;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	/******************************
	 ********** Setters ***********
	 ******************************/

	public void setDate(String date) {
		this.date = date;
	}

	public void setOperation_id(Integer operation_id) {
		this.operation_id = operation_id;
	}

	public void setType_operation(TypeOperationEnum type_operation) {
		this.type_operation = type_operation;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setSummary(Double summary) {
		this.summary = summary;
	}

	public void setCountry(CountryEnum country) {
		this.country = country;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setCountry(String country_) {

		CountryEnum countryenum = CountryEnum.getByLongNameEnglish(country_);

		if (countryenum == null) {
			countryenum = CountryEnum.getByLongNameFrench(country_);
		}

		this.country = countryenum;

	}

	public void setType_operation(String type_op) {

		TypeOperationEnum typeopenum = TypeOperationEnum.getType_operationByString(type_op);

		this.type_operation = typeopenum;

	}

	// Operation.date, Operation.amount, Operation.type_operation,
	// Account.country, Account.number,
	// Account.summary, Customer.firstname, Customer.lastname

	/******************************
	 ********** Methods ***********
	 ******************************/

	public String toString() {

		StringBuilder statement_string = new StringBuilder();

		statement_string.append(this.getDate() + "		");
		statement_string.append(this.getAmount() + "		");
		statement_string.append(this.getType_operation() + "			");
		statement_string.append(this.getCountry() + "		");
		statement_string.append(this.getNumber() + "	");
		statement_string.append(this.getSummary() + "			");
		statement_string.append(this.getFirstname() + "		");
		statement_string.append(this.getLastname());

		return statement_string.toString();
	}

}
