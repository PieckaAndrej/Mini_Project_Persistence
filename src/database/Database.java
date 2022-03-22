package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public abstract class Database {
	
	public static Connection getConnection(int isolationLevel) {
		
		Connection conn = null;
		
		try {
			SQLServerDataSource ds = new SQLServerDataSource();
			ds.setEncrypt(false);
			ds.setPortNumber(1433);
			ds.setUser("sa");
			ds.setPassword("secret2022*");
			ds.setServerName("localhost\\sqlexpress");
			ds.setDatabaseName("TheCoffeeBreak");
			conn = ds.getConnection();

			conn.setTransactionIsolation(isolationLevel);
			
		} catch (SQLException e) {

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