package prodPlan;

import java.util.Collections;
import java.util.List;

public class TesteLab3_1 {


	static void imprimeLista(List<Item> lista, int nivel){
		String s1 = "\t", s2 = "\t";
		for(int i = 0; i < nivel; i++) s1 += "..";
		for(int i = 3; i > nivel; i--) s2 += "  ";
		s2 += "\t";
		Collections.sort(lista);
		for(Item item:lista){
			System.out.println(s1 + item.parte.cod+"\t\t"+item.quantidade+"\t"+item.calculaValor()+ s2+s1+item.parte.nome+"/"+item.parte.descricao);
			if(item.parte instanceof ParteComposta) 
				imprimeLista(((ParteComposta)item.parte).listaDeItens(),nivel+1);
		}
	}
	
	static void imprimeTotal(List<Item> lista){
		float total = 0.0f;
		for(Item item:lista) total += item.calculaValor();
		System.out.println("Valor Total:"+total);
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		List<Item> lista = Dados.criaListaItens();
		System.out.println("\t CODIGO\t    QUANTIDADE \tVALOR  \t\t NOME/DESCRICAO");
		imprimeLista(lista,0);
		imprimeTotal(lista);
		
	}

}
