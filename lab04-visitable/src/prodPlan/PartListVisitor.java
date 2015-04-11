package prodPlan;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Visitor que contém os métodos que, para cada parte, calcula a quantidade da
 * mesma presente em uma lista de itens.
 * 
 * O problema aqui é que estou presa à classe abstrata ProdPlanVisitor, e não é
 * possível calcular a quantidade de uma parte dentro de seu método de visita,
 * para resolver isso tive que concentrar a lógica na visita do Item que é a
 * classe que tem a informação de quantidade desejada, assim os métodos visit de
 * cada parte simplesmente adicionam o código da parte no mapa global.
 * 
 * @author Sabrina Beck Angelini <157240>
 **/
public class PartListVisitor extends ProdPlanVisitor {
	
	/**
	 * Mapa que será alterado por cada método de visita com as quantidades de
	 * cada parte, ou seja, sua chave será o código da parte e seu valor, a
	 * quantidade
	 **/
	private Map<Integer, Integer> mapa = new LinkedHashMap<Integer, Integer>();
	
	public String visit(List<Item> itens) {
		/**
		 * A lógica utilizada altera a lista dada, como uma boa prática, é
		 * melhor não alterar a lista original, por isso o processamento utiliza
		 * uma cópia
		 **/
		List<Item> copyItens = new ArrayList<Item>(itens);
		
		for (Item item : copyItens)
			item.accept(this);
		
		String listagem = "";
		for (int codigo : mapa.keySet()) {
			listagem += MessageFormat.format("cod:{0} quant:{1}\n", codigo, this.mapa.get(codigo));
		}
		
		return listagem;
	}
	
	@Override
	public Object visit(ParteEspecifica parte) {
		insereNoMapa(parte);
		return null;
	}
	
	@Override
	public Object visit(Motor motor) {
		insereNoMapa(motor);
		return null;
	}
	
	@Override
	public Object visit(Parafuso parafuso) {
		insereNoMapa(parafuso);
		return null;
	}
	
	@Override
	public Object visit(ParteComposta parte) {
		for (Item item : parte.getItens())
			item.accept(this);
		return null;
	}
	
	@Override
	public Object visit(Item item) {
		Parte parte = item.getParte();
		int codigo = parte.getCod();
		
		if (parte instanceof ParteComposta) {
			for (Item it : ((ParteComposta) parte).getItens())
				it.setQuantidade(it.getQuantidade() * item.getQuantidade());
		}
		
		parte.accept(this);
		
		if (this.mapa.containsKey(codigo)) {
			int quantidade = this.mapa.get(codigo);
			quantidade += item.getQuantidade();
			this.mapa.put(codigo, quantidade);
		}
		
		return null;
	}
	
	@Override
	public Object visit(Caracteristica caracteristica) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void insereNoMapa(Parte parte) {
		int codigo = parte.getCod();
		if (!this.mapa.containsKey(codigo))
			this.mapa.put(codigo, 0);
	}
}
