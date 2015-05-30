package math;

import java.text.MessageFormat;

public class Complex {
	
	private float imaginary;
	private float real;
	
	public Complex(float imaginary, float real) {
		this.imaginary = imaginary;
		this.real = real;
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("{0} {1}i", this.real, this.imaginary);
	}
	
	public Complex add(Complex c) {
		return new Complex(this.imaginary + c.imaginary, this.real + c.real);
	}
	
	public Complex sub(Complex c) {
		return new Complex(this.imaginary - c.imaginary, this.real - c.real);
	}
	
	public Complex mult(Complex c) {
		return new Complex((this.real * c.imaginary) + (this.imaginary * c.real), (this.real * c.real) - (this.imaginary * c.imaginary));
	}
	
}
