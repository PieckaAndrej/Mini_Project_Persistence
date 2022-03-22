package database;
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
			ds.setUser("sa");
			ds.setPassword("secret2022*");
			ds.setServerName("\\SQLEXPRESS");
			ds.setDatabaseName("bank");
			conn = ds.getConnection();

			
		} catch (SQLException e) {

			e.printStackTrace();
		}	
	}
	
	public DbConnection getInstance() {
		return instance;		
	}
	
	public Connection getConnection(int isolationLevel) {
		try {
			conn.setTransactionIsolation(isolationLevel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void printSessionInfo(Connection conn) {
		
		PreparedStatement selectSessionInfo = null;
		
		try {
			selectSessionInfo = conn.prepareStatement("select @@SPID");
			ResultSet sessionInfoRows = selectSessionInfo.executeQuery();
			sessionInfoRows.next();
			System.out.println(Thread.currentThread().getName()+ " - Session: "+ sessionInfoRows.getInt(1) + ", IsolationLevel: "+ conn.getTransactionIsolation());	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}