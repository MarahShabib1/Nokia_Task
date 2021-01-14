package com.first.main;

import java.io.File;

import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;




public class App {


	public static void main(String[] args) {
		 //Hashtable<Character,Integer> result=new Hashtable<>();
		 
		 AtomicIntegerArray result = new AtomicIntegerArray(28);
		 
		 File folder = new File("C:\\Users\\MarahSh\\Desktop\\test");
		 ExecutorService executer =Executors.newFixedThreadPool(5);
	      ReadFiles thread=new ReadFiles(folder,result,executer);
		 thread.ReadAllFiles(folder, result, executer);
		 
		// ReadAllFiles(folder ,result,executer );
		 executer.shutdown();
		 try {
			executer.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		 int count;
		 System.out.println("CountAll............");
		/* for(int i=97; i<=122;i++) {
			 if(result.get((char)i)==null) {
				 count=0;
			 }else {
				 count= result.get((char)i);
			 }
			  System.out.println((char)i+ " " + count);  
		 }*/
	
	
		 for(int i=0;i<result.length();i++) {
			 System.out.println((char) (i+97) +":"+result.get(i));
		 }
	}
}
