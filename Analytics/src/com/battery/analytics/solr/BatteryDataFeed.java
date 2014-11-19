package com.battery.analytics.solr;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

public class BatteryDataFeed {
	private static int numDoc = 10000;
	private static int numThreads = 2;
	//one thread, in i3 (bought in 2009, two cores), 1000doc 4G(1G available), 128M for JVM, eclipse environment it is 86 msgs
	//one thread, in i3 (bought in 2009, two cores),  10000doc 4G(1G available), 512M for JVM, eclipse environment it is 96 msgs
	//two threads, in i3 (bought in 2009, two cores),  10000doc 4G(1G available), 512M for JVM, eclipse environment it is 193 msgs
	public static void main(String[] args) throws SolrServerException, IOException, InterruptedException{	
		
		CountDownLatch doneSignal = new CountDownLatch(numThreads);		
		String urlString = "http://localhost:8983/solr";
		
		SolrServer solr = new HttpSolrServer(urlString);	
		Thread[] feedThreads = new Thread[numThreads];
		for(int i=0;i<feedThreads.length;i++){
			feedThreads[i]=new Thread(new DataFeedThread(solr, String.valueOf(i),i*numDoc/numThreads,(i+1)*numDoc/numThreads,doneSignal));
		}	

		long start = System.nanoTime();
		
	    for(int i=0;i<feedThreads.length;i++){
		    feedThreads[i].start();
	    }
        doneSignal.await();		
 		long end = System.nanoTime();
	    int capacity = (int) (numDoc /((double)(end-start)/1000/1000/1000));
	 
		System.out.println("current throughput is:"+capacity+" msg/second");

	}
}