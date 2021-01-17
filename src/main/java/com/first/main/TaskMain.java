package com.first.main;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;




public class TaskMain {


	public static void main(String[] args) throws Exception{
	
	/*	 if(args.length==0|| !Files.exists(Paths.get(args[0])) ) {
			 throw new RuntimeException("Please Enter a correct path!");
		 }*/
		 AtomicIntegerArray charCount = new AtomicIntegerArray(27);
		 File folder = new File("C:\\Users\\MarahSh\\Desktop\\TestingDirectory"); //Should be args[0];
	     ExecutorService executer =Executors.newFixedThreadPool(5);
	     ReadFiles ReadThreads=new ReadFiles(folder,charCount,executer);
	     ReadThreads.ReadAllFiles(folder, charCount, executer);
		 executer.shutdown();
		 executer.awaitTermination(1, TimeUnit.HOURS);//hours 
		 for(int i=0;i<charCount.length()-1;i++) {
			 System.out.print((char) (i+97) +":"+charCount.get(i)+"\n");
		 }
	}
}
