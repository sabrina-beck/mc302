package Gomoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Terá o papel de modelo no padrão MVC, com a lógica do jogo gomoku
 * 
 * @author Sabrina Beck Angelini <157240>
 **/
public class Model implements ModelInterface {

	private int[][] tabuleiro;

	/**
	 * Dá início a uma nova partida.
	 **/
	@Override
	public void iniciaPartida() {
		this.tabuleiro = new int[SIZE][SIZE];
		for (int linha = 0; linha < SIZE; linha++) {
			for (int coluna = 0; coluna < SIZE; coluna++)
				this.tabuleiro[linha][coluna] = NONE;
		}
	}

	/**
	 * Salva a partida atual num arquivo cujo nome é passado como parâmetro.
	 * 
	 * @return valor booleano indicando sucesso ou falha
	 **/
	@Override
	public boolean salvaPartida(String nome) {
		if (this.tabuleiro == null)
			return false;

		try {
			PrintWriter writer = new PrintWriter(new File(nome));
			for (int linha = 0; linha < SIZE; linha++) {
				for (int coluna = 0; coluna < SIZE; coluna++)
					writer.print(this.tab(linha, coluna));
				writer.print('\n');
			}
			writer.close();
			return true;
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Carrega uma partida previamente salva em arquivo.
	 * 
	 * @return valor booleano indicando sucesso ou falha na operação
	 **/
	@Override
	public boolean carregaPartida(String nome) {
		iniciaPartida();
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(nome))));

			int indiceLinha = 0;
			while (reader.ready() && indiceLinha < SIZE) {
				String linha = reader.readLine();

				if (linha.length() != SIZE) {
					reader.close();
					return false;
				}

				for (int coluna = 0; coluna < SIZE; coluna++)
					this.tabuleiro[indiceLinha][coluna] = Integer.parseInt(linha.charAt(coluna) + "");
				indiceLinha++;
			}

			reader.close();
			return true;

			// @formatter:off
		// Se o susy compilasse Java 7 seria possível limpar essa parte do
		// código com o multi catch do Java 7
		// @formatter:on
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	/**
	 * Registra jogada de um jogador (1 ou 2), na linha x e coluna y do
	 * tabuleiro.
	 * 
	 * @return um valor booleano indicando ‘sucesso’ (true) ou ‘jogada inválida’
	 *         (false).
	 **/
	@Override
	public boolean joga(int jogador, int x, int y) {
		if (this.tabuleiro == null || x < 0 || x >= SIZE || y < 0 || y >= SIZE || this.tab(x, y) != NONE)
			return false;
		this.tabuleiro[x][y] = jogador;
		return true;
	}

	/**
	 * @return um inteiro indicando qual a peça na posição (x,y) do tabuleiro
	 *         (NONE, WHITE, BLACK). Os valores de x e y indicam a linha e
	 *         coluna respectivamente, a partir de 0.
	 **/
	@Override
	public int tab(int x, int y) {
		/**
		 * É preciso prevenir nullpointer caso o usuário dessa classe não chame
		 * o método iniciaPartida, como o enunciado não especifica escolhi
		 * retornar NONE
		 **/
		if (this.tabuleiro == null)
			return NONE;
		return this.tabuleiro[x][y];
	}

	/**
	 * Verifica se o jogo tem um vencedor.
	 * 
	 * @return nenhum vencedor (NONE), jogador 1 venceu (BLACK), jogador 2
	 *         ganhou (WHITE). Por convenção, o jogador 1 usas as peças pretas e
	 *         o jogador 2, as brancas. Eu gostaria de usar a constante GAMEOVER
	 *         para indicar que não há vencedores, mas a constante tem valor 3 e
	 *         o enunciado exige que esse método retorne 0 nesse caso.
	 **/
	@Override
	public int verifica() {
		int ganhador = verificarLinhas();
		ganhador += verificarColunas();
		ganhador += verificarDiagonaisPrincipais();
		ganhador += verificarDiagonaisSecundarias();

		if (ganhador == 4 * GAMEOVER)
			return GAMEOVER;

		if (ganhador != NONE && ganhador != BLACK && ganhador != WHITE)
			return NONE;
		return ganhador;
	}

	private int verificarDiagonaisSecundarias() {
		boolean podeTerVitoria = false;

		for (int diagonal = 1; diagonal <= 2 * SIZE - 1; diagonal++) {
			int peca = NONE, ultimaPeca = NONE;
			int qtdPeca = 0;
			int qtdEspacoVitoria = 0;
			int linha = 0, coluna = 0;
			boolean diagonalGrande;
			if (diagonal > SIZE) {
				coluna = SIZE - 1;
				linha = diagonal - SIZE;
				diagonalGrande = true;
			} else {
				linha = 0;
				coluna = diagonal - 1;
				diagonalGrande = false;
			}

			for (; (linha < diagonal && !diagonalGrande) || (linha < SIZE && diagonalGrande); linha++, coluna--) {
				int pecaAtual = this.tab(linha, coluna);

				if (pecaAtual != ultimaPeca && pecaAtual != NONE) {
					qtdEspacoVitoria = 1;
				} else {
					qtdEspacoVitoria++;
				}

				// trocou zera contagem
				if (pecaAtual != peca) {
					qtdPeca = 1;
					peca = pecaAtual;
				} else {
					qtdPeca++;
				}

				if (qtdPeca >= LINELENG && peca != NONE)
					return peca;

				if (qtdEspacoVitoria >= LINELENG)
					podeTerVitoria = true;

				if (pecaAtual != NONE)
					ultimaPeca = pecaAtual;
			}
		}

		if (!podeTerVitoria)
			return GAMEOVER;

		return NONE;
	}

	private int verificarDiagonaisPrincipais() {
		boolean podeTerVitoria = false;

		for (int diagonal = 1; diagonal <= 2 * SIZE - 1; diagonal++) {
			int peca = NONE, ultimaPeca = NONE;
			int qtdPeca = 0;
			int qtdEspacoVitoria = 0;
			int linha = 0, coluna = 0;
			boolean diagonalGrande;
			if (diagonal > SIZE) {
				linha = diagonal - SIZE;
				coluna = 0;
				diagonalGrande = true;
			} else {
				linha = 0;
				coluna = SIZE - diagonal;
				diagonalGrande = false;
			}

			for (; (linha < diagonal && !diagonalGrande) || (linha < SIZE && diagonalGrande); coluna++, linha++) {
				int pecaAtual = this.tab(linha, coluna);

				if (pecaAtual != ultimaPeca && pecaAtual != NONE) {
					qtdEspacoVitoria = 1;
				} else {
					qtdEspacoVitoria++;
				}

				// trocou zera contagem
				if (pecaAtual != peca) {
					qtdPeca = 1;
					peca = pecaAtual;
				} else {
					qtdPeca++;
				}

				if (qtdPeca >= LINELENG && peca != NONE)
					return peca;

				if (qtdEspacoVitoria >= LINELENG)
					podeTerVitoria = true;

				if (pecaAtual != NONE)
					ultimaPeca = pecaAtual;
			}
		}

		if (!podeTerVitoria)
			return GAMEOVER;

		return NONE;
	}

	private int verificarColunas() {
		boolean podeTerVitoria = false;

		for (int coluna = 0; coluna < SIZE; coluna++) {
			int peca = NONE, ultimaPeca = NONE;
			int qtdPeca = 0;
			int qtdEspacoVitoria = 0;

			for (int linha = 0; linha < SIZE; linha++) {
				int pecaAtual = this.tab(linha, coluna);

				if (pecaAtual != ultimaPeca && pecaAtual != NONE) {
					qtdEspacoVitoria = 1;
				} else {
					qtdEspacoVitoria++;
				}

				// trocou zera contagem
				if (pecaAtual != peca) {
					qtdPeca = 1;
					peca = pecaAtual;
				} else {
					qtdPeca++;
				}

				if (qtdPeca >= LINELENG && peca != NONE)
					return peca;

				if (qtdEspacoVitoria >= LINELENG)
					podeTerVitoria = true;

				if (pecaAtual != NONE)
					ultimaPeca = pecaAtual;
			}
		}

		if (!podeTerVitoria)
			return GAMEOVER;

		return NONE;
	}

	private int verificarLinhas() {
		boolean podeTerVitoria = false;

		for (int linha = 0; linha < SIZE; linha++) {
			int peca = NONE, ultimaPeca = NONE;
			int qtdPeca = 0;
			int qtdEspacoVitoria = 0;

			for (int coluna = 0; coluna < SIZE; coluna++) {
				int pecaAtual = this.tab(linha, coluna);

				if (pecaAtual != ultimaPeca && pecaAtual != NONE) {
					qtdEspacoVitoria = 1;
				} else {
					qtdEspacoVitoria++;
				}

				// trocou zera contagem
				if (pecaAtual != peca) {
					qtdPeca = 1;
					peca = pecaAtual;
				} else {
					qtdPeca++;
				}

				if (qtdPeca >= LINELENG && peca != NONE)
					return peca;

				if (qtdEspacoVitoria >= LINELENG)
					podeTerVitoria = true;

				if (pecaAtual != NONE)
					ultimaPeca = pecaAtual;
			}
		}

		if (!podeTerVitoria)
			return GAMEOVER;

		return NONE;
	}
	
	//Erro de compilacao no susy
	void printTab(int a,int b,int c,int d) {
		
	}
}
