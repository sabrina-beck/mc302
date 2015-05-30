package tree;

public interface Hierarquia {

	// Verifica se este nó descende do nó passado como parâmetro
	public boolean descende(Node n);

	// Verifica se o nó passado como parâmetro pertence a esta árvore.
	public Boolean pertence(Node n);

}