package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public int doUpdate(Account account) {
		String sql = "update account set password=?, email=? where username=?";
		try(Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setString(1, account.getPassword());
			statement.setString(2, account.getEmail());
			statement.setString(3, account.getUsername());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int doDelete(Account account) {
		String sql = "delete from account where username=?";
		try(Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setString(1, account.getUsername());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Account findByUsername(Account aAccount) {
		String sql = "select * from account where username=?";
		try(Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setString(1, aAccount.getUsername());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Account account = new Account();
				account.setUsername(resultSet.getString(1));
				account.setPassword(resultSet.getString(2));
				account.setEmail(resultSet.getString(3));
				return account;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Account> findAll(int index, int limit){
		String sql = "select * from account limit ?,?";
		List<Account> accounts = new ArrayList<>();
		try(Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setInt(1, (index-1)*limit);
			statement.setInt(2, index*limit);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Account account = new Account();
				account.setUsername(resultSet.getString(1));
				account.setPassword(resultSet.getString(2));
				account.setEmail(resultSet.getString(3));
				accounts.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	public int getCount() {
		String sql = "select count(*) from account";
		try(Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)){
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				return resultSet.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Account test = new Account();
		test.setUsername("spencer1");
		test.setPassword("abcd");
		test.setEmail("456@abdc.com");
		AccountDao accountDao = new AccountDao();
		System.out.println(accountDao.findAll(1, 1));
	}
}
