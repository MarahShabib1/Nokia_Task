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

	
		try ( BufferedReader br = new BufferedReader(new FileReader(folder))){
			int[] threadArray =new int[27];
			char[] theChars = new char[8000];
			
	
			int charsRead = br.read(theChars, 0, theChars.length);

			while(charsRead != -1) {

				for(int i=0 ;i<theChars.length;i++) {
					if(	theChars[i]>=97) 
					   threadArray[(int)(theChars[i]-97)]++;	
						}
			    charsRead = br.read(theChars, 0, theChars.length);
			
			}
			
	
			
	  		 for(int i=0 ;i<threadArray.length;i++) {
	  			result.addAndGet(i, threadArray[i]);
		   		//	result.incrementAndGet( (int) (AllChars[i]-97));	 
		   			 
		   		 }
		} catch (IOException e) {
			
			e.printStackTrace();
		}catch (Exception e) {
			 System.out.println("heeyeee");
			e.printStackTrace();
		}

	    		    
	    		}
	     


}
