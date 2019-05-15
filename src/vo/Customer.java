package vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.helper.StringUtil;

public class Customer {

	private String name;
	private List<String> stockCodes;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getStockCodes() {
		return stockCodes;
	}
	
	public void setStockCodes(List<String> astocks) {
		this.stockCodes = astocks;
	}
	
	public void setStockCodes(String astocks) {
		this.stockCodes = new ArrayList<>(Arrays.asList(astocks.split(",")));
	}
	
	public String getStockCodesStr() {
		return StringUtil.join(getStockCodes(),",");
	}
	
	public void addStock(String code) {
		if (!stockCodes.contains(code)) {
			stockCodes.add(code);
		}
	}
	
	public void removeStock(String code) {
		if (stockCodes.contains(code)) {
			stockCodes.remove(code);
		}
	}
	
}
