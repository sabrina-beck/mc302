package prodPlan;

import java.text.MessageFormat;

/**
 * Associa uma Parte a uma quantidade. Usada em várias situações como por
 * exemplo para definir um lote de produção ou para representar o estoque
 * disponível
 * 
 * @author Sabrina Beck Angelini <157240>
 **/
public class Item {

	protected Parte parte;
	private int quantidade;

	/**
	 * Cria uma instância da classe Item
	 * 
	 * @throws IllegalArgumentException
	 *             caso a parte passada seja nula
	 **/
	public Item(Parte parte, int quantidade) {
		if (parte == null)
			throw new IllegalArgumentException();
		this.parte = parte;
		this.quantidade = quantidade;
	}

	/**
	 * @return valor correspondente ao item dado, considerando a quantidade e o
	 *         valor unitário da parte
	 **/
	public float calculaValor() {
		return this.quantidade * parte.valor;
	}

	/**
	 * @return representação do item como um string
	 **/
	@Override
	public String toString() {
		return MessageFormat.format(
				"cod:{0} nome:{1} quantidade:{2} valor unitario:{3} valor:{4}",
				String.format("%d", this.parte.codigo), this.parte.nome,
				String.format("%d", this.quantidade),
				String.format("%.1f", this.parte.valor),
				String.format("%.1f", this.calculaValor()));
	}
}
