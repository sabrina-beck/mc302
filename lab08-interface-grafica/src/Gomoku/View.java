package Gomoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.text.MessageFormat;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Gomoku.Controller.FileViewInterface;
import Gomoku.Controller.TabuleiroViewInterface;

/**
 * Representa a interface com o usuário do jogo Gomoku.
 * 
 * @author Sabrina Beck Angelini <157240>
 **/
public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Color BACKGROUND_COLOR = new Color(189, 189, 189);

	private Controller controller = Controller.controller;

	/**
	 * Cria e exibe a tela do jogo Gomoku com o tabuleiro do jogo e os botões de
	 * reiniciar a partida, salvar e carregar
	 **/
	public View() {
		/**
		 * Cria painel do tabuleiro com o tratador de eventos de mouse fornecido
		 * pelo controller
		 **/
		TabuleiroPanel pnltabuleiro = new TabuleiroPanel();
		pnltabuleiro.addMouseListener(controller.jogadaListener(pnltabuleiro));

		/** Criação dos botões com seus devidos eventos de clique **/
		JButton btnNovaPartida = new JButton("Nova Partida");
		btnNovaPartida.addActionListener(controller.novaPartidaListener(pnltabuleiro));

		TratadorDeArquivos tratadorDeArquivos = new TratadorDeArquivos();
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(controller.salvarListener(tratadorDeArquivos));
		JButton btnCarregar = new JButton("Carregar");
		btnCarregar.addActionListener(controller.carregarListener(tratadorDeArquivos, pnltabuleiro));

		/**
		 * Criação do painel de botões que são organizados em um grid layout
		 * vertical
		 **/
		GridLayout gridLayout = new GridLayout(15, 1);
		gridLayout.setVgap(2);

		JPanel pnlBotoes = new JPanel();
		pnlBotoes.setLayout(gridLayout);
		pnlBotoes.add(btnNovaPartida);
		pnlBotoes.add(btnSalvar);
		pnlBotoes.add(btnCarregar);
		pnlBotoes.setBackground(BACKGROUND_COLOR); // Altera a cor de fundo

		// encerra o processo quando a janela é fechada
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 500);
		this.setTitle("Gomoku");
		this.setLocationRelativeTo(null); // centraliza na tela
		this.getContentPane().setBackground(BACKGROUND_COLOR); // Altera cor de
		                                                       // fundo

		/**
		 * Organiza os componentes na tela: o tabuleiro vai na região central e
		 * o painel de botões se localiza na coluna à direita
		 **/
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(pnltabuleiro, BorderLayout.CENTER);
		container.add(pnlBotoes, BorderLayout.EAST);

		// Deixa a tela visível para o usuário
		this.setVisible(true);
	}

	/**
	 * Responsável pela exibição de um JFileChooser e retorna o nome dos
	 * arquivos escolhidos
	 **/
	private class TratadorDeArquivos implements FileViewInterface {

		final String TIPO_ARQUIVO = ".gomoku";

		private JFileChooser chooser;

		public TratadorDeArquivos() {
			this.chooser = new JFileChooser();

			FileFilter filter = new FileNameExtensionFilter("Gomoku file", new String[] { "gomoku", "gomoku" });
			this.chooser.addChoosableFileFilter(filter);
			this.chooser.setFileFilter(filter);
		}

		/**
		 * Exibe JFileChooser para salvar arquivo
		 **/
		@Override
		public String arquivoParaSalvar() {
			int opcao = chooser.showSaveDialog(View.this);
			if (opcao == JFileChooser.APPROVE_OPTION) {
				return adicionarTipoGomoku(this.chooser.getSelectedFile().getAbsolutePath());
			}
			return null;
		}

		/**
		 * Exibe JFileChooser para carregar arquivo
		 **/
		@Override
		public String arquivoParaCarregar() {
			int opcao = chooser.showOpenDialog(View.this);
			if (opcao == JFileChooser.APPROVE_OPTION) {
				return adicionarTipoGomoku(this.chooser.getSelectedFile().getAbsolutePath());
			}
			return null;
		}

		/**
		 * Adiciona o tipo específico de arquivo tratado por esse programa
		 **/
		private String adicionarTipoGomoku(String arquivo) {
			if (!arquivo.endsWith(TIPO_ARQUIVO))
				arquivo += TIPO_ARQUIVO;
			return arquivo;
		}
	}

	/**
	 * Painel responsável por exibir o tabuleiro na tela e retornar informações
	 * importantes para o controller
	 **/
	private class TabuleiroPanel extends JPanel implements TabuleiroViewInterface {

		private static final long serialVersionUID = 1L;

		static final int SQUARE_LENGTH = 30;
		static final int INITIAL_X = 10;
		static final int INITIAL_Y = 10;

		/**
		 * Desenha o tabuleiro
		 **/
		@Override
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;

			// /Pinta o fundo
			g2d.setColor(BACKGROUND_COLOR);
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			g2d.setColor(Color.BLACK);

			// Desenha retangulo externo do tabuleiro
			int screenSize = controller.tamanhoJogo() * SQUARE_LENGTH;
			g2d.drawRect(INITIAL_X, INITIAL_Y, screenSize, screenSize);

			// Desenha linhas e colunas
			for (int x = INITIAL_X; x < screenSize; x += SQUARE_LENGTH) {
				g2d.drawLine(x, INITIAL_Y, x, INITIAL_Y + screenSize);
			}
			for (int y = INITIAL_Y; y < screenSize; y += SQUARE_LENGTH)
				g2d.drawLine(INITIAL_X, y, INITIAL_X + screenSize, y);

			// Desenha as peças
			for (int linha = 0; linha < controller.tamanhoJogo(); linha++)
				for (int coluna = 0; coluna < controller.tamanhoJogo(); coluna++) {
					int peca = controller.getPecaEm(linha, coluna);
					if (controller.getJogadorPecaBranca() == peca)
						g2d.setColor(Color.WHITE);
					else
						g2d.setColor(Color.BLACK);
					if (peca != controller.getPosicaoVazia()) {
						g2d.fillOval(posicaoDaColuna(coluna), posicaoDaLinha(linha), SQUARE_LENGTH, SQUARE_LENGTH);
					}
				}
		}

		/**
		 * @return calcula posicao da coluna
		 **/
		private int posicaoDaColuna(int linha) {
			return linha * SQUARE_LENGTH + INITIAL_Y;
		}

		/**
		 * @return calcula posicao da linha
		 **/
		private int posicaoDaLinha(int coluna) {
			return coluna * SQUARE_LENGTH + INITIAL_X;
		}

		/**
		 * @return linha correspondente ao ponto selecionado
		 * @param ponto
		 *            selecionado na tela
		 **/
		@Override
		public int linhaNo(Point ponto) {
			return (int) Math.floor((ponto.getY() - INITIAL_Y) / SQUARE_LENGTH);
		}

		/**
		 * @return coluna correspondente ao ponto selecionado
		 * @param ponto
		 *            selecionado na tela
		 **/
		@Override
		public int colunaNo(Point ponto) {
			return (int) Math.floor((ponto.getX() - INITIAL_X) / SQUARE_LENGTH);
		}

		/**
		 * Exibe mensagem de que um jogador ganhou
		 * 
		 * @param jogador
		 *            vencedor
		 **/
		@Override
		public void venceu(int resultado) {
			String vencedor = resultado == controller.getJogadorPecaBranca() ? "Branca" : "Preta";
			JOptionPane.showMessageDialog(this,
			        MessageFormat.format("Parabéns!! O jogador da peça {0} venceu!", vencedor), "Gomoku!",
			        JOptionPane.INFORMATION_MESSAGE);
		}

		/**
		 * Exibe mensagem de jogada inválida
		 **/
		@Override
		public void jogadaInvalida() {
			JOptionPane.showMessageDialog(this, "Jogada Inválida!", "Gomoku!", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 * Exibe mensagem de fim de jogo
		 **/
		@Override
		public void fimDeJogo() {
			JOptionPane.showMessageDialog(this, "Fim de Jogo! Infelizmente nenhum jogador ganhou.", "Gomoku!",
			        JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
