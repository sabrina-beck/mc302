package Ativ7;

/**
 * Classe que ordena um vetor de elementos com os métodos paralelizando o
 * algoritmo com threads
 * 
 * @author Sabrina Beck Angelini <157240>
 **/
public class MultithreadSorter<T> implements Runnable {

	/**
	 * Constante que limita o tamanho mínimo que um vetor precisa ter para que
	 * seja feita com quicksort e novas threads, evita a criação denecessária de
	 * threads para ordernar vetores muito pequenos
	 * 
	 * Obs: Um nome melhor para essa constante seria TAMANHO_MINIMO, no entanto,
	 * seguirei o enunciado que chama a constante de M
	 **/
	private static final int M = 7;

	/** Vetor a ser ordenado **/
	private T[] lista;

	/** Responsável pela comparação entre dois objetos **/
	private Comparator<T> comparator;

	/**
	 * Índices de início e fim do vetor a ser analisado, esses indíces são do
	 * campo lista
	 **/
	private int indiceInicio;
	private int indiceFim;

	/**
	 * Cria um MultithreadSorter para ordenação de uma lista. Obs: o índice
	 * inicial da lista é 0 e o final é length - 1
	 * 
	 * @param lista
	 *            lista a ser ordenada
	 * @param comparator
	 *            comparator responsável pela comparação de dois objetos da
	 *            lista passada como parâmetro
	 **/
	public MultithreadSorter(T[] lista, Comparator<T> comparator) {
		this(lista, comparator, 0, lista.length - 1);
	}

	/**
	 * Construtor para uso interno, recebe o intervalo de índices (início e fim)
	 * onde estão os elementos a serem ordenados
	 * 
	 * @param lista
	 *            lista a ser ordenada
	 * @param comparator
	 *            comparator responsável pela comparação de dois objetos da
	 *            lista passada como parâmetro
	 * @param indiceInicio
	 *            índice de início da lista a ser ordenada
	 * @param indiceFim
	 *            índice de fim da lista a ser ordenada
	 **/
	private MultithreadSorter(T[] lista, Comparator<T> comparator, int indiceInicio, int indiceFim) {
		this.lista = lista;
		this.comparator = comparator;
		this.indiceInicio = indiceInicio;
		this.indiceFim = indiceFim;
	}

	/**
	 * Ordena a lista usada na construção desse Sorter.
	 **/
	public void sort() throws InterruptedException {
		// @formatter:off
		/** 
		    Enunciado: "Cria um objeto thread, baseado numa nova instância da classe MultithreadSorter"
		    Não há a necessidade de sempre que eu chamar o método sort do MultithreadSorter,
		    criar uma nova instância, pois, a instância que chamar seu sort() será desperdiçada
		    Vou colocar comentado como seria a implementação de acordo com o enunciado:
		 **/
		
		/**
		 * Thread thread = new Thread(new MultithreadSorter<T>(this.lista, this.comparator, this.indiceInicio, this.indiceFim));
		 * 
		 * ou simplemente chamaria o método threadSort()
		 **/
		// @formatter:on
		Thread thread = new Thread(this);
		// Inicia a execução da thread
		thread.start();
		// Espera pelo encerramento dessa thread
		thread.join();
	}

	/**
	 * Método previsto na interface Runnable. Responsável pela implementação do
	 * algoritmo 'quicksort concorrente'
	 **/
	@Override
	public void run() {
		try {
			ordenar(this.indiceInicio, this.indiceFim);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ordena os elementos do campo lista que estão compreendidos entre os
	 * índices inico e fim, inclusive eles
	 * 
	 * @param inicio
	 *            índice de início dos elementos a serem ordenados
	 * @param fim
	 *            índice de fim dos elementos a serem ordenados
	 **/
	private void ordenar(int inicio, int fim) throws InterruptedException {
		// Se o vetor tiver um tamanho menor que o especificado pela constante
		// M, então ele será ordenado pelo bubblesort senão, será ordenado pelo
		// quicksort concorrente
		if (tamanho(inicio, fim) > M)
			quickSort(inicio, fim);
		else
			bubbleSort(inicio, fim);
	}

	/**
	 * Ordena os elementos compreendidos entre os índices início e fim da lista
	 * da intância atual
	 * 
	 * @param inicio
	 *            índice de início dos elementos a serem ordenados
	 * @param fim
	 *            índice de fim dos elementos a serem ordenados
	 **/
	private void quickSort(int inicio, int fim) throws InterruptedException {
		int esquerda = inicio; // índice da parte esqueda do vetor
		int direita = fim; // índice da parte direita do vetor
		/**
		 * o pivot é escolhido como o elemento central do vetor - o método de
		 * escolha não foi especificado pelo enunciado
		 **/
		int indicePivot = (inicio + fim) / 2;
		T pivot = this.lista[indicePivot];

		do {
			/**
			 * Aumenta o índice esquerdo até encontrar um elemento maior que o
			 * pivot nessa metade do vetor
			 **/
			while (comparator.precede(this.lista[esquerda], pivot))
				esquerda++;
			/**
			 * Decrementa o índice direito até encontrar um elemento menor que o
			 * pivot nessa metade do vetor
			 **/
			while (comparator.precede(pivot, this.lista[direita]))
				direita--;
			/**
			 * Dado que os índices não saíram de sua metado do vetor, os
			 * elementos por eles indicados, estão na parte errada do vetor (em
			 * termos de ordenação), logo eles são trocados ficando no lugar
			 * certo, A repetição desse procedimento fará com que todos os
			 * elementos à esquerda do pivot sejam menor que o pivot e todos os
			 * elementos à direita do pivot sejam maiores que o pivot
			 **/
			if (esquerda <= direita) {
				troca(esquerda, direita);
				esquerda++;
				direita--;
			}
		} while (esquerda <= direita);

		/**
		 * Tendo dois subvetores (a esquerda do pivot e a direita do pivot) que
		 * precisam ser ordenados, eles podem ser ordenados em paralelo uma vez
		 * que seus elementos estão em posições diferentes da lista
		 **/

		Thread filhaEsquerda = null;
		Thread filhaDireita = null;

		if (inicio < direita)
			filhaEsquerda = threadSort(inicio, direita);

		if (fim > esquerda)
			filhaDireita = threadSort(esquerda, fim);

		/**
		 * A thread mãe deve esperar a execução de suas threads filhas para que
		 * o programa não se encerre antes de sua execução
		 **/
		if (filhaEsquerda != null)
			filhaEsquerda.join();
		if (filhaDireita != null)
			filhaDireita.join();
	}

	/**
	 * Calcula o tamanho de um subvetor compreendido entre os índices inicio e
	 * fim
	 * 
	 * @param inicio
	 *            indice de inicio do subvetor
	 * @param fim
	 *            indice de fim do subvetor
	 **/
	private int tamanho(int inicio, int fim) {
		return fim - inicio + 1;
	}

	/**
	 * Responsável por ordenar o subvetor com indices de inicio e fim com o
	 * método bubble sort
	 * 
	 * @param inicio
	 *            indice de inicio do subvetor
	 * @param fim
	 *            indice de fim do subvetor
	 **/
	private void bubbleSort(int inicio, int fim) {
		for (int i = fim; i > inicio; i--)
			for (int j = inicio; j < i; j++)
				if (comparator.precede(this.lista[j + 1], this.lista[j]))
					troca(j, j + 1);
	}

	/**
	 * Responsável por ordenar o subvetor com indices de inicio e fim com o
	 * thread sort, criar um MultithreadSorter para o subvetor especificado e o
	 * ordena
	 * 
	 * @param inicio
	 *            indice de inicio do subvetor
	 * @param fim
	 *            indice de fim do subvetor
	 **/
	private Thread threadSort(int inicio, int fim) {
		// Não é possível chamar o método sort do multithreadSorter uma vez que
		// ele não retorna a thread correspondente e dá o join em seu interior
		// A utilização desse método dentro quicksort permite que duas threads
		// que ordenam partes diferentes do vetor executem ao mesmo tempo sem
		// que uma tenha que esperar a outra, por isso esse método não chama o
		// join nem o sort
		MultithreadSorter<T> multithreadSorter = new MultithreadSorter<T>(lista, comparator, inicio, fim);
		Thread thread = new Thread(multithreadSorter);
		thread.start();
		return thread;
	}

	/**
	 * Troca dois elementos na lista dessa instância
	 * 
	 * @param indice1
	 *            indice de um dos elementos a serem trocados de posição
	 * @param indice2
	 *            indice do outro elemento a ser trocado de posição
	 **/
	private void troca(int indice1, int indice2) {
		T aux = this.lista[indice1];
		this.lista[indice1] = this.lista[indice2];
		this.lista[indice2] = aux;
	}

}
