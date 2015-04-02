package prodPlan;

public class Caracteristica {

	private String nome;
	private String conteudo;

	public Caracteristica(String nome, String conteudo) {
		super();
		this.nome = nome;
		this.conteudo = conteudo;
	}

	@Override
	public String toString() {
		return "nome:" + this.nome + " conteudo:" + this.getConteudo();
	}

	public String getConteudo() {
	    return conteudo;
    }
}
