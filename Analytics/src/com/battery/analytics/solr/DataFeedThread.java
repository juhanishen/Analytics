package com.battery.analytics.solr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;









import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;

public class DataFeedThread implements Runnable {

	private String threadName;
	private SolrServer solr;
	private final CountDownLatch doneSignal;
	
	public DataFeedThread(SolrServer solr, String tName,CountDownLatch doneSignal){
		this.threadName = tName;
		this.solr = solr;
		this.doneSignal = doneSignal;
	}
	
	
	@Override
	public void run() {
		
		try {
			readDocumentsFiles();
			doneSignal.countDown();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getThreadName(){
		return threadName;
	}
	
	private void readDocumentsFiles() throws IOException, SolrServerException
	{
		File file = new File("./lib/test.log");
	    FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);
	    String line;
	    long lineNum =1;
	    long recordNum = 0;
	    List<String> lines = new ArrayList<String>();
	    while((line = br.readLine()) != null){
	       if( line.equals("") || line.equalsIgnoreCase("\n")){
	    	   DataFeedHelper.addDocuments(solr,lines);
	    	   recordNum++;
	    	   lines = null;
	    	   lines = new ArrayList<String>();
	       }else{
	           line = line.concat(DataFeedHelper.attributeSeporator+"lineNum"+
	                              DataFeedHelper.keyValueSeporator+lineNum+
	                              DataFeedHelper.attributeSeporator+"recordNum"+DataFeedHelper.keyValueSeporator+recordNum);
	           lines.add(line);
	       }    
	       lineNum++;
	    }
	    br.close();
	    fr.close();
		
	}
}
