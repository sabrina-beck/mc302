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
		assertEquals(umQuarto.add(seteQuartos).toString(), "( 2 / 1 )");
		
		Fracao umSetimo = new Fracao(1, 7);
		Fracao doisSetimos = new Fracao(2, 7);
		assertEquals(umSetimo.add(doisSetimos).toString(), "( 3 / 7 )");
	}
	
	@Test
	public void testAdicionarFracoesComDenominadoresDiferentes() {
		Fracao umQuarto = new Fracao(1, 4);
		Fracao doisSetimos = new Fracao(2, 7);
		assertEquals(umQuarto.add(doisSetimos).toString(), "( 15 / 28 )");
		
		Fracao umSexto = new Fracao(1, 6);
		Fracao seteQuintos = new Fracao(7, 5);
		assertEquals(umSexto.add(seteQuintos).toString(), "( 47 / 30 )");
	}
	
}
