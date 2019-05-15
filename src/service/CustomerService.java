package service;

import java.util.List;

import dao.CustomerDao;
import dao.StockDao;
import vo.Customer;
import vo.StockInfo;

public class CustomerService {

	private Customer customer;

	public CustomerService(Customer customer) {
		this.customer = customer;
	}

	public void addStock(String stockCode) {
		customer.addStock(stockCode);
		CustomerDao customerDao = new CustomerDao();
		customerDao.doUpdate(customer);
	}

	public void remveStock(String stockCode) {
		customer.removeStock(stockCode);
	}

	public void show() {
		StockDao stockDao = new StockDao();
		for (String stockCode : customer.getStockCodes()) {
			StockInfo sInfo = stockDao.findByCode(Integer.valueOf(stockCode));
			System.out.println(sInfo.toJson());
		}
	}

	public static void main(String[] args) {
		CustomerDao customerDao = new CustomerDao();
		Customer customer = customerDao.findByName("spencer");
		System.out.println(customer.getStockCodes());
		CustomerService customerService = new CustomerService(customer);
//		customerService.addStock("2231");
//		customerService.addStock("2668");
//		customerService.addStock("2172");
		customerService.show();
	}
}
