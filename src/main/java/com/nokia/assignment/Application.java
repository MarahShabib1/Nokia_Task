package com.nokia.assignment;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Application is the main class takes the file path from the command line
 * arguments and it call the ReadAllFiles method. After that it will print the
 * counts of the low case letters in the console .
 * 
 * @author MarahSh
 *
 */

public class Application {

	public static void main(String[] args) throws Exception {

		if (args.length == 0 || !Files.exists(Paths.get(args[0]))) {
			throw new RuntimeException("Please Enter a correct path!");
		}

		AtomicIntegerArray charCount = new AtomicIntegerArray(26);
		File file = new File(args[0]);
		int numOfCores = Runtime.getRuntime().availableProcessors();
		ExecutorService executer = Executors.newFixedThreadPool(numOfCores); // thread # equal to the # of cpu cores
		GetFiles getFiles = new GetFiles();
		getFiles.ReadFiles(file, charCount, executer);
		executer.shutdown();
		executer.awaitTermination(1, TimeUnit.HOURS);
		for (int i = 0; i < charCount.length(); i++) {
			System.out.print((char) (i + 97) + "\t" + charCount.get(i) + "\n");
		}
	}
}
