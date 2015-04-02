package prodPlan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParteEspecifica extends Parte {

	private Map<String, Caracteristica> caracteristicas;

	public ParteEspecifica(int codigo, String nome, String descricao,
			float valor) {
		super(codigo, nome, descricao, valor);
		this.caracteristicas = new HashMap<>();
	}

	public void agregaCaracteristica(String nome, String conteudo) throws IllegalArgumentException {
		if(nome == null || conteudo == null || this.caracteristicas.containsKey(nome)) {
			throw new IllegalArgumentException();
		}
		
		this.caracteristicas.put(nome, new Caracteristica(nome, conteudo));
	}
	
	public String caracteristica(String nome) {
		if(!this.caracteristicas.containsKey(nome))
			return null;
		return this.caracteristicas.get(nome).getConteudo();
	}

	public List<Caracteristica> listaDeCaracteristicas() {
		return (List<Caracteristica>) this.caracteristicas.values();
	}

	@Override
	public Object accept(ProdPlanVisitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public float calculaValor() {
		return this.getValor();
	}

	@Override
	public String toString() {
		String str = super.asString();
		for(Caracteristica caracteristica : this.caracteristicas.values()) {
			str += "\n.." + caracteristica;
		}
		return str;
	}

}
