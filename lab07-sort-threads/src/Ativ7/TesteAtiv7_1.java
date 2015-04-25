package Ativ7;

public class TesteAtiv7_1 {

	/* lista de nomes a ser ordenada */
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
	 * Escreve a lista de nomes na saída padrão
	 */
	static void print(){
		for(int i = 0; i < listaDeNomes.length; i++ ) System.out.println(i+": "+ listaDeNomes[i]);
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		MultithreadSorter <String> sorter = new MultithreadSorter<String>(listaDeNomes, new Comparator7());
		sorter.sort();
		print();
	}
	
}

class Comparator7 implements Comparator<String> {

	@Override
	public boolean precede(String n1, String n2) {
		//System.out.println("precede("+n1+","+n2+"):"+n1.compareTo(n2));
		return n1.compareTo(n2) < 0;
	}
	
}
