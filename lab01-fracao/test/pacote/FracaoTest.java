package pacote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	
	@Test
	public void testDividirFracoes() {
		Fracao umQuarto = new Fracao(1, 4);
		Fracao doisSetimos = new Fracao(2, 7);
		assertEquals("( 7 / 8 )", umQuarto.div(doisSetimos).toString());
		
		Fracao umQuartoNegativo = new Fracao(1, -4);
		Fracao tresSetimos = new Fracao(3, 7);
		assertEquals("( -7 / 12 )", umQuartoNegativo.div(tresSetimos).toString());
	}
	
	@Test
	public void testToFloat() {
		Fracao umQuarto = new Fracao(1, 4);
		assertEquals(1 / 4, umQuarto.toFloat(), 10);
		
		Fracao tresSetimos = new Fracao(3, 7);
		assertEquals(3 / 7, tresSetimos.toFloat(), 10);
	}
	
	@Test
	public void testCompararFracoes() {
		Fracao tresQuintos = new Fracao(3, 5);
		Fracao doisQuintos = new Fracao(2, 5);
		assertTrue(tresQuintos.compareTo(doisQuintos) > 0);
		assertTrue(doisQuintos.compareTo(tresQuintos) < 0);
		assertTrue(doisQuintos.compareTo(doisQuintos) == 0);
		assertTrue(tresQuintos.compareTo(tresQuintos) == 0);
		
		Fracao umQuarto = new Fracao(1, 4);
		Fracao tresSetimos = new Fracao(3, 7);
		assertTrue(umQuarto.compareTo(tresSetimos) < 0);
		assertTrue(tresSetimos.compareTo(umQuarto) > 0);
		assertTrue(tresSetimos.compareTo(tresSetimos) == 0);
		assertTrue(umQuarto.compareTo(umQuarto) == 0);
	}
	
	@Test
	public void testFracaoEnunciado() {
		Fracao f1 = new Fracao(1, 2);
		Fracao f2 = new Fracao(1, 3);
		Fracao f3 = f1.add(f2);
		Fracao f4 = f1.sub(f2);
		Fracao f5 = f1.mult(f2);
		Fracao f6 = f1.div(f2);
		assertEquals("f1: ( 1 / 2 )", "f1: " + f1.toString());
		assertEquals("f2: ( 1 / 3 )", "f2: " + f2.toString());
		assertEquals("f3: ( 5 / 6 )", "f3: " + f3.toString());
		assertEquals("f4: ( 1 / 6 )", "f4: " + f4.toString());
		assertEquals("f5: ( 1 / 6 )", "f5: " + f5.toString());
		assertEquals("f6: ( 3 / 2 )", "f6: " + f6.toString());
		assertEquals("float(f3): 0.8333333", "float(f3): " + f3.toFloat());
	}
	
	@Test
	public void testFracaoSusy() {
		Fracao f1 = new Fracao(1, 2);
		Fracao f2 = new Fracao(1, 3);
		Fracao f3 = f1.add(f2);
		Fracao f4 = f1.sub(f2);
		Fracao f5 = f1.mult(f2);
		Fracao f6 = f1.div(f2);
		Fracao f7 = new Fracao(15, 9);
		assertEquals("f1: ( 1 / 2 )", "f1: " + f1.toString());
		assertEquals("f2: ( 1 / 3 )", "f2: " + f2.toString());
		assertEquals("f3: ( 5 / 6 )", "f3: " + f3.toString());
		assertEquals("f4: ( 1 / 6 )", "f4: " + f4.toString());
		assertEquals("f5: ( 1 / 6 )", "f5: " + f5.toString());
		assertEquals("f6: ( 3 / 2 )", "f6: " + f6.toString());
		assertEquals("f7: ( 5 / 3 )", "f7: " + f7.toString());
		assertEquals("float(f3): 0.8333333", "float(f3): " + f3.toFloat());
		assertEquals("f2 compareTo f3: -1", "f2 compareTo f3: " + f2.compareTo(f3));
		assertEquals("f2 compareTo f2: 0", "f2 compareTo f2: " + f2.compareTo(f2));
		assertEquals("f6 compareTo f1: 1", "f6 compareTo f1: " + f6.compareTo(f1));
	}
}
