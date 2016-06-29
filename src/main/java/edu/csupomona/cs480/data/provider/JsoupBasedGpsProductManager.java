package edu.csupomona.cs480.data.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.csupomona.cs480.data.GpsProduct;

public class JsoupBasedGpsProductManager implements GpsProductManager {

	@Override
	public List<GpsProduct> listAllGpsProducts() {
		List<GpsProduct> gpsProducts = new ArrayList<GpsProduct>();
		
		Document doc;
		try {
			doc = Jsoup.connect("http://www.ebay.com/sch/i.html?_from=R40&_trksid=p2050601.m570.l1313.TR12.TRC2.A0.H0.Xgps.TRS0&_nkw=gps&_sacat=0").get();
			Elements products = doc.select("h3.lvtitle strong");
			for(Element ele : products) {
				System.out.println(ele.text());
			}

			Elements prices = doc.select("li.lvprice span.bold");
			for(Element ele : prices) {
				System.out.println(ele.text());
			}

			Elements images = doc.select("div.lvpicinner img.img");
			for(Element ele : images) {
				System.out.println(ele.attr("src"));
			}
			
			for(int i = 0; i < 8; i++) {
				GpsProduct gp = new GpsProduct();
				gp.setName(products.get(i).text());
				gp.setPrice(prices.get(i).text());
				gp.setImgUrl(images.get(i).attr("src"));
				gpsProducts.add(gp);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return gpsProducts;
	}

}
