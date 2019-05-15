package com.spider;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dao.StockDao;
import vo.StockInfo;

public class StockSpider {

	public static String get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		
		CloseableHttpResponse response = httpClient.execute(httpGet);
		if (response.getStatusLine().getStatusCode() == 200) {
			System.out.println(String.format("connect %s succeed", url));
		}else {
			System.err.println(String.format("connect %s failed", url));
		}
		
		HttpEntity entity = response.getEntity();
		
		String result = EntityUtils.toString(entity);
		EntityUtils.consume(entity);
		
		return result;
	}
	
	public static void parserStockstar(String htmlString) {
		Document document = Jsoup.parse(htmlString);
		Element table = document.getElementById("datalist");
		Elements stocksElements = table.getElementsByTag("tr");
		StockDao stockDao = new StockDao();
		for (Element stockElement : stocksElements) {
			List<Element> details = stockElement.getElementsByTag("td").subList(0, 5);
			StockInfo stockInfo = new StockInfo();
			stockInfo.setCode(Integer.valueOf(details.get(0).text()));
			stockInfo.setName(details.get(1).text());
			stockInfo.setPrice(Double.valueOf(details.get(2).text()));
			stockInfo.setChangePercent(Double.valueOf(details.get(3).text().split("%")[0]));
			stockInfo.setChangeSize(Double.valueOf(details.get(4).text()));
			if (stockDao.findByCode(stockInfo.getCode()).equals(null)) {
				stockDao.doAdd(stockInfo);
			}
		}
	}
	
	
	public static void main(String[] args) throws ClientProtocolException, IOException, InterruptedException {
		String urlTemp = "http://quote.stockstar.com/stock/ranklist_a_3_1_%s.html";
		for (int i=1; i< 122; i++) {
			String url = String.format(urlTemp, i);
			String result = get(url);
			parserStockstar(result);
		}
	}
}
