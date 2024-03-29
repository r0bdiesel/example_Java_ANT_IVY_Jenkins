package com.bob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;


public class HelloWorldTest{
  	private static final Logger log = LoggerFactory.getLogger(HelloWorldTest.class);
  	String message = "Robert";	
   
   	@Test
   	public void testA() {	 
     		String where = "test";
		log.debug("Logged:{}", where);
      		System.out.println(where);
     		assertEquals(message, "Robert");     
   	}
  
  	@Test
   	public void testB() {	 
      		assertEquals(true, true);     
   	}
	
	@Test
   	public void testC() {	 
      		assertEquals(HelloWorld.returnHello(), "Hello");     
   	}
  
   	@Ignore
   	@Test
   	public void testIgnore() {
      		assertEquals("this", "that");
   	}
  
}
