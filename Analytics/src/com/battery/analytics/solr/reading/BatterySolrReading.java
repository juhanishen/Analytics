package com.battery.analytics.solr.reading;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class BatterySolrReading {
    public static void main(String[] args) throws SolrServerException{
        String urlString = "http://localhost:8983/solr"; 		
        SolrServer solr = new HttpSolrServer(urlString);	
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
//        query.setQuery("sony digital camera");
//        query.addFilterQuery("cat:electronics","store:amazon.com");
//         query.setFields("id","price","merchant","cat","store");
//        query.setStart(0);    
//        query.set("defType", "edismax");

        QueryResponse response = solr.query(query);
        SolrDocumentList results = response.getResults();
//        for (int i = 0; i < results.size(); ++i) {
        for (int i = 0; i <10; ++i) { 
          SolrDocument doc = results.get(i);	
          for(String fieldName: doc.getFieldNames()){
        	  System.out.println("fieldName is"+fieldName+",Value is "+doc.getFieldValue(fieldName));
          }
        }
        
    }
}
