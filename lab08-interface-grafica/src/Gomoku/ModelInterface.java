package Gomoku;

public interface ModelInterface {
	
	static final int NONE     = 0;
	static final int BLACK    = 1;
	static final int WHITE    = 2;
	static final int GAMEOVER = 3;
	static final int LINELENG = 5;
	
	static final int SIZE = 15;
	

	/**
	 * Inicia nova partida, 'zerando' o tabuleiro
	 */
	public void iniciaPartida();
	
	/**
	 * Salva a partida atual sob um nome, para continuar depois
	 * @param nome
	 */
	public boolean salvaPartida(String nome);
	
	/**
	 * Restaura a partida atual, para continuar o jogo
	 */
	public boolean carregaPartida(String nome);
	
	/**
	 * Registra uma jogada.
	 * @param jogador
	 * @param x
	 * @param y
	 * @return jogada valida (true) ou invalida (false)
	 */
	public boolean joga(int jogador, int x, int y);
	
	/**
	 * Retorna a peca na posicao (x,y) :
	 *  (NONE, BLACK, WHITE)
	 * @param x
	 * @param y
	 * @return
	 */
	public int tab(int x, int y);
	
	/**
	 * Verifica se existe um vencedor (algum jogador 
	 * fez '5 em linha')
	 * @return
	 */
    public int verifica();
    
}
