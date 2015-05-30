package Gomoku;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller {

	static Controller controller = new Controller();

	public static void main(String[] args) {
		new View();
	}

	private ModelInterface jogo = new Model();
	private int jogador = Model.BLACK;
	private boolean acabou = false;

	public Controller() {
		this.jogo.iniciaPartida();
	}

	public int tamanhoJogo() {
		return ModelInterface.SIZE;
	}

	public int getPosicaoVazia() {
		return ModelInterface.NONE;
	}

	public int getJogadorPecaBranca() {
		return ModelInterface.WHITE;
	}

	public int getJogadorPecaPreta() {
		return ModelInterface.BLACK;
	}

	public int getPecaEm(int linha, int coluna) {
		return this.jogo.tab(linha, coluna);
	}

	public ActionListener novaPartidaListener(TabuleiroViewInterface tabuleiro) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.this.jogo.iniciaPartida();
				Controller.this.acabou = false;
				tabuleiro.repaint();
			}
		};
	}

	public interface FileViewInterface {

		String arquivoParaSalvar();

		String arquivoParaCarregar();
	}

	public ActionListener salvarListener(FileViewInterface tratadorDeArquivos) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String arquivoParaSalvar = tratadorDeArquivos.arquivoParaSalvar();
				if (arquivoParaSalvar != null)
					Controller.this.jogo.salvaPartida(arquivoParaSalvar);
			}
		};
	}
	
	public ActionListener carregarListener(FileViewInterface tratadorDeArquivos, TabuleiroViewInterface tabuleiro) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String arquivoParaCarregar = tratadorDeArquivos.arquivoParaCarregar();
				if(arquivoParaCarregar != null)
					Controller.this.jogo.carregaPartida(arquivoParaCarregar);
				tabuleiro.repaint();
			}
		};
	}

	public interface TabuleiroViewInterface {

		int linhaNo(Point ponto);

		void repaint();

		int colunaNo(Point ponto);

		void venceu(int resultado);

		void jogadaInvalida();

		void fimDeJogo(int resultado);
	}

	public MouseListener jogadaListener(TabuleiroViewInterface tabuleiro) {
		return new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (!Controller.this.acabou) {
					int linha = tabuleiro.linhaNo(e.getPoint());
					int coluna = tabuleiro.colunaNo(e.getPoint());

					if (!Controller.this.jogo.joga(jogador, linha, coluna)) {
						tabuleiro.jogadaInvalida();
						return;
					}

					if (Controller.this.jogador == Model.BLACK)
						Controller.this.jogador = Model.WHITE;
					else
						Controller.this.jogador = Model.BLACK;

					e.getComponent().repaint();

					int resultado = Controller.this.jogo.verifica();
					if(resultado == ModelInterface.GAMEOVER) {
						Controller.this.acabou = true;
						tabuleiro.fimDeJogo(resultado);
					} else if (resultado != ModelInterface.NONE) {
						Controller.this.acabou = true;
						tabuleiro.venceu(resultado);
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		};
	}
}
