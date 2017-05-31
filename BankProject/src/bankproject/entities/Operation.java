package bankproject.entities;

import java.sql.Date;

import bankproject.enumerations.TypeOperationEnum;

public class Operation {

	private Integer id;
	private Double amount;
	private Account account;
	private TypeOperationEnum type_operation;
	private Customer customer;
	private Date date;
	
}
