package prodPlan;


import java.text.MessageFormat;

/**
 * Define um objeto Parafuso
 * 
 * @author Sabrina Beck Angelini <157240>
 **/
public class Parafuso extends Parte {

	private float comprimento;
	private float diametro;

	/**
	 * Cria uma instância da classe Parafuso
	 **/
	public Parafuso(int codigo, String nome, String descricao, float valor,
			float comprimento, float diametro) {
		super(codigo, nome, descricao, valor);
		this.comprimento = comprimento;
		this.diametro = diametro;
	}

	/**
	 * @return valor correspondente ao parafuso descrito pelo objeto
	 **/
	@Override
	public float calculaValor() {
		return this.getValor();
	}

	/**
	 * @return representação do parafuso como um string
	 **/
	@Override
	public String toString() {
		return MessageFormat.format("{0} comprimento:{1} diametro:{2}",
				super.asString(), String.format("%.1f", this.comprimento),
				String.format("%.1f", this.diametro));
	}

}
