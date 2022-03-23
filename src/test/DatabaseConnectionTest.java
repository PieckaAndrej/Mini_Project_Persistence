package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dal.DbConnection;

public class DatabaseConnectionTest {

	DbConnection db = null;
	Connection con = null;

	@BeforeEach
	public void setUp() {
		con = DbConnection.getInstance().getConnection(0);
	}
	
	
	@Test
	public void wasConnected() {
		assertNotNull(con, "Connected - connection cannot be null");	
	}

}
