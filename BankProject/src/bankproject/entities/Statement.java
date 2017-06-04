package bankproject.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import bankproject.enumerations.CountryEnum;
import bankproject.enumerations.TypeOperationEnum;

public class Statement extends AbstractEntity {

	private String date;
	private Integer operation_id;
	private TypeOperationEnum type_operation;
	private Double amount;
	private String number;
	private Double summary;
	private CountryEnum country;
	private String firstname;
	private String lastname;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getOperation_id() {
		return operation_id;
	}

	public void setOperation_id(Integer operation_id) {
		this.operation_id = operation_id;
	}

	public TypeOperationEnum getType_operation() {
		return type_operation;
	}

	public void setType_operation(TypeOperationEnum type_operation) {
		this.type_operation = type_operation;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public void setCountry(CountryEnum country) {
		this.country = country;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setCountry(String country_) {

		// TODO On doit pouvoir faire mieux en intégrant les différentes
		// possibilités à CountryEnum
		// TODO Possibilité d'ajouter une interface pour utiliser cette méthode
		// dans Account et Statement sans la réécrire

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

	public void setType_operation(String type_op) {
		
		// TODO On doit pouvoir faire mieux en intégrant les différentes
		// possibilités à CountryEnum
		// TODO Possibilité d'ajouter une interface pour utiliser cette méthode
		// dans Account et Statement sans la réécrire

		switch (type_op) {

		case "DEBIT":
			this.type_operation = TypeOperationEnum.DEBIT;
			break;
		case "CREDIT":
			this.type_operation = TypeOperationEnum.CREDIT;
			break;
		}
	}
	
//	Operation.date, Operation.amount, Operation.type_operation, Account.country, Account.number, 
//	Account.summary, Customer.firstname, Customer.lastname
	
	public String toString(){
		
		StringBuilder statement_string = new StringBuilder();
		
		statement_string.append(this.getDate()+"		");
		statement_string.append(this.getAmount()+"		");
		statement_string.append(this.getType_operation()+"			");
		statement_string.append(this.getCountry()+"		");
		statement_string.append(this.getNumber()+"	");
		statement_string.append(this.getSummary()+"		");
		statement_string.append(this.getFirstname()+"		");
		statement_string.append(this.getLastname());
		
		return statement_string.toString();
	}

}
