package bankproject.entities;

import java.util.Date;

import bankproject.enumerations.TypeOperationEnum;

public class Operation extends AbstractEntity {

	private Integer id;
	private Double amount;
	private TypeOperationEnum type_operation;
	private Integer account_id;
	private Integer customer_id;
	private String date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public TypeOperationEnum getType_operation() {
		return type_operation;
	}

	public void setType_operation(TypeOperationEnum type_operation) {
		this.type_operation = type_operation;
	}

	public void setType_operation(String type_op) {

		switch (type_op) {

		case "DEBIT":
			this.type_operation = TypeOperationEnum.DEBIT;
			break;
		case "CREDIT":
			this.type_operation = TypeOperationEnum.CREDIT;
			break;
		}
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
