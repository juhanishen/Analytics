package com.battery.analytics.solr;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;

public class DataFeedThread implements Runnable {

	private String threadName;
	private int indexDocStart;
	private int indexDocEnd;
	private SolrServer solr;
	private final CountDownLatch doneSignal;
	
	public DataFeedThread(SolrServer solr, String tName,int indexDocStart,int indexDocEnd,CountDownLatch doneSignal){
		this.threadName = tName;
		this.indexDocStart = indexDocStart;
		this.indexDocEnd = indexDocEnd;
		this.solr = solr;
		this.doneSignal = doneSignal;
	}
	
	
	@Override
	public void run() {
		
		try {
			DataFeedHelper.addDocuments(solr, indexDocStart, indexDocEnd);
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
}
