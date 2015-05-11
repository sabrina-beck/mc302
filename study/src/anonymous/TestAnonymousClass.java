package anonymous;

import java.text.MessageFormat;

public class TestAnonymousClass {

	public static abstract class Pessoa {

		protected String nome;
		protected String documento;

        public Pessoa(String nome, String documento) {
	        this.nome = nome;
	        this.documento = documento;
        }
		
		public abstract void cumprimentar();

	}

	public static void main(String[] args) {
		Pessoa pessoa = new Pessoa("Sabrina", "440.434.248-92") {

			@Override
			public void cumprimentar() {
				System.out.println("Olá!");
			}
			
			@Override
			public String toString() {
			    return MessageFormat.format("Nome: {0}\nDocumento: {1}", this.nome, this.documento);
			}

		};
		
		System.out.println(pessoa);
		pessoa.cumprimentar();
	}

}
