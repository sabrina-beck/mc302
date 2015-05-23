package Gomoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.MessageFormat;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Gomoku.Controller.FileViewInterface;
import Gomoku.Controller.TabuleiroViewInterface;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Color BACKGROUND_COLOR = new Color(189, 189, 189);

	private Controller controller = Controller.controller;

	public View() {
		TabuleiroPanel pnltabuleiro = new TabuleiroPanel();
		pnltabuleiro.addMouseListener(controller.jogadaListener(pnltabuleiro));

		JButton btnNovaPartida = new ColouredJButton("Nova Partida");
		btnNovaPartida.addActionListener(controller.novaPartidaListener(pnltabuleiro));

		TratadorDeArquivos tratadorDeArquivos = new TratadorDeArquivos();

		JButton btnSalvar = new ColouredJButton("Salvar");
		btnSalvar.addActionListener(controller.salvarListener(tratadorDeArquivos));

		JButton btnCarregar = new ColouredJButton("Carregar");
		btnCarregar.addActionListener(controller.carregarListener(tratadorDeArquivos, pnltabuleiro));

		GridLayout gridLayout = new GridLayout(15, 1);
		gridLayout.setVgap(2);

		JPanel pnlBotoes = new JPanel();
		pnlBotoes.setLayout(gridLayout);
		pnlBotoes.add(btnNovaPartida);
		pnlBotoes.add(btnSalvar);
		pnlBotoes.add(btnCarregar);
		pnlBotoes.setBackground(BACKGROUND_COLOR);

		// encerra o processo quando a janela é fechada
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(590, 500);
		this.setTitle("Gomoku");
		this.setLocationRelativeTo(null); // centraliza na tela
		this.getContentPane().setBackground(BACKGROUND_COLOR);

		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(pnltabuleiro, BorderLayout.CENTER);
		container.add(pnlBotoes, BorderLayout.EAST);

		this.setVisible(true);
	}

	private class TratadorDeArquivos implements FileViewInterface {

		final String TIPO_ARQUIVO = ".gomoku";

		private JFileChooser chooser;

		public TratadorDeArquivos() {
			this.chooser = new JFileChooser();

			FileFilter filter = new FileNameExtensionFilter("Gomoku file", new String[] { "gomoku", "gomoku" });
			this.chooser.addChoosableFileFilter(filter);
			this.chooser.setFileFilter(filter);
		}

		@Override
		public String arquivoParaSalvar() {
			int opcao = chooser.showSaveDialog(View.this);
			if (opcao == JFileChooser.APPROVE_OPTION) {
				return adicionarTipoGomoku(this.chooser.getSelectedFile().getAbsolutePath());
			}
			return null;
		}

		@Override
		public String arquivoParaCarregar() {
			int opcao = chooser.showOpenDialog(View.this);
			if (opcao == JFileChooser.APPROVE_OPTION) {
				return adicionarTipoGomoku(this.chooser.getSelectedFile().getAbsolutePath());
			}
			return null;
		}

		private String adicionarTipoGomoku(String arquivo) {
			if (!arquivo.endsWith(TIPO_ARQUIVO))
				arquivo += TIPO_ARQUIVO;
			return arquivo;
		}
	}

	private class ColouredJButton extends JButton {

		private static final long serialVersionUID = 1L;
		private final Color BUTTON_BACKGROUND_COLOR = new Color(97, 97, 97);
		private final Color HOVER_BUTTON_BACKGROUND_COLOR = new Color(117, 117, 117);
		private final Color CLICK_BUTTON_BACKGROUND_COLOR = new Color(117, 117, 117);

		public ColouredJButton(String text) {
			super(text);
			this.setForeground(Color.WHITE);
			this.setBackground(BUTTON_BACKGROUND_COLOR);
			this.addMouseListener(new EfeitosBotaoListener());
		}

		public void setBackground(Color color) {
			super.setBackground(color);
			this.setBorder(new LineBorder(color, 10));
		}

		private class EfeitosBotaoListener implements MouseListener {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				ColouredJButton.this.setBackground(CLICK_BUTTON_BACKGROUND_COLOR);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				ColouredJButton.this.setBackground(BUTTON_BACKGROUND_COLOR);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				ColouredJButton.this.setBackground(HOVER_BUTTON_BACKGROUND_COLOR);
				ColouredJButton.this.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				ColouredJButton.this.setBackground(BUTTON_BACKGROUND_COLOR);
				ColouredJButton.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}

	private class TabuleiroPanel extends JPanel implements TabuleiroViewInterface {

		private static final long serialVersionUID = 1L;

		static final int SQUARE_LENGTH = 30;
		static final int INITIAL_X = 10;
		static final int INITIAL_Y = 10;

		@Override
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;

			g2d.setColor(BACKGROUND_COLOR);
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			g2d.setColor(Color.BLACK);

			int screenSize = controller.tamanhoJogo() * SQUARE_LENGTH;
			g2d.drawRect(INITIAL_X, INITIAL_Y, screenSize, screenSize);
			for (int x = INITIAL_X; x < screenSize; x += SQUARE_LENGTH) {
				g2d.drawLine(x, INITIAL_Y, x, INITIAL_Y + screenSize);
			}

			for (int y = INITIAL_Y; y < screenSize; y += SQUARE_LENGTH)
				g2d.drawLine(INITIAL_X, y, INITIAL_X + screenSize, y);

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

		private int posicaoDaColuna(int linha) {
			return linha * SQUARE_LENGTH + INITIAL_Y;
		}

		private int posicaoDaLinha(int coluna) {
			return coluna * SQUARE_LENGTH + INITIAL_X;
		}

		@Override
		public int linhaNo(Point ponto) {
			return (int) Math.floor((ponto.getY() - INITIAL_Y) / SQUARE_LENGTH);
		}

		@Override
		public int colunaNo(Point ponto) {
			return (int) Math.floor((ponto.getX() - INITIAL_X) / SQUARE_LENGTH);
		}

		@Override
		public void venceu(int resultado) {
			String vencedor = resultado == controller.getJogadorPecaBranca() ? "Branca" : "Preta";
			JOptionPane.showMessageDialog(this,
			        MessageFormat.format("Parabéns!! O jogador da peça {0} venceu!", vencedor), "Gomoku!",
			        JOptionPane.INFORMATION_MESSAGE);
		}

		@Override
		public void jogadaInvalida() {
			JOptionPane.showMessageDialog(this, "Jogada Inválida!", "Gomoku!", JOptionPane.ERROR_MESSAGE);
		}

		@Override
		public void fimDeJogo(int resultado) {
			JOptionPane.showMessageDialog(this, "Fim de Jogo! Infelizmente nenhum jogador ganhou.", "Gomoku!",
			        JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
