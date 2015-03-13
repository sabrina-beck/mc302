package pacote;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FracaoTest {

	@Test
	public void testCriarFracao() {
		Fracao fracao = new Fracao(1, 4);
		assertEquals("( 1 / 4 )", fracao.toString());
		
		fracao = new Fracao(6, 3);
		assertEquals("( 2 / 1 )", fracao.toString());
		
		fracao = new Fracao(12, 4);
		assertEquals("( 3 / 1 )", fracao.toString());
		
		fracao = new Fracao(35, 15);
		assertEquals("( 7 / 3 )", fracao.toString());
		
		fracao = new Fracao(-7, -3);
		assertEquals("( 7 / 3 )", fracao.toString());
		
		fracao = new Fracao(-7, 3);
		assertEquals("( -7 / 3 )", fracao.toString());
		
		fracao = new Fracao(7, -3);
		assertEquals("( -7 / 3 )", fracao.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCriarFracaoComDenominadorNulo() {
		new Fracao(1, 0);
	}
	
	@Test
	public void testAdicionarFracoesMesmoDenominador() {
		Fracao umQuarto = new Fracao(1, 4);
		Fracao seteQuartos = new Fracao(7, 4);
		assertEquals("( 2 / 1 )", umQuarto.add(seteQuartos).toString());
		
		Fracao umSetimo = new Fracao(1, 7);
		Fracao doisSetimos = new Fracao(2, 7);
		assertEquals("( 3 / 7 )", umSetimo.add(doisSetimos).toString());
	}
	
	@Test
	public void testAdicionarFracoesComDenominadoresDiferentes() {
		Fracao umQuarto = new Fracao(1, 4);
		Fracao doisSetimos = new Fracao(2, 7);
		assertEquals("( 15 / 28 )", umQuarto.add(doisSetimos).toString());
		
		Fracao umSexto = new Fracao(1, 6);
		Fracao seteQuintos = new Fracao(7, 5);
		assertEquals("( 47 / 30 )", umSexto.add(seteQuintos).toString());
	}
	
	@Test
	public void testSubtrairFracoesMesmoDenominador() {
		Fracao umQuarto = new Fracao(1, 4);
		Fracao seteQuartos = new Fracao(7, 4);
		assertEquals("( -3 / 2 )", umQuarto.sub(seteQuartos).toString());
		
		Fracao doisSetimos = new Fracao(2, 7);
		Fracao umSetimo = new Fracao(1, 7);
		assertEquals("( 1 / 7 )", doisSetimos.sub(umSetimo).toString());
	}

	@Test
	public void testSubtrairFracoesComDenominadoresDiferentes() {
		Fracao umQuarto = new Fracao(1, 4);
		Fracao doisSetimos = new Fracao(2, 7);
		assertEquals("( -1 / 28 )", umQuarto.sub(doisSetimos).toString());
		
		Fracao umSexto = new Fracao(1, 6);
		Fracao seteQuintos = new Fracao(7, 5);
		assertEquals("( -37 / 30 )", umSexto.sub(seteQuintos).toString());
	}
	
	@Test
	public void testMultiplicarFracoes() {
		Fracao umQuarto = new Fracao(1, 4);
		Fracao doisSetimos = new Fracao(2, 7);
		assertEquals("( 1 / 14 )", umQuarto.mult(doisSetimos).toString());
		
		Fracao umQuartoNegativo = new Fracao(1, -4);
		Fracao tresSetimos = new Fracao(3, 7);
		assertEquals("( -3 / 28 )", umQuartoNegativo.mult(tresSetimos).toString());
	}
}
