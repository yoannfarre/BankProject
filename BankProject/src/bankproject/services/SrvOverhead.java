package bankproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bankproject.entities.AbstractEntity;
import bankproject.enumerations.CountryEnum;
import bankproject.exceptions.SrvException;

public class SrvOverhead extends AbstractService {
	
	private static SrvOverhead INSTANCE = new SrvOverhead();


	public static SrvOverhead getInstance() {
		return INSTANCE;
	}

	@Override
	public void save(AbstractEntity entity) throws SrvException, SQLException {

	}

	@Override
	protected AbstractEntity populateEntity(ResultSet rs) throws SQLException, Exception {
		return null;
	}

	@Override
	public String createTableInDB() {
		return null;
	}
	
//	public static void main(String[] args) {
//		
//		ResultSet rs =null;
//
//		try {
//			rs = requestStatementsByCountry(CountryEnum.FRANCE);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(rs.toString());
//		
//	}
	
	

	public ResultSet requestStatementsByCountry(CountryEnum country) throws Exception {
		
		Connection connexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		StringBuilder query = new StringBuilder("SELECT "); 
		query.append("Operation.date, Operation.amount, ");
		query.append("Operation.type_operation, Account.number, ");
		query.append("Account.summary, Customer.firstname, Customer.lastname ");
		query.append("FROM Operation ");
		query.append("INNER JOIN Account ON Account.id = Operation.account_id ");
		query.append("INNER JOIN Customer ON Customer.id = Operation.customer_id ");
		query.append("WHERE Account.country = ? ");

		try {
			connexion = getDbManager().getConnection();
			pst = connexion.prepareStatement(query.toString());
			pst.setString(1, country.toString());
			rs = pst.executeQuery();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			
			if (pst != null) {
				pst.close();
			}
			
			if (connexion != null) {
				connexion.close();
			}
		}
		
		return rs;
	}
	


	
}
