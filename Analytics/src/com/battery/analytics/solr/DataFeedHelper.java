package com.battery.analytics.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

public class DataFeedHelper {
	private static String IDField = "id";
	private static String nameField = "name_s";
	private static String priceField = "price_d";
	
	public static void addDocuments(SolrServer solr, int numStart, int numEnd) throws SolrServerException, IOException{
		for( int i=numStart; i<numEnd; i++){
		   SolrInputDocument document = new SolrInputDocument();
		   document.addField( IDField, String.valueOf(i));
		   document.addField( nameField, "Gouda cheese wheel"+i);
		   document.addField( priceField, 49.99+i);
		   UpdateResponse response = solr.add(document);			   
		} 
		solr.commit(); 
	}
}
