package prodPlan;

import java.text.MessageFormat;

/**
 * Define um Motor
 * 
 * @author Sabrina Beck Angelini <157240>
 **/
public class Motor extends Parte {

	private float potencia;
	private float corrente;
	private int rpm;

	/**
	 * Cria uma instância da classe Motor
	 **/
	public Motor(int codigo, String nome, String descricao, float valor,
			float potencia, float corrente, int rpm) {
		super(codigo, nome, descricao, valor);
		this.potencia = potencia;
		this.corrente = corrente;
		this.rpm = rpm;
	}

	/**
	 * @return valor correspondente ao motor descrito pelo objeto
	 **/
	@Override
	public float calculaValor() {
		return this.valor;
	}

	/**
	 * @return representação do motor como um string
	 **/
	@Override
	public String toString() {
		return MessageFormat.format("{0} potencia:{1} corrente:{2} rpm:{3}",
				super.asString(), String.format("%.1f", this.potencia),
				String.format("%.1f", this.corrente), String.format("%d", this.rpm));
	}

	@Override
	public Object accept(ProdPlanVisitor visitor) {
		return visitor.visit(this);
	}

}
