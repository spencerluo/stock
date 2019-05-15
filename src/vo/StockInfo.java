package vo;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class StockInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String name;
	private Double price;
	private Double changePercent;
	private Double changeSize;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getChangePercent() {
		return changePercent;
	}

	public void setChangePercent(Double changePercent) {
		this.changePercent = changePercent;
	}

	public Double getChangeSize() {
		return changeSize;
	}

	public void setChangeSize(Double changeSize) {
		this.changeSize = changeSize;
	}

	@Override
	public String toString() {
		return getName();
	}
	
	public String toJson() {
		JSONObject stockJson = new JSONObject();
		stockJson.put("code", getCode());
		stockJson.put("name", getName());
		stockJson.put("price", getPrice());
		stockJson.put("changePercent", getChangePercent());
		stockJson.put("changeSize", getChangeSize());
		return stockJson.toJSONString();
	}
	

}
