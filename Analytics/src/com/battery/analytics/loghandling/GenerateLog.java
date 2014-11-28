package com.battery.analytics.loghandling;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;

public class GenerateLog {
   
	private static int numLogItems = 2000;
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub	
	   Path path = Paths.get("./lib/test.log");
	   BufferedWriter writer = Files.newBufferedWriter(path, ENCODING,StandardOpenOption.CREATE,StandardOpenOption.APPEND);
       int j=0;
	   for(int i=0;i<numLogItems;i++){
    	   StringBuffer msg = new StringBuffer();
    	   msg.append("name:"+"name"+i+" "+" timestamp:"+Calendar.getInstance().getTime());
    	   msg.append("\n");
    	   int random = (int) Math.floor(Math.random()*1000);
    	   msg.append("count:"+random);
    	   msg.append("\n");
    	   msg.append("error_t:"+"this is error information,get a Randome number "+random);
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
