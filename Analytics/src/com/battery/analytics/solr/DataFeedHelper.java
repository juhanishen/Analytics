package com.battery.analytics.solr;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

public class DataFeedHelper {
	private static String IDField = "id";
	private static String nameField = "name_s";
	private static String countField = "count_d";
	private static String errorField = "errot_s";
	private static String timestampField = "ts_dt";
	private static String recordTypeField = "recordType";
	private static String fileTypeField = "fileType";
	private static String lineNumField = "lineNum_l";
	private static String recordNumField = "recordNum_l";
	private static String lineTypeField = "lineType";
	private static String NAME= "name";
	private static String COUNT = "count";
	private static String ERROR = "error";
	
	public static String attributeSeporator= "---";
	public static String keyValueSeporator = ";";
	
	private static AtomicLong idGenerator = new AtomicLong(0);
	
	
	public static void addDocuments(SolrServer solr, List<String> lines) throws SolrServerException, IOException{
		for( String line: lines){
		   SolrInputDocument document = new SolrInputDocument();
		   document.addField( IDField, String.valueOf(idGenerator.getAndIncrement()));
		   if(lineContainsName(line)){
			   addNameDocument(line,document);
		   }else if(lineContainsCount(line)){
			   addCountDocument(line,document);
		   }else if(lineContainsError(line)){
		       addErrorDocument(line,document);
		   }else {
			   // error log
		   }
		   UpdateResponse response = solr.add(document);			   
		} 
		solr.commit(); 
	}


	private static void addNameDocument(String line, SolrInputDocument document) {
		String[] attributes = line.split(attributeSeporator);
		String nameValue = (attributes[0].split(keyValueSeporator))[1];
		document.addField(nameField,nameValue);
		String timeStampValue = (attributes[1].split(keyValueSeporator))[1];
		document.addField(timestampField,timeStampValue);
		String lineNumValue = (attributes[2].split(keyValueSeporator))[1];
		document.addField(lineNumField,Long.parseLong(lineNumValue));
		String recordNumValue = (attributes[3].split(keyValueSeporator))[1];
		document.addField(recordNumField,Long.parseLong(recordNumValue));		
	}

	private static void addCountDocument(String line, SolrInputDocument document) {
		String[] attributes = line.split(attributeSeporator);
		String countValue = (attributes[0].split(keyValueSeporator))[1];
		document.addField(countField,Integer.parseInt(countValue));
		String lineNumValue = (attributes[1].split(keyValueSeporator))[1];
		document.addField(lineNumField,Long.parseLong(lineNumValue));
		String recordNumValue = (attributes[2].split(keyValueSeporator))[1];
		document.addField(recordNumField,Long.parseLong(recordNumValue));		
	}
	
	private static void addErrorDocument(String line, SolrInputDocument document) {
		String[] attributes = line.split(attributeSeporator);
		String errorValue = (attributes[0].split(keyValueSeporator))[1];
		document.addField(errorField,errorValue);
		String lineNumValue = (attributes[1].split(keyValueSeporator))[1];
		document.addField(lineNumField,Long.parseLong(lineNumValue));
		String recordNumValue = (attributes[2].split(keyValueSeporator))[1];
		document.addField(recordNumField,Long.parseLong(recordNumValue));		
	}
	
	
	private static boolean lineContainsName(String line) {
		String[] lines = line.split(keyValueSeporator);
		if(lines[0].contains(NAME)) return true;
		return false;
	}
	
	private static boolean lineContainsCount(String line) {
		String[] lines = line.split(keyValueSeporator);
		if(lines[0].contains(COUNT)) return true;
		return false;
	}
	
	private static boolean lineContainsError(String line) {
		String[] lines = line.split(keyValueSeporator);
		if(lines[0].contains(ERROR)) return true;
		return false;
	}
}
