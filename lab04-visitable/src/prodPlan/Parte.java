package prodPlan;

import java.text.MessageFormat;

/**
 * Define os elementos comuns aos objetos Parte utilizados na produção
 * 
 * @author Sabrina Beck Angelini <157240>
 **/
public abstract class Parte implements Visitable {

	private int cod;
	private String nome;
	private String descricao;
	private float valor;

	public Parte(int codigo, String nome, String descricao, float valor) {
		this.cod = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
	}

	/**
	 * @return valor correspondente à parte descrita pelo objeto
	 **/
	public abstract float calculaValor();

	/**
	 * @return representação da parte como um string
	 **/
	@Override
	public abstract String toString();

	/**
	 * Auxiliar para evitar duplicação do código de representação dos dados
	 * básicos de uma parte nas classes filhas
	 **/
	protected String asString() {
		return MessageFormat.format(
				"codigo:{0} nome:{1} descricao:{2} valor:{3}",
				String.format("%d", this.getCod()), this.getNome(), this.descricao,
				String.format("%.1f", this.getValor()));
	}

	public int getCod() {
	    return cod;
    }

	public String getNome() {
	    return nome;
    }

	public float getValor() {
	    return valor;
    }

}
