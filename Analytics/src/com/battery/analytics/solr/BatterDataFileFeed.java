package com.battery.analytics.solr;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class BatterDataFileFeed {
	
	private final static Charset ENCODING = StandardCharsets.UTF_8;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Path path = Paths.get("./lib/test.log");
	    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
	      while (scanner.hasNextLine()){
	        //process each line in some way
	        System.out.println(scanner.nextLine());
	      }      
	    }
	}

}
