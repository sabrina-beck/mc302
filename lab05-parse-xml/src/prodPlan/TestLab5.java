package prodPlan;

import java.util.Collections;
import java.util.List;

public class TestLab5 {

	static void imprimeLista(List<Item> lista, int nivel){
		String s1 = "\t", s2 = "\t";
		for(int i = 0; i < nivel; i++) s1 += "..";
		for(int i = 3; i > nivel; i--) s2 += "  ";
		s2 += "\t";
		Collections.sort(lista);
		for(Item item:lista){
			System.out.println(s1 + item.getParte().getCod()+"\t\t"+item.getQuantidade()+"\t"+item.calculaValor()+ s2+s1+item.getParte().getNome()+"/"+item.getParte().getDescricao());
			if(item.getParte() instanceof ParteComposta) 
				imprimeLista(((ParteComposta)item.getParte()).listaDeItens(),nivel+1);
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
	public static void main(String[] args) throws Exception {
		
		Lab5Builder lb5 = new Lab5Builder();
		List<Item> lista = lb5.ItemsFromXml("entradaLab5.xml");

		System.out.println("\t CODIGO\t    QUANTIDADE \tVALOR  \t\t NOME/DESCRICAO");
		imprimeLista(lista,0);
		imprimeTotal(lista);
		
	}

}
