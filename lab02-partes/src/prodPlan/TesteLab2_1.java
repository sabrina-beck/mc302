package prodPlan;

public class TesteLab2_1 {

	/**
	 * Cria um vetor de objetos Parte para testes.
	 * 
	 * @return o vetor criado.
	 */
	static Parte[] criaPartes() {
		Parte[] res = new Parte[8];
		res[0] = new Motor(112, "motor m112", "motor de avanco do cabecote",
				100.0f, 1.2f, 1.1f, 1250);
		res[1] = new Motor(114, "motor m114", "motor auxiliar", 60.0f, 0.6f,
				0.8f, 1250);
		res[2] = new Motor(111, "motor m111", "motor de ventilador", 70.0f,
				1.0f, 1.0f, 3000);
		res[3] = new Motor(110, "motor m110", "motor principal", 120.0f, 1.8f,
				1.5f, 1250);
		res[4] = new Parafuso(231, "parafuso p1",
				"parafuso de fixacao do cabecote", 2.5f, 100.0f, 8.0f);
		res[5] = new Parafuso(232, "parafuso p2",
				"parafuso de fixacao do motor", 2.5f, 80.0f, 6.0f);
		res[6] = new Parafuso(233, "parafuso p3",
				"parafuso de fixacao do ventilador", 2.0f, 60.0f, 6.0f);
		res[7] = new Parafuso(234, "parafuso p4", "parafuso de uso geral",
				3.0f, 120.0f, 12.0f);
		return res;
	}

	/**
	 * Escreve na saida padrao o conteudo de um vetor de objetos Parte
	 * devidamente convertidos para String.
	 * 
	 * @param titulo
	 *            texto a ser mostrado antes da listagem
	 * @param partes
	 *            vetor de objetos Parte a ser listado
	 */
	static void listaPartes(String titulo, Parte[] partes) {
		System.out.println(titulo);
		for (int i = 0; i < partes.length; i++)
			System.out.println(partes[i].toString());
		System.out.println();
	}

	/**
	 * Cria um vetor de objetos Item a ser usado nos testes
	 * 
	 * @param partes
	 *            vetor de objetos Parte a partir do qual serao criados os
	 *            objetos Item que formarão o vetor
	 * @return vetor de objetos Item
	 */
	static Item[] criaItens(Parte[] partes) {
		Item[] res = new Item[4];
		res[0] = new Item(partes[0], 10);
		res[1] = new Item(partes[5], 50);
		res[2] = new Item(partes[7], 30);
		res[3] = new Item(partes[2], 5);
		return res;
	}

	/**
	 * Escreve na saida padrao o conteudo de um vetor de objetos Item,
	 * devidamente convertidos para String.
	 * 
	 * @param titulo
	 *            texto apresentado antes da listagem.
	 * @param itens
	 *            vetor de objetos Item a ser listado.
	 */
	static void listaItens(String titulo, Item[] itens) {
		float total = 0;
		System.out.println(titulo);
		for (int i = 0; i < itens.length; i++) {
			System.out.println(itens[i].toString());
			total += itens[i].calculaValor();
		}
		System.out.println("Valor total:" + total);
		System.out.println();
	}

	/**
	 * Metodo principal que dispara os testes.
	 * 
	 * @param args
	 *            o de sempre.
	 */
	public static void main(String[] args) {
		Parte[] partes = criaPartes();
		Item[] itens = criaItens(partes);
		listaPartes("*** Partes utilizadas na producao ****", partes);
		listaItens("*** Itens solicitados ***", itens);
	}

}