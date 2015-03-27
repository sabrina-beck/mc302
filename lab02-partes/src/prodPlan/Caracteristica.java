package prodPlan;

public class Caracteristica {

	protected String nome;
	protected String conteudo;

	public Caracteristica(String nome, String conteudo) {
		super();
		this.nome = nome;
		this.conteudo = conteudo;
	}

	@Override
	public String toString() {
		return "nome:" + this.nome + " conteudo:" + this.conteudo;
	}
}
