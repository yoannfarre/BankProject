package bankproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bankproject.entities.AbstractEntity;
import bankproject.entities.Account;
import bankproject.exceptions.SrvException;

public class SrvAccount extends AbstractService {

	private static SrvAccount INSTANCE = new SrvAccount();

	/**
	 * 
	 */
	private SrvAccount() {
		setEntitySqlTable("Account");
		//(id, country, number, customer_id, summary)
	}

	/**
	 * 
	 * @return
	 */
	public static SrvAccount getInstance() {
		return INSTANCE;
	}

	/**
	 * 
	 * @param entity
	 * @throws SQLException
	 */

	private void create(Account entity) throws SQLException {
		String sql = "INSERT INTO " + getEntitySqlTable() + " (country, number, customer_id, summary) VALUES (?, ?, ?, ?)";
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = getDbManager().getConnection();
			ps = connection.prepareStatement(sql);
			
			ps.setString(1, entity.getCountry().toString());
			ps.setString(2, entity.getNumber());
			ps.setString(3, entity.getCustomer_id().toString());
			ps.setString(4, entity.getSummary().toString());

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

	/**
	 * 
	 * @param entity
	 * @throws SQLException
	 */
	
	
	private void update(Account entity) throws SQLException {
		String sql = "UPDATE " + getEntitySqlTable() + " SET country = ?, number = ?, customer_id = ?, summary = ? WHERE id = ?";
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = getDbManager().getConnection();
			ps = connection.prepareStatement(sql);
			
			ps.setString(1, entity.getCountry().toString());
			ps.setString(2, entity.getNumber());
			ps.setString(3, entity.getCustomer_id().toString());
			ps.setString(4, entity.getSummary().toString());
			ps.setInt(5, entity.getId());
			
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
	// TODO
	protected Account populateEntity(ResultSet rs) throws SQLException {
		Account account = new Account();
		account.setId(rs.getInt("id"));
		account.setCountry(rs.getString("country")); 
		account.setNumber(rs.getString("number"));
		account.setCustomer_id(rs.getInt("customer_id"));
		account.setSummary(rs.getDouble("summary"));

		return account;
	}

	@Override
	public void save(AbstractEntity entity) throws SrvException, SQLException {
		if (entity instanceof Account) {
			Account account = (Account) entity;
			if (account.getId() == null) {
				create(account);
			} else {
				update(account);
			}
		} else {
			throw new SrvException("Utilisation du mauvais service");
		}
	}

	public String createTableInDB() {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE IF NOT EXISTS Account ( ");
		sb.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sb.append("country VARCHAR(30) NOT NULL, ");
		sb.append("number VARCHAR(30) NOT NULL, ");
		sb.append("customer_id INTEGER NOT NULL, ");
		sb.append("summary DOUBLE NOT NULL ");
		sb.append(")");

		return sb.toString();
	}
	
	//(id, country, number, customer_id, summary)

	
}


