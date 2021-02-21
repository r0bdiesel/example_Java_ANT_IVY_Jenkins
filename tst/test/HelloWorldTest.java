package test;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;


public class HelloWorldTest{
  
  String message = "Robert";	
   
   @Test
   public void testA() {	 
      assertEquals(message, "Robert");     
   }
  
   @Test
   public void testA() {	 
      assertEquals(true, true);     
   }
  
   @Ignore
   @Test
   public void testIgnore() {
      assertEquals("this", "that");
   }
  
}
