package bankproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

import bankproject.entities.AbstractEntity;
import bankproject.entities.Statement;
import bankproject.enumerations.CountryEnum;
import bankproject.enumerations.TypeOperationEnum;
import bankproject.exceptions.SrvException;

public class SrvStatement extends AbstractService {

	/********************************
	 ********* Attributes ***********
	 ********************************/

	private static SrvStatement INSTANCE = new SrvStatement();

	/********************************
	 *********** Getters ************
	 ********************************/

	public static SrvStatement getInstance() {
		return INSTANCE;
	}

	/********************************
	 *********** Methods ************
	 ********************************/

	@Override
	public void save(AbstractEntity entity) throws SrvException, SQLException {

	}

	@Override
	public String createTableInDB() {
		return null;
	}

	protected Statement populateEntity(ResultSet rs) throws SQLException {
		Statement statement = new Statement();
		statement.setDate(rs.getString("date"));
		statement.setAmount(rs.getDouble("amount"));
		statement.setType_operation(rs.getString("type_operation"));
		statement.setCountry(rs.getString("country"));
		statement.setNumber(rs.getString("number"));
		statement.setSummary(rs.getDouble("summary"));
		statement.setFirstname(rs.getString("firstname"));
		statement.setLastname(rs.getString("lastname"));

		return statement;
	}

	public Collection<Statement> requestStatementsByType(TypeOperationEnum type) throws Exception {

		Connection connexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Collection<Statement> results = new LinkedHashSet<>();

		StringBuilder query = new StringBuilder("SELECT ");
		query.append("Operation.date, Operation.amount, ");
		query.append("Operation.type_operation, Account.country, Account.number, ");
		query.append("Account.summary, Customer.firstname, Customer.lastname ");
		query.append("FROM Operation ");
		query.append("INNER JOIN Account ON Account.id = Operation.account_id ");
		query.append("INNER JOIN Customer ON Customer.id = Operation.customer_id ");
		query.append("WHERE Operation.type_operation = ? ");
		query.append("ORDER BY Operation.date ; "); 

		try {
			connexion = getDbManager().getConnection();
			pst = connexion.prepareStatement(query.toString());
			pst.setString(1, type.toString());
			rs = pst.executeQuery();

			while (rs.next()) {
				results.add(populateEntity(rs));
			}

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

		return results;
	}

	public Collection<Statement> requestStatementsByCountry(CountryEnum country) throws Exception {

		Connection connexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Collection<Statement> results = new LinkedHashSet<>();

		StringBuilder query = new StringBuilder("SELECT ");
		query.append("Operation.date, Operation.amount, ");
		query.append("Operation.type_operation, Account.country, Account.number, ");
		query.append("Account.summary, Customer.firstname, Customer.lastname ");
		query.append("FROM Operation ");
		query.append("INNER JOIN Account ON Account.id = Operation.account_id ");
		query.append("INNER JOIN Customer ON Customer.id = Operation.customer_id ");
		query.append("WHERE Account.country = ? ");
		query.append("ORDER BY Operation.date ;"); 

		try {
			connexion = getDbManager().getConnection();
			pst = connexion.prepareStatement(query.toString());
			pst.setString(1, country.toString());
			rs = pst.executeQuery();

			while (rs.next()) {
				results.add(populateEntity(rs));
			}

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

		return results;
	}

	public Collection<Statement> requestStatementsCustomer(String firstname, String lastname) throws Exception {

		Connection connexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Collection<Statement> results = new LinkedHashSet<>();

		StringBuilder query = new StringBuilder("SELECT ");
		query.append("Operation.date, Operation.amount, ");
		query.append("Operation.type_operation, Account.country, Account.number, ");
		query.append("Account.summary, Customer.firstname, Customer.lastname ");
		query.append("FROM Operation ");
		query.append("INNER JOIN Account ON Account.id = Operation.account_id ");
		query.append("INNER JOIN Customer ON Customer.id = Operation.customer_id ");
		query.append("WHERE (Customer.firstname = ? AND Customer.lastname = ?) ");
		query.append("ORDER BY Operation.date ;"); 

		try {
			connexion = getDbManager().getConnection();
			pst = connexion.prepareStatement(query.toString());
			pst.setString(1, firstname.toString());
			pst.setString(2, lastname.toString());
			rs = pst.executeQuery();

			while (rs.next()) {
				results.add(populateEntity(rs));
			}

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

		return results;
	}

}
