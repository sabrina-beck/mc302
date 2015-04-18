package prodPlan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Define uma parte específica com várias características
 * 
 * @author Sabrina Beck Angelini <157240>
 **/
public class ParteEspecifica extends Parte {
	
	private Map<String, Caracteristica> caracteristicas;
	
	/**
	 * Cria uma instância da classe ParteEspecifica
	 **/
	public ParteEspecifica(int codigo, String nome, String descricao, float valor) {
		super(codigo, nome, descricao, valor);
		this.caracteristicas = new HashMap<String, Caracteristica>();
	}
	
	/**
	 * Insere uma nova característica nessa parte específica
	 **/
	public void agregaCaracteristica(String nome, String conteudo) throws IllegalArgumentException {
		if (nome == null || conteudo == null || this.caracteristicas.containsKey(nome)) {
			throw new IllegalArgumentException();
		}
		
		this.caracteristicas.put(nome, new Caracteristica(nome, conteudo));
	}
	
	/**
	 * @return a característica cujo nome é especificado, null caso não seja uma
	 *         caracteristica da parte específica
	 **/
	public String caracteristica(String nome) {
		if (!this.caracteristicas.containsKey(nome))
			return null;
		return this.caracteristicas.get(nome).getConteudo();
	}
	
	/**
	 * @return lista de características dessa parte específica
	 **/
	public List<Caracteristica> listaDeCaracteristicas() {
		return (List<Caracteristica>) this.caracteristicas.values();
	}
	
	/**
	 * @return valor correspondente a parte específica descrita pelo objeto
	 **/
	@Override
	public float calculaValor() {
		return this.getValor();
	}
	
	/**
	 * @return representação da parte específica como um string
	 **/
	@Override
	public String toString() {
		String str = super.asString();
		for (Caracteristica caracteristica : this.caracteristicas.values()) {
			str += "\n.." + caracteristica;
		}
		return str;
	}
	
}
