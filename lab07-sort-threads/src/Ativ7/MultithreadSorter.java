package Ativ7;

public class MultithreadSorter<T> implements Runnable {

	private static final int M = 7;
	
	private T[] lista;
	private Comparator<T> comparator;

	public MultithreadSorter(T[] lista, Comparator<T> comparator) {
		this.lista = lista;
		this.comparator = comparator;
	}

	public void sort() throws InterruptedException {
		Thread thread = new Thread(this);
		thread.start();
		thread.join();
	}

	@Override
	public void run() {
		int indicePivo = escolherPivo(0, this.lista.length - 1);
		

	}

	private int escolherPivo(int inicio, int fim) {
	    int pivo = inicio;
	    
	    while(inicio <= fim) {
	    	if(comparator.precede(this.lista[inicio], this.lista[fim])) {
	    		
	    	}
	    }
	    
	    return 0;
    }

}
