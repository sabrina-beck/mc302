package Ativ7;

public class MultithreadSorter<T> implements Runnable {

	private static final int M = 7;

	private T[] lista;
	private Comparator<T> comparator;

	private int indiceInicio;
	private int indiceFim;

	public MultithreadSorter(T[] lista, Comparator<T> comparator) {
		this(lista, comparator, 0, lista.length - 1);
	}

	private MultithreadSorter(T[] lista, Comparator<T> comparator, int indiceInicio, int indiceFim) {
		this.lista = lista;
		this.comparator = comparator;
		this.indiceInicio = indiceInicio;
		this.indiceFim = indiceFim;
	}

	public void sort() throws InterruptedException {
		Thread thread = new Thread(this);
		thread.start();
		thread.join();
	}

	@Override
	public void run() {
		try {
	        ordenar(this.indiceInicio, this.indiceFim);
        } catch (InterruptedException e) {
	        e.printStackTrace();
        }
	}

	private void ordenar(int inicio, int fim) throws InterruptedException {
		if (tamanho(inicio, fim) > M)
			quickSort(inicio, fim);
		else
			bubbleSort(inicio, fim);
	}

	private void quickSort(int inicio, int fim) throws InterruptedException {
		int esquerda = inicio;
		int direita = fim;
		int indicePivot = (inicio + fim) / 2;
		T pivot = this.lista[indicePivot];

		do {
			while (comparator.precede(this.lista[esquerda], pivot))
				esquerda++;
			while (comparator.precede(pivot, this.lista[direita]))
				direita--;
			if (esquerda <= direita) {
				troca(esquerda, direita);
				esquerda++;
				direita--;
			}
		} while (esquerda < direita);

		Thread filhaEsquerda = null;
		Thread filhaDireita = null;

		if (inicio < direita)
			filhaEsquerda = threadSort(inicio, direita);

		if (fim > esquerda)
			filhaDireita = threadSort(esquerda, fim);

		if (filhaEsquerda != null)
			filhaEsquerda.join();
		if (filhaDireita != null)
			filhaDireita.join();
	}

	private int tamanho(int inicio, int fim) {
		return fim - inicio + 1;
	}

	private void bubbleSort(int inicio, int fim) {
		for (int i = tamanho(inicio, fim) - 1; i > 0; i--)
			for (int j = 0; j < i; j++)
				if (comparator.precede(this.lista[j + 1], this.lista[j]))
					troca(j, j + 1);
	}

	private Thread threadSort(int inicio, int fim) {
		MultithreadSorter<T> multithreadSorter = new MultithreadSorter<T>(lista, comparator, inicio, fim);
		Thread thread = new Thread(multithreadSorter);
		thread.start();
		return thread;
	}

	private void troca(int indice1, int indice2) {
		T aux = this.lista[indice1];
		this.lista[indice1] = this.lista[indice2];
		this.lista[indice2] = aux;
	}

}
