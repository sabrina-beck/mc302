package pacote;

import java.text.MessageFormat;

/**
 * Implementação de fração que fornece as operações básicas realizadas em frações
 * 
 * @author Sabrina Beck Angelini (RA: 157240) <sabrinabeck.96@gmail.com> 
 **/
public class Fracao implements Comparable<Fracao> {
	
	private int numerador;
	private int denominador;
	
	/**
	 * Cria uma fração simpĺificada
	 * 
	 * @param numerador
	 * @param denominador
	 **/
	public Fracao(int numerador, int denominador) {
		if (denominador == 0)
			throw new IllegalArgumentException();
		
		this.numerador = numerador;
		this.denominador = denominador;
		simplificar();
	}
	
	/**
	 * @return Fração resultante da adição da instancia atual com a fração informada.
	 * @param fracao Segunda parcela da adição
	 **/
	public Fracao add(Fracao fracao) {
		
		if (this.denominador != fracao.denominador) {
			int mmc = Math.mmc(this.denominador, fracao.denominador);
			return new Fracao(this.novoNumerador(mmc) + fracao.novoNumerador(mmc), mmc);
		}
		
		return new Fracao(this.numerador + fracao.numerador, this.denominador);
	}
	
	/**
	 *  @return Fração resultante da subtração da instancia atual com a fração informada.
	 *  @param fracao Segunda parcela da subtração
	 **/
	public Fracao sub(Fracao fracao) {
		return this.add(fracao.oposta());
	}
	
	/**
	 *  @return Fração resultante da multiplicação da instancia atual com a fração informada.
	 *  @param fracao Segundo fator da multiplicacao
	 **/
	public Fracao mult(Fracao fracao) {
		return new Fracao(this.numerador * fracao.numerador, this.denominador * fracao.denominador);
	}
	
	/**
	 *  @return Fração resultante da divisão da instancia atual com a fração informada.
	 *  @param fracao Divisor
	 **/
	public Fracao div(Fracao fracao) {
		return new Fracao(this.numerador * fracao.denominador, this.denominador * fracao.numerador);
	}
	
	/**
	 *  @return Fração oposta da instancia atual, ou seja, a fração atual multiplicada por -1.
	 **/
	public Fracao oposta() {
		return new Fracao(-this.numerador, this.denominador);
	}
	
	public float toFloat() {
		return (float) this.numerador / this.denominador;
	}
	
	/**
	 *  @return -1, zero ou 1 se o resultado da comparação for ‘menor’, ‘igual’ ou maior, respectivamente.
	 *  @param fracao Fração a ser comparada com a instância atual
	 **/
	@Override
	public int compareTo(Fracao fracao) {
		if (this.denominador != fracao.denominador) {
			int mmc = Math.mmc(this.denominador, fracao.denominador);
			return transformarResultadoCompareToEmModuloUm(this.novoNumerador(mmc) - fracao.novoNumerador(mmc));
		}
		
		return transformarResultadoCompareToEmModuloUm(this.numerador - fracao.numerador);
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("( {0} / {1} )", this.numerador, this.denominador);
	}
	
	private int transformarResultadoCompareToEmModuloUm(int resultado) {
		if (resultado > 0)
			return 1;
		else if (resultado < 0)
			return -1;
		return resultado;
	}
	
	/**
	 * @return Numerador equivalente ao denominador informado.
	 * @param novoDenominador Novo denominador 
	 **/
	private int novoNumerador(int novoDenominador) {
		return this.numerador * (novoDenominador / this.denominador);
	}
	
	/**
	 * Simplifica a fração representada pela instancia atual. 
	 **/
	private void simplificar() {
		if (this.denominador < 0) {
			this.numerador *= -1;
			this.denominador *= -1;
		}
		
		int divisor = Math.mdc(Math.abs(this.numerador), Math.abs(this.denominador));
		this.numerador /= divisor;
		this.denominador /= divisor;
	}
	
	
	/**
	 * Implementa operações matemáticas 
	 **/
	public static class Math {
		/**
		 * @return MDC entre a e b.
		 * @param a
		 * @param b
		 **/	
		public static int mdc(int a, int b) {
			while (b != 0) {
				int aux = a;
				a = b;
				b = aux % b;
			}
			return a;
		}
		/**
		 * @return MMC entre a e b.
		 * @param a
		 * @param b 
		 **/
		public static int mmc(int a, int b) {
			return (a * b) / mdc(a, b);
		}
		
		/**
		 * @return Módulo do valor informado
		 * @param a 
		 **/
		public static int abs(int a) {
			if (a < 0)
				return -a;
			return a;
		}
	}
	
}
