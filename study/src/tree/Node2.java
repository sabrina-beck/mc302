package tree;

public class Node2 extends Node implements Hierarquia {

	private Node father;

	public Node2(String info, Node left, Node right, Node father) {
		super(info, left, right);
		this.father = father;
	}

	@Override
	public boolean descende(Node n) {
		Node paiAtual = this.father;
		do {
			if (this.father.equals(n))
				return true;
			if (!(paiAtual instanceof Node2))
				return false;
			paiAtual = ((Node2) paiAtual).father;
		} while (paiAtual != null);

		return false;
	}

	@Override
	public Boolean pertence(Node n) {
		return this.pertence(this, n);
	}

	private Boolean pertence(Node root, Node element) {
		if (root.equals(element))
			return true;
		// O método auxiliar é necessário pois a classe Node não tem definição
		// de pertence, logo não dá para chamar this.left.pertence(n), para
		// evitar fazer cast e correr o risco de ter uma ClassCastException
		// (caso left seja uma instância de Node e não de Node2) esse método
		// auxiliar me ajuda a verificar a regra de negócio sem me limitar à
		// árvores que contenham apenas Node2 como nós
		return pertence(this.left, element) || pertence(this.right, element);
	}
}