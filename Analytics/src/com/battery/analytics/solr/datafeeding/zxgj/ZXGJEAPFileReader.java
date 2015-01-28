package com.battery.analytics.solr.datafeeding.zxgj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import com.battery.analytics.solr.DataFeedHelper;

public class ZXGJEAPFileReader {
	
	private ZXGJEPADocumentUploader uploader;
	
	public ZXGJEAPFileReader(ZXGJEPADocumentUploader uploader){
		this.uploader = uploader;
	}	
	
	public void readDocumentsFiles() throws IOException{
		File file = new File("./lib/EAPTest.log");
	    FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);
	    String line;
	    long lineNum =1;
	    long recordNum = 0;
	    List<String> lines = new ArrayList<String>();
	    while((line = br.readLine()) != null){
	    	  if(line.startsWith(ZXGJParserHelper.lineBeginWithCommnets)){
	               line = line.concat(ZXGJParserHelper.ownAttributesSeporator+
	                              +lineNum+ZXGJParserHelper.ownAttributesSeporator
	                              +recordNum);
	    	  }else if(line.startsWith(ZXGJParserHelper.attributeBegin)){
	    		  line = line.concat(ZXGJParserHelper.ownAttributesSeporator+
                          +lineNum+ZXGJParserHelper.ownAttributesSeporator
                          +recordNum);
	    	  }else {
	    		  line = line.concat(ZXGJParserHelper.ownAttributesSeporator+
                          +lineNum+ZXGJParserHelper.ownAttributesSeporator
                          +recordNum);    		  
	    	  }
	       lines.add(line);	       
	       uploader.submitDoc(lines);	         
	       lineNum++;
	       recordNum++;
	       lines=null;
	       lines = new ArrayList<String>();
	    }
	    br.close();
	    fr.close();
		
	}

}
