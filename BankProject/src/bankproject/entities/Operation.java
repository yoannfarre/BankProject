package bankproject.entities;

import bankproject.enumerations.TypeOperationEnum;

public class Operation extends AbstractEntity {

	/********************************
	 ********** Attributes **********
	 ********************************/

	private Integer id;
	private Double amount;
	private TypeOperationEnum type_operation;
	private Integer account_id;
	private Integer customer_id;
	private String date;

	/******************************
	 ********** Getters ***********
	 ******************************/

	public Integer getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public Integer getAccount_id() {
		return account_id;
	}

	public TypeOperationEnum getType_operation() {
		return type_operation;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public String getDate() {
		return date;
	}

	/******************************
	 ********** Setters ***********
	 ******************************/

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public void setType_operation(TypeOperationEnum type_operation) {
		this.type_operation = type_operation;
	}

	public void setType_operation(String type_op) {

		TypeOperationEnum typeopenum = TypeOperationEnum.getType_operationByString(type_op);

		this.type_operation = typeopenum;

	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
