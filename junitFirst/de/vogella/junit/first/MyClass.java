package de.vogella.junit.first;

import java.util.Random;

public class MyClass {
	public int multiply(int x, int y) {
		return x * y;
	}
	
	public void buggy() throws Exception{
		Random r = new Random();
		if (r.nextInt(3)==0)
			throw new Exception("I'm an exception!");
		return;
	}
}
