package com.first.main;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.io.FileReader;




public class FilesReader {

	public static  void ReadAllFiles(File folder ,final Hashtable<Character,Integer> result ,ExecutorService executer) {
   
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            ReadAllFiles(fileEntry ,result ,executer);
	        } else {
	        	executer.submit(new Runnable() {
					@Override
					public void run() {
					     try (FileReader fr = new FileReader(fileEntry)) {
					            int content;
					            while ((content = fr.read()) != -1) {
					       		 synchronized(result) {
					     		    if(result.get((char) content)!=null) {	
					                 	result.put((char) content, result.get((char) content) + 1);
					                 }else {
					                 	result.put((char) content ,1);
					                 }}
					            }
					        } catch (IOException e) {
					            e.printStackTrace();
					        }
					}
	        	});
	        }
	    }
	}


	public static void main(String[] args) {
		 Hashtable<Character,Integer> result=new Hashtable<>();
		 File folder = new File("C:\\Users\\MarahSh\\Desktop\\test");
		 ExecutorService executer =Executors.newCachedThreadPool();
		 ReadAllFiles(folder ,result,executer );
		 executer.shutdown();
		 try {
			executer.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		 int count;
		 System.out.println("CountAll............");
		 for(int i=97; i<=122;i++) {
			 if(result.get((char)i)==null) {
				 count=0;
			 }else {
				 count= result.get((char)i);
			 }
			  System.out.println((char)i+ " " + count);  
		 }
	}
}
