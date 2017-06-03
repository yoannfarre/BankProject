package bankproject.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import bankproject.enumerations.CountryEnum;
import bankproject.enumerations.TypeOperationEnum;

public class Statement {
	
	
	private String date;
	private Integer operation_id;
	private TypeOperationEnum type_operation;
	private Double amount;
	private String number;
	private Double summary;
	private CountryEnum country;
	private String firstname;
	private String lastname;
	


	




	
	
	protected Statement populateEntity(ResultSet rs) throws SQLException {
		Statement statement = new Statement();
//		account.setId(rs.getInt("id"));
//		account.setCountry(rs.getString("country")); 
//		account.setNumber(rs.getString("number"));
//		account.setCustomer_id(rs.getInt("customer_id"));
//		account.setSummary(rs.getDouble("summary"));
//
		return statement;
	}

}
