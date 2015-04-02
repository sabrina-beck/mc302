package prodPlan;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartListVisitor extends ProdPlanVisitor {
	
	private Map<Integer, Integer> mapa = new HashMap<Integer, Integer>();
	
	public String visit(List<Item> itens) {
		for (Item item : itens)
			item.getParte().accept(this);
		
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
		item.getParte().accept(this);
		int codigo = item.getParte().getCod();
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
