package prodPlan;
import java.util.List;

/**
 * Teste do lab3 usando um objeto 'visitor' (um dia voce vai saber o que e isso...)
 *
 */

public class TesteLab3_2 {
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		List<Item> lista = Dados.criaListaItens();
		Lab3Visitor_1.visit(lista);
	}
}
