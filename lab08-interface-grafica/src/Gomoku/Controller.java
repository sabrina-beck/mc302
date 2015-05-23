package Gomoku;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Responsável pelo tratamento dos eventos gerados pela interface com o usuário,
 * e quando necessário, ativar os métodos do modelo para atualizar os dados.
 * Será responsável também pela ativação da apresentação (deverá ter, portanto,
 * o método main() )
 * 
 * @author Sabrina Beck Angelini <157240>
 **/
public class Controller {

	static Controller controller = new Controller();

	public static void main(String[] args) {
		new View();
	}

	private ModelInterface jogo = new Model();

	// representa o jogador atual
	private int jogador = Model.BLACK;

	// indica fim de jogo
	private boolean acabou = false;

	/**
	 * Cria uma instância do controller responsável por gerir o jogo, já inicia
	 * uma partida
	 **/
	public Controller() {
		this.jogo.iniciaPartida();
	}

	/**
	 * @return o tamanho do tabuleiro do jogo, lembrando que o tabuleiro é
	 *         quadrado
	 **/
	public int tamanhoJogo() {
		return ModelInterface.SIZE;
	}

	/**
	 * @return o valor equivalente à uma posição vazia no tabuleiro, para
	 *         auxiliar a exibição na View
	 **/
	public int getPosicaoVazia() {
		return ModelInterface.NONE;
	}

	/**
	 * @return o valor equivalente ao jogador da peça branca no tabuleiro, para
	 *         auxiliar a exibição na View
	 **/
	public int getJogadorPecaBranca() {
		return ModelInterface.WHITE;
	}

	/**
	 * @return o valor equivalente ao jogador da peça preta no tabuleiro, para
	 *         auxiliar a exibição na View
	 **/
	public int getJogadorPecaPreta() {
		return ModelInterface.BLACK;
	}

	/**
	 * @return a peca contida em uma determinada linha e coluna do jogo, para
	 *         auxiliar a exibição do tabuleiro na View
	 **/
	public int getPecaEm(int linha, int coluna) {
		return this.jogo.tab(linha, coluna);
	}

	/**
	 * @return tratador de evento de nova partida, reinicia o jogo do zero, além
	 *         de ordenar que a view atualize os dados exibidos para o usuário
	 **/
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

	/**
	 * Representa a interface que uma view que deseja persistir o jogo deve
	 * implementar e passar para o controller utilizar os dados recolhidos do
	 * usuário. Esses dados são o nome do arquivo onde o usuário deseja salvar o
	 * jogo e o nome do arquivo do qual o usuário deseja carregar um jogo antigo
	 **/
	public interface FileViewInterface {

		/**
		 * @return o nome do arquivo onde o usuário deseja salvar o jogo
		 **/
		String arquivoParaSalvar();

		/**
		 * @return o nome do arquivo do qual o usuário deseja carregar um jogo
		 *         antigo
		 **/
		String arquivoParaCarregar();
	}

	/**
	 * @return tratador de evento de salvar o jogo, através do arquivo informado
	 *         pela view, salva o jogo
	 * @param tratadorDeArquivos
	 *            é a interface que cuida da obtenção do nome do arquivo
	 *            informado pelo usuário
	 **/
	public ActionListener salvarListener(FileViewInterface tratadorDeArquivos) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String arquivoParaSalvar = tratadorDeArquivos.arquivoParaSalvar(); // Obtém
				                                                                   // o
				                                                                   // nome
				                                                                   // do
				                                                                   // arquivo
				if (arquivoParaSalvar != null)
					Controller.this.jogo.salvaPartida(arquivoParaSalvar);
			}
		};
	}

	/**
	 * @return tratador de evento de carrega um jogo previamente salvo em um
	 *         arquivo informado pela view
	 * @param tratadorDeArquivos
	 *            é a interface que cuida da obtenção do nome do arquivo
	 *            informado pelo usuário
	 **/
	public ActionListener carregarListener(FileViewInterface tratadorDeArquivos, TabuleiroViewInterface tabuleiro) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String arquivoParaCarregar = tratadorDeArquivos.arquivoParaCarregar();
				if (arquivoParaCarregar != null)
					Controller.this.jogo.carregaPartida(arquivoParaCarregar);
				tabuleiro.repaint();
			}
		};
	}

	/**
	 * Representa os dados que a view deve informar para o andamento e montagem
	 * do jogo
	 **/
	public interface TabuleiroViewInterface {

		/**
		 * @return linha correspondente ao ponto selecionado
		 * @param ponto selecionado na tela
		 **/
		int linhaNo(Point ponto);

		/**
		 * Atualiza a tela com o tabuleiro
		 **/
		void repaint();

		/**
		 * @return coluna correspondente ao ponto selecionado
		 * @param ponto selecionado na tela
		 **/
		int colunaNo(Point ponto);

		/**
		 * Exibe mensagem de que um jogador ganhou
		 * @param jogador vencedor
		 **/
		void venceu(int jogador);

		/**
		 * Exibe mensagem de jogada inválida
		 **/
		void jogadaInvalida();

		/**
		 * Exibe mensagem de fim de jogo
		 **/
		void fimDeJogo();
	}

	/**
	 * Tratador de eventos de jogada, cliques no tabuleiro
	 **/
	public MouseListener jogadaListener(TabuleiroViewInterface tabuleiro) {
		return new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				/**
				 * Trata evento de clique no tabuleiro
				 **/
				/** Se o jogo avabou o usuário não pode fazer nada **/
				if (!Controller.this.acabou) {
					int linha = tabuleiro.linhaNo(e.getPoint());
					int coluna = tabuleiro.colunaNo(e.getPoint());

					//Realiza a jogada e verifica jogada inválida
					if (!Controller.this.jogo.joga(jogador, linha, coluna)) {
						tabuleiro.jogadaInvalida();
						return;
					}

					// Faz a troca do jogador que fará a próxima jogada
					if (Controller.this.jogador == Model.BLACK)
						Controller.this.jogador = Model.WHITE;
					else
						Controller.this.jogador = Model.BLACK;

					//Atualiza tabuleiro na tela
					e.getComponent().repaint();

					//Verifica fim de jogo com ou sem vencedor
					int resultado = Controller.this.jogo.verifica();
					if (resultado == ModelInterface.GAMEOVER) {
						Controller.this.acabou = true;
						tabuleiro.fimDeJogo();
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
