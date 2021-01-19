package com.nokia.assignment;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * MainCounterTest class to verify that the code is counting the occurrences of
 * the lower case correctly .
 * 
 * @author MarahSh
 *
 */

public class MainCounterTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setup() {
		System.setOut(new PrintStream(outContent));
	}

	/**
	 * This test verifies whether the count of the lower case letter in the
	 * TestingDirectory(inside resources) is equal to the expectedOutput.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLowerCaseCounter() throws Exception {

		URI uri = ClassLoader.getSystemResource("TestingDirectory").toURI();
		String[] args = { Paths.get(uri).toString() };
		MainCounter.main(args);
		String TestString = "okia";
		StringBuilder expectedOutput = new StringBuilder();
		for (int i = 97; i <= 122; i++) {

			if (TestString.indexOf(i) != -1) {
				expectedOutput.append((char) (i) + "\t" + "75" + "\n");
			} else {
				expectedOutput.append((char) (i) + "\t" + "0" + "\n");
			}
		}

		Assert.assertEquals(expectedOutput.toString(), outContent.toString());
	}

	/**
	 * 
	 * This test verifies if the user enter a invalid path an Exception must be
	 * thrown.
	 * 
	 * @throws Exception
	 */
	@Test(expected = RuntimeException.class)
	public void testInvalidPath() throws Exception {

		URI uri = ClassLoader.getSystemResource("invalidPath").toURI();
		String[] args = { Paths.get(uri).toString() };
		MainCounter.main(args);
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

}
