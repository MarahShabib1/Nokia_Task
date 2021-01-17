package com.first.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Counter implements Runnable {
	
	
	File folder;
	AtomicIntegerArray charCount;
	ExecutorService executer;
	public Counter(File folder , AtomicIntegerArray charCount ) {
		this.folder=folder;
		this.charCount=charCount;
	}
	

	@Override
	public void run() {

		try ( BufferedReader br = new BufferedReader(new FileReader(folder))){
			int[] threadArray =new int[27];
			char[] theChars = new char[8192];
			int charsRead = br.read(theChars, 0, theChars.length);
			while(charsRead != -1) {
				for(int i=0 ;i<theChars.length;i++) {
					if(	theChars[i]>=97 && theChars[i]<=122) 
					   threadArray[(int)(theChars[i]-97)]++;	
						}
			    charsRead = br.read(theChars, 0, theChars.length);
			}
	  		 for(int i=0 ;i<threadArray.length;i++) {
	  			charCount.addAndGet(i, threadArray[i]);	 
		   		 }
	  		 
		} catch (IOException e) {
			
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
    
	    		}
	     


}
