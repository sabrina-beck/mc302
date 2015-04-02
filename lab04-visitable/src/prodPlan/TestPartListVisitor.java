package prodPlan;

import java.util.List;

public class TestPartListVisitor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Item> lista = Dados.criaListaItens();
        PartListVisitor plv = new PartListVisitor();
        String partList = plv.visit(lista);
        System.out.println(partList);
	}

}
