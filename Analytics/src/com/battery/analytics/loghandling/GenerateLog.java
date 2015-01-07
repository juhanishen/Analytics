package com.battery.analytics.loghandling;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.battery.analytics.solr.DataFeedHelper;

public class GenerateLog {
   
	private static int numLogItems = 10;
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub	
	   Path path = Paths.get("./lib/test.log");
	   BufferedWriter writer = Files.newBufferedWriter(path, ENCODING,StandardOpenOption.CREATE,StandardOpenOption.APPEND);
       int j=0;
       long line=1;
	   for(int i=0;i<numLogItems;i++){
    	   StringBuffer msg = new StringBuffer();
    	   
     	   Date date = new Date();
    	   Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    	   calendar.setTime(date);
    	   SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYY-MM-DD'T'HH:MM:SS.000'Z'");
    	   msg.append(" name"+DataFeedHelper.keyValueSeporator+"name"+i+DataFeedHelper.attributeSeporator+" timestamp"+DataFeedHelper.keyValueSeporator+dateFormatter.format(calendar.getTime()));
    	   msg.append("\n");
    	   int random = (int) Math.floor(Math.random()*1000);
    	   line++;
    	   msg.append("count"+DataFeedHelper.keyValueSeporator+random);
    	   msg.append("\n");
    	   msg.append("error"+DataFeedHelper.keyValueSeporator+"this is error information,get a Randome number "+random);
    	   printMsgToFile(writer,msg.toString());
    	   j=i;
       }	   
       writer.close();
	   System.out.println("final j is:"+j);
       System.out.println("Done!");
	}
	
	private static void printMsgToFile(BufferedWriter writer,String str) throws IOException{
        writer.write(str);
        writer.newLine();
        writer.newLine(); //print an empty line   
        writer.flush();
    }
	
}
