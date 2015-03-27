package prodPlan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParteComposta extends Parte {

	private Set<Item> itens;

	public ParteComposta(int codigo, String nome, String descricao, float valor) {
		super(codigo, nome, descricao, valor);
		this.itens = new HashSet<>();
	}

	public void agregaItem(Parte parte, int quantidade)
			throws IllegalArgumentException {
		Item item = new Item(parte, quantidade);
		if (parte == null || this.itens.contains(item) || quantidade <= 0) {
			throw new IllegalArgumentException();
		}

		this.itens.add(item);
	}

	public List<Item> listaDeItens() {
		ArrayList<Item> lista = new ArrayList<Item>(this.itens);
		Collections.sort(lista);
		return lista;
	}

	@Override
	public float calculaValor() {
		float valor = this.valor;
		for (Item item : this.itens)
			valor += item.calculaValor();
		return valor;
	}

	@Override
	public String toString() {
		String str = super.asString();
		for(Item item : this.itens) {
			str += "\n.." + item.toString();
		}
		return str;
	}

	@Override
	public Object accept(ProdPlanVisitor visitor) {
		return visitor.visit(this);
	}

}
