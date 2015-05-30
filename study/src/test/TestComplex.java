package test;

import math.Complex;

public class TestComplex {
	
	public static void main(String args[]) {
		Complex c1 = new Complex(0.0F, 3.14159F);
		Complex c2 = new Complex(3.14159F, 0.0F);
		Complex z = new Complex(0.0F, 0.0F);
		Complex u = new Complex(1.0F, 0.0F);
		Complex c3 = c1.add(c2);
		Complex c4 = c1.sub(c2);
		System.out.println("c1:" + c1.toString() + " c2:" + c2.toString());
		System.out.println("c3:" + c3.toString() + " c4:" + c4.toString());
		System.out.println("c1+z:" + c1.add(z).toString());
		System.out.println("z-c1:" + z.sub(c1).toString());
		System.out.println("z-u:" + z.sub(u).toString());
		System.out.println("c3*u:" + c3.mult(u).toString());
		System.out.println("c1*c2:" + c1.mult(c2).toString());
		
	}
	
}
