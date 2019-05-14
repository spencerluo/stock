package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import vo.Account;
import static db.SqliteDB.getConnection;
public class AccountDao {

	public int doAdd(Account account) {
		String sql = "insert into account values(?,?,?)";
		try(Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setString(1, account.getUsername());
			statement.setString(2, account.getPassword());
			statement.setString(3, account.getEmail());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public static void main(String[] args) {
		Account test = new Account();
		test.setUsername("spencer");
		test.setPassword("123456");
		test.setEmail("123@abc.com");
		AccountDao accountDao = new AccountDao();
		accountDao.doAdd(test);
	}
}
