package com.nokia.assignment;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * FilesReader class is responsible to loop through all files in a directory
 * including sub directories through readFiles method.And then assign a task
 * (which is LowCaseCounter in our case) to the ExecutorService .
 * 
 * @author MarahSh
 *
 */
public class FilesReader {

	/**
	 * This method is responsible to loop through all files and assign a task (which
	 * is LowCaseCounter in our case) to the ExecutorService.
	 * 
	 * @param file      directory or sub directory
	 * @param charCount Array that contains the final count for each chars
	 * @param executer  to provide a pool of threads.
	 */

	public void readFiles(File file, AtomicIntegerArray charCount, ExecutorService executer) {

		for (final File fileEntry : file.listFiles()) {

			if (!(fileEntry.isDirectory())) {
				executer.submit(new LowerCaseCounter(fileEntry, charCount));

			} else {
				readFiles(fileEntry, charCount, executer);

			}
		}

	}

}
