package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
	private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
	
	
    public static void main(String[] args) {
		String where = "Hello World";
		logger.debug("[MAIN] Current Date : {}", where);
        System.out.println(where);
    }
}