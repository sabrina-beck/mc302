package tree;

public class Node {

	Node left;
	Node right;
	String info;

	public Node(String info, Node left, Node right) {
		this.left = left;
		this.right = right;
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int altura() {
		// nunca dará zero, pois sempre tem o elemento raíz que o nó da
		// instância atual
		return this.altura(this);
	}

	private int altura(Node root) {
		if (root == null)
			return 0;

		int alturaEsquerda = this.left.altura();
		int alturaDireita = this.right.altura();

		if (alturaDireita < alturaEsquerda)
			return alturaEsquerda;
		return alturaDireita;
	}

	public Node clone() {
		return this.clone(this);
	}

	private Node clone(Node root) {
		if (root == null)
			return null;
		// se usar this.left.clone() dará NullPointerException numa folha
		return new Node(new String(this.info), clone(this.left), clone(this.right));
	}

	static boolean isomorfas(Node n1, Node n2) {
		if (n1 == null && n2 == null)
			return true;
		if (n1 == null || n2 == null)
			return false;
		if (!n1.info.equals(n2.info))
			return false;
		return isomorfas(n1.left, n2.left) && isomorfas(n1.right, n2.right) && isomorfas(n1.right, n2.left)
		        && isomorfas(n1.left, n2.right);
	}
}