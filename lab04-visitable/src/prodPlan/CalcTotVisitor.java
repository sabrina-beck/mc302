package prodPlan;

import java.util.Collections;
import java.util.List;

/**
 * Exemplo de Visitor simples, que calcula o valor total correspondente
 * a uma lista de itens.
 * Forma de uso:
 * 	   A lista de itens cujo valor se deseja calcular deve ser passada como 
 *     parâmetro para o método visit(List<Item> lista), estático, que cria
 *     o objeto  CalcTotVisitor e dispara a visita aos elementos da lista.
 *
 */
public class CalcTotVisitor extends ProdPlanVisitor {

	
	/**
	 * Resultado do cálculo
	 */
	private Float total;  
	
	/**
	 * Construtor, sem parâmetros
	 * (para ser utilizado apenas nesta classe)
	 */
	private CalcTotVisitor(){
		total = 0.0f;
	}
	
	/**
	 * Retorna o valor total calculado por este objeto
	 * @return
	 */
	public Float getTotal(){
		return total;
	}
	
	/**
	 * Cria um objeto CalcTotVisitor e percorre uma lista de itens atraves do mesmo, 
	 * calculando o valor total correspondente à lista de objetos Item passada como  parâmetro. 
	 * @param lista lista de objetos Item a ser visitada.
	 */
	public static Float visit(List<Item> lista){
		CalcTotVisitor ctv = new CalcTotVisitor();                    // cria o 'visitor'
		for(Item item: lista) ctv.total += (Float)item.accept(ctv);   // visita cada 'item' da lista
		return ctv.total;                                                     
	}
		

	
	
	@Override
	public Object visit(ParteEspecifica parte) {
		return parte.accept(this);
	}

	@Override
	public Object visit(Motor motor) {
		return motor.accept(this);
	}

	@Override
	public Object visit(Parafuso parafuso) {
		return parafuso.accept(this);
	}

	@Override
	public Object visit(ParteComposta parte) {
		Float v = 0.0f;
		for(Item it: parte.getItens()) v += (Float)it.accept(this);
		return new Float(v);
	}

	@Override
	public Object visit(Item item) {
		return new Float(item.calculaValor());
	}

	@Override
	public Object visit(Caracteristica caracteristica) {
		return new Float(0.0f);
	}

}
