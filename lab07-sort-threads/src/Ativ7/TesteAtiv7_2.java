package Ativ7;

public class TesteAtiv7_2 {

	/* lista de nomes */
	static String[] listaDeNomes = { "Luiz", "Vinicius","Geraldo","Isabela","Fernando","Marcelo",
			         "Ricardo","Danilo","Flavio","Gustavo","Ivanildo","Jose","Leandro","Candido",
			         "Jose Carlos", "Carlos Jose","Jose Luiz","Jose Pedro","Joao Pedro","Jose Roberto",
			         "Romao","Ramon","Marcos","Maicol","Michel","Michelle","Rodrigo","Sergio","Andre",
			         "Victor","Vitor","Vitorio","Humberto","Rafael","Fabio","Fabiano","Fabiana", "Zuleide",
			         "Fabiola","Davi","Giordano","Mauricio","Thiago","Thomaz","Tomas","Alexandre",
			         "Henrique","Aline","Angelo","Bruna","Bruno","Dario","Evandro","Felipe","Izis",
			         "Tiago","Thiago","Lucas","Mateus","Vera Lucia", "Luiz", "Geraldo", "Regina","Cecilia","Rosana",
			         "Rosiane","Renato","Renata","Andreia","Maria Carolina","Maria Leticia","Jonas",
			         "Luiz Antonio","Luiz Ignacio","Luiz Carlos","Pedro Luiz", "Pedro", "Alberto",
			         "Marco Antonio","Marco Aurelio","Marco","Augusto","Luiz Augusto","Maria Cecilia",
			         "Alice","Alice Maria", "Maria Jose", "Jose Maria", "Joao Carlos","Luiz Augusto",  "Joao Luiz",
			         "Joao Marcos", "Joana","Heraldo","Johnny","Gladys","Patrick","Steve","Samuel",
			         "Willian","Guilherme","Severino", "Mario","Aurelio","Samuel", "Jose Roberto", "Adalberto",
			         "Cristina","Maria Cristina","Lorella","Marta","Marcia","Edilene","Daniel", "Zeferino",
			         "Edson","Francisco","Candida","Antonio Candido","Eulalia","Nestor", "Marcela",
			         "Ernesto","Adoniram","Edvaldo","Evair","Juliano","Ariovaldo","Osvaldo", "Zuleica",
			         "Jose Osvaldo","Renato","Jose Renato"};
	
	/**
	 * Cria um vetor de objetos Pessoa
	 * @return
	 */
	static Pessoa[] makePessoas(){
		int n = listaDeNomes.length;
		Pessoa[] res = new Pessoa[n];
		for(int i = 0; i < n; i++) res[i] = new Pessoa(listaDeNomes[i], 18 + (i*listaDeNomes[i].length()*17) % 47);
		return res;
	}
	
	/**
	 * Vetor a ser ordenado
	 */
	static Pessoa[] pessoas = makePessoas();
	
	/**
	 * Escreve a lista de objetos Pessoa na saída padrão
	 */
	static void print(){
		for(int i = 0; i < pessoas.length; i++ ) System.out.println(i+": "+ pessoas[i].idade+" "+pessoas[i].nome);
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		MultithreadSorter <Pessoa> sorter = new MultithreadSorter<Pessoa>(pessoas, new Comparator7_2());
		sorter.sort();
		print();
	}
	
}

class Comparator7_2 implements Comparator<Pessoa> {

	/**
	 * comparação 
	 * 	1 -- idade em ordem decrescente
	 *  2 -- nomes em ordem crescente
	 */
	@Override
	public boolean precede(Pessoa p1, Pessoa p2) {
		if( p1.idade > p2.idade) return true;
		if( p1.idade < p2.idade) return false;
		return (p1.nome.compareTo(p2.nome) < 0);
	}
}

class Pessoa {
	String nome;
	int idade;
	
	Pessoa(String n, int i) {
		nome = n;
		idade = i;
	}
}
