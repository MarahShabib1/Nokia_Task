package com.first.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class ReadFiles implements Runnable {
	
	
	File folder;
	 AtomicIntegerArray result;
	ExecutorService executer;
	public ReadFiles(File folder , AtomicIntegerArray result ,ExecutorService executer) {
		this.folder=folder;
		this.result=result;
		this.executer=executer;
		
	}
	

	
	public   void ReadAllFiles(File folder , AtomicIntegerArray result ,ExecutorService executer) {
		
		for (final File fileEntry : folder.listFiles()) {
	        if (!(fileEntry.isDirectory())) {
	        	executer.submit(new ReadFiles(fileEntry,result,executer));
	          
	        } else {
	        	  ReadAllFiles(fileEntry ,result ,executer);
	        }
		}
		
	}
	
	
	
	

	@Override
	public void run() {
		
	     
		   StringBuilder sb = new StringBuilder();
		   BufferedReader br;
		try {
			br = new BufferedReader( new FileReader(folder));
		       String line;
		        while ((line = br.readLine()) != null) {
		            sb.append(line + System.lineSeparator());
		        }
		 
		        // sb.toString();
		        char [] cc=sb.toString().toCharArray();
		        
		   	// synchronized(result) {
		   		 for(int i=0 ;i<cc.length;i++) {
		   			 
		   			result.incrementAndGet( (int) (cc[i]-97));	 
		   			 
		   		 }
		   	 //}
		}
		catch (IOException e) {

	    		    
	    		    
		}	    
	    		    
	    		}
	     


}
