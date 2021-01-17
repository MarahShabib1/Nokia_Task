package com.first.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicIntegerArray;
/**
 * 
 * @author MarahSh
 *
 */
public class ReadFiles {
	
	
	 File file;
     AtomicIntegerArray charCount;
 	 ExecutorService executer;
	List<String> list=new ArrayList<String >();
	public ReadFiles(File folder , AtomicIntegerArray charCount ,ExecutorService executer) {
		this.file=folder;
		this.charCount=charCount;
		this.executer=executer;
		
	}
	/**
	 * 
	 * @param folder
	 * @param charCount
	 * @param executer
	 */
	
	public   void ReadAllFiles(File folder , AtomicIntegerArray charCount ,ExecutorService executer) {
	
	/*	for (final File fileEntry : folder.listFiles()) {
            if (!(fileEntry.isDirectory())) {
	         list.add(fileEntry.getAbsolutePath());      
	        } 
		}*/
		
		for (final File fileEntry : folder.listFiles()) {
			 
           /*   for(final String path :list) {
            	  executer.submit(new Counter(new File(path),result));	  
              }
			
			list.removeAll(list);*/
	        if (!(fileEntry.isDirectory())) {
	        	
	        	 executer.submit(new Counter(fileEntry,charCount));	
	        	// ReadAllFiles(fileEntry ,result ,executer);
	          
	        } else {
	        	 ReadAllFiles(fileEntry ,charCount ,executer);
	        	
	        }
		}
		
	}

}
