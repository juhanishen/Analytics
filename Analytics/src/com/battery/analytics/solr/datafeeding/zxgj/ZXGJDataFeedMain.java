package com.battery.analytics.solr.datafeeding.zxgj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

public class ZXGJDataFeedMain {
	
	public static void main(String[] args) throws IOException{
		
		String urlString = "http://localhost:8983/solr";		
		SolrServer solr = new HttpSolrServer(urlString);
		
		ZXGJDocumentUploader uploader = new ZXGJDocumentUploader(solr);
			
		ZXGJEAPFileReader eapFileReader = new ZXGJEAPFileReader(uploader);
		eapFileReader.readDocumentsFiles();
        	
		
		
		
	}
}
