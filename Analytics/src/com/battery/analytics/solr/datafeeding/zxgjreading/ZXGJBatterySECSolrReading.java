package com.battery.analytics.solr.datafeeding.zxgjreading;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.battery.analytics.solr.datafeeding.zxgj.ZXGJParserHelper;

public class ZXGJBatterySECSolrReading {

	public static void main(String[] args) throws SolrServerException {
        String urlString = "http://localhost:8983/solr"; 		
        SolrServer solr = new HttpSolrServer(urlString);	
        SolrQuery query = new SolrQuery();
        
        //query facet
        query.setQuery("*:*");
        query.setFacet(true);
        query.addFacetField(ZXGJParserHelper.secLineMessageKeyCodeValueField);
        query.addFacetQuery(ZXGJParserHelper.secLineTimeStampField+"[2015-01-23T11:35:34.653Z TO 2015-01-23T11:35:36.653Z}");
        query.add(ZXGJParserHelper.facetSECDate,ZXGJParserHelper.secLineTimeStampField);
        query.add(ZXGJParserHelper.facetSECDateStartField,"2015-01-23T11:35:34.653Z");
        query.add(ZXGJParserHelper.facetSECDateEndField,"2015-01-23T11:36:39.096Z");
//        query.add(ZXGJParserHelper.facetSECDateGapField,"%2B1SECOND");   
        query.add(ZXGJParserHelper.facetSECDateGapField,"+1SECOND");   

        QueryResponse response = solr.query(query);

        
       List<FacetField> cat = response.getFacetFields();
       System.out.println("facet fields length is:"+cat.size());
       for(FacetField key: cat){
    	   System.out.println("key is "+key.getName()+",value is:"+key.getValueCount());
    	   List<Count> values = key.getValues();
    	   for(Count c : values){
    			   System.out.println(c.getName()+":"+c.getCount());
    	   }
       }
        
      System.out.println("response header is:"+response.getResponseHeader().toString());  
        
      SolrDocumentList results = response.getResults();
      System.out.println("number of result is:"+results.getNumFound());

//        for (int i = 0; i < results.size(); ++i) {
        int range = Math.min(10, results.size());
        for (int i = 0; i <range; ++i) { 
          SolrDocument doc = results.get(i);	
          for(String fieldName: doc.getFieldNames()){
        	  System.out.println("fieldName is"+fieldName+",Value is "+doc.getFieldValue(fieldName));
          }
        }

	}

}
