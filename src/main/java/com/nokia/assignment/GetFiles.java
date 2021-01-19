package com.nokia.assignment;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * GetFiles class is responsible to make a loop on all files in a directory
 * including sub directories through ReadAllFiles method . and then assign a
 * task (which is LowCaseCounter in our case) to the ExecutorService .
 * 
 * @author MarahSh
 *
 */
public class GetFiles {

	/**
	 * 
	 * @param file      directory or sub directory
	 * @param charCount Array that contains the final count for each chars
	 * @param executer  to provide a pool of threads.
	 */

	public void ReadFiles(File file, AtomicIntegerArray charCount, ExecutorService executer) {

		for (final File fileEntry : file.listFiles()) {

			if (!(fileEntry.isDirectory())) {
				executer.submit(new LowerCaseCounter(fileEntry, charCount));

			} else {
				ReadFiles(fileEntry, charCount, executer);

			}
		}

	}

}
