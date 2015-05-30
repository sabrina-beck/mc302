package change;

import tree.Node;

public class Changer {

	public static void main(String[] args) {
		Node n1 = new Node("LALA", null, null);
		Node n2 = new Node("LULU", null, null);

		System.out.println("Node 1: " + n1.getInfo());
		System.out.println("Node 2: " + n2.getInfo());

		System.out.println("Changing...");
		wrongSwap(n1, n2);

		System.out.println("Node 1: " + n1.getInfo());
		System.out.println("Node 2: " + n2.getInfo());

		System.out.println("Really changing now...");
		swap(n1, n2);

		System.out.println("Node 1: " + n1.getInfo());
		System.out.println("Node 2: " + n2.getInfo());
	}

	/**
	 * Faltam dados na pergunta, existem os getters & setters? se não, o método
	 * de troca está no mesmo pacote que a classe node para poder ter acesso aos
	 * campos protected? Se nenhum dos dois, o método tem que ter a mesma
	 * assinatura (se sim impossível fazer) se não dá pra fazer retornando um
	 * vetor com os elementos na ordem inversa, passando um objeto que tem dois
	 * atributos node que e troca-los...
	 **/
	private static void swap(Node n1, Node n2) {
		String info1 = n1.getInfo();
		Node right1 = n1.getRight();
		Node left1 = n1.getLeft();

		n1.setInfo(n2.getInfo());
		n1.setLeft(n2.getLeft());
		n1.setRight(n2.getRight());

		n2.setInfo(info1);
		n2.setLeft(left1);
		n2.setRight(right1);
	}

	/**
	 * Não funciona porque n1 e n2 são variáveis locais a esse método, trocá-las
	 * aqui não surtirá efeito no método de chamada, pensando em apontadores,
	 * Java passa objetos por referência, ou seja, passa o apontador para um
	 * objeto para os métodos que chama, logo alterar o conteúdo/atributos de n1
	 * e n2 surtirá efeito no método de chamada, mas, os apontadores em si são
	 * passados por valor, logo, trocar os apontadores entre si não surtirá
	 * efeito no método de chamada, pois estarei apenas mudando o conteúdo de
	 * variáveis locais ao método wrongSwap
	 **/
	private static void wrongSwap(Node n1, Node n2) {
		Node aux = n1;
		n1 = n2;
		n2 = aux;
	}

}
