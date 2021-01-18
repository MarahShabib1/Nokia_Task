package com.first.main;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * LowCaseCounter is a Runnable class thats responsible to count the occurrences of lower-case  characters in the file .
 * @author MarahSh
 *
 */

public class LowCaseCounter implements Runnable {

	private File file;
	private AtomicIntegerArray charCount;

	public LowCaseCounter(File file, AtomicIntegerArray charCount) {
		this.file = file;
		this.charCount = charCount;
	}

	@Override
	public void run() {

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			int[] threadArray = new int[24]; // to save the low case chars count for the whole file .
			char[] charsChunk = new char[8192]; // to read chunk of characters from the file . 
			int charsRead = br.read(charsChunk, 0, charsChunk.length);
			while (charsRead != -1) {
				for (int i = 0; i < charsChunk.length; i++) {
					if (charsChunk[i] >= 97 && charsChunk[i] <= 122)
						threadArray[(int) (charsChunk[i] - 97)]++;
				}
				charsRead = br.read(charsChunk, 0, charsChunk.length);
			}
			for (int i = 0; i < threadArray.length; i++) {
				if (threadArray[i] > 0)
					charCount.addAndGet(i, threadArray[i]);
			}

		} catch (IOException e ) {

			e.printStackTrace();
		} 
		    

	}

}