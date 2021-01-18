package com.first.main;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ApplicationTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setup() {
		System.setOut(new PrintStream(outContent));
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLowerCaseCounter() throws Exception {
		
		
		URI uri = ClassLoader.getSystemResource("TestingDirectory").toURI();
		String[] args = { Paths.get(uri).toString() };
		Application.main(args);
		String TestString = "okia";
		StringBuilder expectedOutput = new StringBuilder();
		for (int i = 97; i <= 122; i++) {

			if (TestString.indexOf(i) != -1) {
				expectedOutput.append((char) (i) + ":" + "75" + "\n");
			} else {
				expectedOutput.append((char) (i) + ":" + "0" + "\n");
			}
		}

		Assert.assertEquals(expectedOutput.toString(), outContent.toString());
	}

	@Test(expected = RuntimeException.class)
	public void testInvalidPath() throws Exception {
		
		URI uri = ClassLoader.getSystemResource("invalidPath").toURI();
		String[] args = { Paths.get(uri).toString() };
		Application.main(args);
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

}
