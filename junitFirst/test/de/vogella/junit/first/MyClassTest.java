package de.vogella.junit.first;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyClassTest {
	MyClass tester;
	
	@Before
	public void init(){
		tester = new MyClass();
	}
	
	
	@Test(expected=Exception.class)
	public void testBuggy() throws Exception{
		tester.buggy();
	}
	
	@Test
	public void testMultiply() {
		assertEquals("Result", 50, tester.multiply(10, 5));
	}
	
	

}
