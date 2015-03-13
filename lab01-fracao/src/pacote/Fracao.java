package pacote;

import java.text.MessageFormat;

public class Fracao {

	private int numerador;
	private int denominador;

	public Fracao(int numerador, int denominador) {
		if (denominador == 0)
			throw new IllegalArgumentException();

		this.numerador = numerador;
		this.denominador = denominador;
		simplificar();
	}
	
	public Fracao add(Fracao fracao) {
		
		if(this.denominador != fracao.denominador) {
			int mmc = Math.mmc(this.denominador, fracao.denominador);
			return new Fracao(this.calculaNovoNumerador(mmc) + fracao.calculaNovoNumerador(mmc), mmc);			
		}
		
		return new Fracao(this.numerador + fracao.numerador, this.denominador);
	}
	
	public Fracao sub(Fracao fracao) {
		return this.add(fracao.oposta());
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("( {0} / {1} )", this.numerador,
				this.denominador);
	}
	
	private Fracao oposta() {
		return new Fracao(- this.numerador, this.denominador);
	}

	private int calculaNovoNumerador(int novoDenominador) {
		return this.numerador * (novoDenominador / this.denominador);
	}
	
	private void simplificar() {
		if (this.denominador < 0) {
			this.numerador *= -1;
			this.denominador *= -1;
		}

		int divisor = Math.mdc(Math.abs(this.numerador), Math.abs(this.denominador));
		this.numerador /= divisor;
		this.denominador /= divisor;
	}
	
	public static class Math {
		public static int mdc(int a, int b) {
			while(b != 0) {
				int aux = a;
				a = b;
				b = aux % b;
			}
			return a;
		}

		public static int mmc(int a, int b) {
			return (a * b) / mdc(a, b);
		}

		public static int abs(int a) {
			if(a < 0)
				return -a;
			return a;
		}
	}

}
