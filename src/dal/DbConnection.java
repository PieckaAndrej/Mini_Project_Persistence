package dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class DbConnection {
	
	private static DbConnection instance = new DbConnection();
	private Connection conn;
	
	private DbConnection() {
		try {
			SQLServerDataSource ds = new SQLServerDataSource();
			ds.setEncrypt(false);
			ds.setPortNumber(1433);
			ds.setUser("CSC-CSD-S211_10407533");
			ds.setPassword("Password1!");
			ds.setServerName("hildur.ucn.dk");
			ds.setDatabaseName("CSC-CSD-S211_10407533");
			conn = ds.getConnection();

			
		} catch (SQLException e) {

			e.printStackTrace();
		}	
	}
	
	public static DbConnection getInstance() {
		return instance;		
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public Connection getConnection(int isolationLevel) {
		try {
			conn.setTransactionIsolation(isolationLevel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void printSessionInfo(Connection conn) {
		
		PreparedStatement selectSessionInfo = null;
		
		try {
			selectSessionInfo = conn.prepareStatement("select @@SPID");
			ResultSet sessionInfoRows = selectSessionInfo.executeQuery();
			sessionInfoRows.next();
			System.out.println(Thread.currentThread().getName() + " - Session: " + sessionInfoRows.getInt(1) +
					", IsolationLevel: " + conn.getTransactionIsolation());	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	// requires prepared statement to be created with the additional argument
	// PreparedStatement.RETURN_GENERATED_KEYS
	public int executeSqlInsertWithIdentity(PreparedStatement ps) {
		int res = -1;
		try {
			res = ps.executeUpdate();
			if (res > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public void startTransaction() {
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void commitTransaction() {
		try {
			try {
				conn.commit();
			} catch (SQLException e) {
				throw e;
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rollbackTransaction() {
		try {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw e;
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void setTransactionIsolation(int level) {
		try {
			conn.setTransactionIsolation(level);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}