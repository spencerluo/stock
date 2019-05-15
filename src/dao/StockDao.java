package dao;

import static db.SqliteDB.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.StockInfo;

public class StockDao {

	public int doAdd(StockInfo stockInfo) {
		String sql = "insert into stock values(?,?,?,?,?)";
		try(Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setInt(1, stockInfo.getCode());
			statement.setString(2, stockInfo.getName());
			statement.setDouble(3, stockInfo.getPrice());
			statement.setDouble(4, stockInfo.getChangePercent());
			statement.setDouble(5, stockInfo.getChangeSize());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public StockInfo findByCode(int code) {
		String sql = "select * from stock where code = ?";
		try(Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setInt(1, code);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				StockInfo stockInfo = new StockInfo();
				stockInfo.setCode(resultSet.getInt(1));
				stockInfo.setName(resultSet.getString(2));
				stockInfo.setPrice(resultSet.getDouble(3));
				stockInfo.setChangePercent(resultSet.getDouble(4));
				stockInfo.setChangeSize(resultSet.getDouble(5));
				return stockInfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
	}
	
}
