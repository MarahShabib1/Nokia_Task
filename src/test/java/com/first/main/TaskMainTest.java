package com.first.main;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TaskMainTest {
	  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    private ClassLoader loader = TaskMain.class.getClassLoader();

	    
	    
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
	        String[] args = {loader.getResource("TestingDirectory").getPath()};
	        TaskMain.main(args);
	        String TestString ="okia";
	        StringBuilder expectedOutput = new StringBuilder();
	   	 for(int i=97;i<=122;i++) {
	   		 
	   		if(TestString.indexOf(i) != -1) {
	   			expectedOutput.append((char)(i)+":"+"75"+ "\n");
	   		}else {
	   			expectedOutput.append((char)(i)+":"+"0"+ "\n");
	   		}
		 }

	        Assert.assertEquals(expectedOutput.toString(), outContent.toString());
	   }
		
		@Test
	    public void testInvalidPath() throws Exception {
	        String[] args =new String[2];
	        args[0]="M:\\Users\\MarahSh\\Desktop\\TestingDirectory"; //invalidpath
	        TaskMain.main(args);
	    }
		
		
		   @After
		    public void cleanUpStreams() {
		        System.setOut(null);
		    }


}
