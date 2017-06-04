package bankproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import bankproject.entities.AbstractEntity;
import bankproject.exceptions.SrvException;

public abstract class AbstractService {

	/********************************
	 ********** Attributes **********
	 ********************************/

	private String entitySqlTable;
	private DatabaseManager dbManager;

	/********************************
	 ********** Getters *************
	 ********************************/

	public String getEntitySqlTable() {
		return entitySqlTable;
	}

	public DatabaseManager getDbManager() {
		return dbManager;
	}

	/********************************
	 ********** Setters *************
	 ********************************/

	public void setEntitySqlTable(String entityTable) {
		this.entitySqlTable = entityTable;
	}

	public void setDbManager(DatabaseManager dbManager) {
		this.dbManager = dbManager;
	}

	/********************************
	 ********** Methods ************
	 ********************************/

	public abstract void save(AbstractEntity entity) throws SrvException, SQLException;

	public AbstractEntity get(Integer id) throws Exception {
		Connection connexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		AbstractEntity result = null;

		StringBuilder query = new StringBuilder("SELECT * FROM ");
		query.append(getEntitySqlTable());
		query.append(" WHERE id = ?");

		try {
			connexion = getDbManager().getConnection();
			pst = connexion.prepareStatement(query.toString());
			pst.setInt(1, id);
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

	public Collection<AbstractEntity> get(Collection<Integer> ids) throws Exception {
		Connection connexion = null;
		Statement st = null;
		ResultSet rs = null;
		Collection<AbstractEntity> results = new HashSet<AbstractEntity>();

		StringBuilder query = new StringBuilder("SELECT * FROM ");
		query.append(getEntitySqlTable());
		query.append(" WHERE id IN (");

		Iterator<Integer> it = ids.iterator();
		do {
			query.append(it.next());
			if (it.hasNext()) {
				query.append(",");
			}
		} while (it.hasNext());

		query.append(")");

		try {
			connexion = getDbManager().getConnection();
			st = connexion.createStatement();
			rs = st.executeQuery(query.toString());

			while (rs.next()) {
				results.add(populateEntity(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (st != null) {
				st.close();
			}

			if (connexion != null) {
				connexion.close();
			}
		}

		return results;
	}

	protected abstract AbstractEntity populateEntity(ResultSet rs) throws SQLException, Exception;

	public abstract String createTableInDB();
}
