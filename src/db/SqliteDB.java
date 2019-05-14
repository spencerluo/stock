package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteDB {


	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection =  DriverManager.getConnection("jdbc:sqlite:res//stock.db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
