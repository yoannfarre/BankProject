package bankproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bankproject.entities.AbstractEntity;
import bankproject.entities.Customer;
import bankproject.exceptions.SrvException;

public class SrvCustomer extends AbstractService {

	/********************************
	 ********** Attributes **********
	 ********************************/

	private static SrvCustomer INSTANCE = new SrvCustomer();

	/********************************
	 *********** Builders ***********
	 ********************************/

	private SrvCustomer() {
		setEntitySqlTable("Customer");
	}

	/********************************
	 ************ Getters ***********
	 ********************************/
	
	public static SrvCustomer getInstance (DatabaseManager dbm) {
		if (INSTANCE.getDbManager() == null) {
			INSTANCE.setDbManager(dbm);
		}
		return INSTANCE;
	}

	/********************************
	 ************ Methods ***********
	 ********************************/

	private void create(Customer entity) throws SQLException {
		String sql = "INSERT INTO " + getEntitySqlTable() + " (firstname, lastname) VALUES (?, ?)";
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = getDbManager().getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, entity.getFirstname());
			ps.setString(2, entity.getLastname());

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (ps != null) {
				ps.close();
			}

			if (connection != null) {
				connection.close();
			}

		}
	}

	private void update(Customer entity) throws SQLException {
		String sql = "UPDATE " + getEntitySqlTable() + " SET firstname = ?, lastname = ? WHERE id = ?";
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = getDbManager().getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, entity.getFirstname());
			ps.setString(2, entity.getLastname());
			ps.setInt(3, entity.getId());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (ps != null) {
				ps.close();
			}

			if (connection != null) {
				connection.close();
			}

		}
	}

	@Override
	protected Customer populateEntity(ResultSet rs) throws SQLException {
		Customer customer = new Customer();
		customer.setId(rs.getInt("id"));
		customer.setFirstname(rs.getString("firstname"));
		customer.setLastname(rs.getString("lastname"));

		return customer;
	}

	@Override
	public void save(AbstractEntity entity) throws SrvException, SQLException {
		if (entity instanceof Customer) {
			Customer customer = (Customer) entity;
			if (customer.getId() == null) {
				create(customer);
			} else {
				update(customer);
			}
		} else {
			throw new SrvException("Utilisation du mauvais service");
		}
	}

	public String createTableInDB() {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE IF NOT EXISTS Customer ( ");
		sb.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sb.append("firstname TEXT, ");
		sb.append("lastname TEXT ");
		sb.append(")");

		return sb.toString();
	}
	
	public String dropTableInDB() {
		StringBuilder sb = new StringBuilder();
		sb.append("DROP TABLE IF EXISTS Customer;");

		return sb.toString();
	}

	// Méthode de récupération d'id d'un customer

	public Customer get(String firstname_, String lastname_) throws Exception {

		Connection connexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Customer result = null;

		StringBuilder query = new StringBuilder("SELECT * FROM ");
		query.append(getEntitySqlTable());
		query.append(" WHERE (firstname = ? AND lastname = ?)");

		try {
			connexion = getDbManager().getConnection();
			pst = connexion.prepareStatement(query.toString());
			pst.setString(1, firstname_);
			pst.setString(2, lastname_);
			rs = pst.executeQuery();

			while (rs.next()) {
				result = populateEntity(rs);
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
		return result;

	}
	
	public Customer getOrCreateByName(String firstname, String lastname) throws Exception {
		Customer customer = get(firstname, lastname);
		if (customer == null) {
			customer = new Customer();
			customer.setLastname(lastname);
			customer.setFirstname(firstname);
			this.create(customer);
		}
		
		return customer;
	}
}