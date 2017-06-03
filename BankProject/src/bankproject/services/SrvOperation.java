package bankproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bankproject.entities.AbstractEntity;
import bankproject.entities.Operation;
import bankproject.exceptions.SrvException;

public class SrvOperation extends AbstractService {

	private static SrvOperation INSTANCE = new SrvOperation();

	/**
	 * 
	 */
	private SrvOperation() {
		setEntitySqlTable("Operation");
		//(id, amount, type_operation, account_id, customer_id, date)
	}

	/**
	 * 
	 * @return
	 */
	public static SrvOperation getInstance() {
		return INSTANCE;
	}

	/**
	 * 
	 * @param entity
	 * @throws SQLException
	 */

	private void create(Operation entity) throws SQLException {
		String sql = "INSERT INTO " + getEntitySqlTable() + " (amount, type_operation, account_id, customer_id, date) VALUES (?, ?, ?, ?, ?)";
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = getDbManager().getConnection();
			ps = connection.prepareStatement(sql);
			
			ps.setString(1, entity.getAmount().toString());
			ps.setString(2, entity.getType_operation().toString());
			ps.setString(3, entity.getAccount_id().toString());
			ps.setString(4, entity.getCustomer_id().toString());
			ps.setString(5, entity.getDate());
			
			//(id, amount, type_operation, account_id, customer_id, date)

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
	
	//(id, amount, type_operation, account_id, customer_id, date)
	
	private void update(Operation entity) throws SQLException {
		String sql = "UPDATE " + getEntitySqlTable() + " SET amount = ?, type_operation = ?, account_id = ?, customer_id = ?, date = ? WHERE id = ?";
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = getDbManager().getConnection();
			ps = connection.prepareStatement(sql);
			
			ps.setString(1, entity.getAmount().toString());
			ps.setString(2, entity.getType_operation().toString());
			ps.setString(3, entity.getAccount_id().toString());
			ps.setString(4, entity.getCustomer_id().toString());
			ps.setString(5, entity.getDate());
			ps.setInt(6, entity.getId());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}

			if (ps != null) {
				ps.close();
			}
		}
	}

	@Override
	//(id, amount, type_operation, account_id, customer_id, date)
	
	protected Operation populateEntity(ResultSet rs) throws SQLException {
		Operation operation = new Operation();
		operation.setId(rs.getInt("id"));
		operation.setAmount(rs.getDouble("amount")); 
		operation.setType_operation(rs.getString("type_operation"));
		operation.setCustomer_id(rs.getInt("customer_id"));
		operation.setDate(rs.getString("date"));

		return operation;
	}

	@Override
	public void save(AbstractEntity entity) throws SrvException, SQLException {
		if (entity instanceof Operation) {
			Operation operation = (Operation) entity;
			if (operation.getId() == null) {
				create(operation);
			} else {
				update(operation);
			}
		} else {
			throw new SrvException("Utilisation du mauvais service");
		}
	}

	public String createTableInDB() {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE IF NOT EXISTS Operation ( ");
		sb.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sb.append("amount DOUBLE NOT NULL, ");
		sb.append("type_operation VARCHAR(10) NOT NULL, ");
		sb.append("account_id INTEGER NOT NULL, ");
		sb.append("customer_id INTEGER NOT NULL, ");
		sb.append("date TEXT NOT NULL ");
		sb.append(")");

		return sb.toString();
	}
	
	//(id, amount, type_operation, account_id, customer_id, date)
}