package prodPlan;

import java.util.Collections;
import java.util.List;

public class Lab3Visitor_1 extends ProdPlanVisitor {
	
	/**
	 * Percorre uma lista de itens atraves de um 'visitor' que escreve um texto
	 * formatado da mesma
	 * @param lista lista de objetos Item a ser visitada.
	 */
	public static void visit(List<Item> lista){
		Lab3Visitor_1 l3v1 = new Lab3Visitor_1();                                      // cria o 'visitor'
		System.out.println("\t CODIGO\t    QUANTIDADE \tVALOR  \t\t NOME/DESCRICAO");  // escreve o cabecalho
		Collections.sort(lista);                                                       // ordena a lista
		for(Item item: lista) item.accept(l3v1);                                       // visita cada 'item' da lista
		imprimeTotal(lista);                                                           // escreve linha final com o total
	}
		
	int nivel;      // nivel de encaixamento do item ou parte
	String s1,s2;   // string usado para deslocar os campos do texto conforme o nivel de encaixamento
	
	/**
	 * Construtor (uso local)
	 */
	private Lab3Visitor_1(){
		nivel = 0;
	}
	
	/**
	 * escreve linha final com o total
	 * @param lista
	 */
	private static void imprimeTotal(List<Item> lista){
		float total = 0.0f;
		for(Item item:lista) total += item.calculaValor();
		System.out.println("Valor Total:"+total);
	}
	


	@Override
	public Object visit(ParteEspecifica parte) {
		return parteStr(parte);
	}

	@Override
	public Object visit(Motor motor) {
		return parteStr(motor);
	}

	@Override
	public Object visit(Parafuso parafuso) {
		return parteStr(parafuso);
	}

	@Override
	public Object visit(ParteComposta parte) {
		parteStr(parte);
			String ss1 = s1, ss2 = s2;
			nivel++;
			List<Item> listaDeItens = ((ParteComposta) parte).listaDeItens();
			Collections.sort(listaDeItens);
			for(Item it: listaDeItens) it.accept(this);
			nivel--;
			s1 = ss1; s2 = ss2;
		return null; 
	}
	
	/**
	 * Medoto auxiliar: escreve nome e descricao de um objeto Parte
	 * @param parte
	 * @return null (sempre !)
	 */
	private Object parteStr(Parte parte){
		System.out.println(s2+s1+parte.nome+"/" + parte.descricao);
		return null;
	}

	@Override
	public Object visit(Item item) {
		s1 = "\t"; s2 = "\t";
		for(int i = 0; i < nivel; i++) s1 += "..";
		for(int i = 3; i > nivel; i--) s2 += "  ";
		System.out.print(s1 + item.parte.cod+"\t\t"+item.quantidade+"\t"+item.calculaValor());
		item.parte.accept(this);
		return null;
	}

}
