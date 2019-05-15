package dao;

import static db.SqliteDB.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Customer;

public class CustomerDao {

	public int doAdd(Customer customer) {
		String sql = "insert into customer values(?,?)";
		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, customer.getName());
			statement.setString(2, customer.getStockCodesStr());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int doUpdate(Customer customer) {
		String sql = "update customer set stocks=? where name=?";
		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, customer.getStockCodesStr());
			statement.setString(2, customer.getName());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Customer findByName(String name) {
		String sql = "select * from customer where name=?";
		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Customer customer = new Customer();
				customer.setName(resultSet.getString("name"));
				customer.setStockCodes(resultSet.getString("stocks"));
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
