package lab06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Processa o arquivo alunos.csv informando as relações de homônimos, documentos
 * repetidos e de alunos por curso.
 * 
 * @author Sabrina Beck Angelini <157240>
 **/
public class Lab06 {

	private static final String FILE_NAME = "alunos.csv";

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(FILE_NAME))));

			/** Mapa que mantém relação nome e ocorrências **/
			Map<String, Integer> homonimos = new HashMap<String, Integer>();
			/** Mapa que mantém relação documento e ocorrências **/
			Map<String, Integer> documentos = new HashMap<String, Integer>();
			/** Mapa que mantém relação curso e quantidade de alunos **/
			Map<String, Integer> relacaoCursos = new HashMap<String, Integer>();

			/** Leitura do cabeçalho **/
			reader.readLine();

			/**
			 * Lê linha a linha do arquivo alunos.csv mantendo os dados
			 * necessários nos mapas corretos
			 **/
			while (reader.ready()) {
				String[] aluno = reader.readLine().split(";");

				if (!homonimos.containsKey(aluno[0]))
					homonimos.put(aluno[0], 0);
				if (!documentos.containsKey(aluno[1]))
					documentos.put(aluno[1], 0);
				if (!relacaoCursos.containsKey(aluno[3]))
					relacaoCursos.put(aluno[3], 0);

				homonimos.put(aluno[0], homonimos.get(aluno[0]) + 1);
				documentos.put(aluno[1], documentos.get(aluno[1]) + 1);
				relacaoCursos.put(aluno[3], relacaoCursos.get(aluno[3]) + 1);
			}

			/** Escreve os resultados **/
			escreveResultado(homonimos, "Homonimos:", false);
			escreveResultado(documentos, "Documentos repetidos:", false);
			escreveResultado(relacaoCursos, "Cursos:", true);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo " + FILE_NAME + " não encontrado!");
		} catch (IOException e) {
			System.err.println("Erro na leitura do arquivo " + FILE_NAME);
		}
	}

	/**
	 * Escreve os dados repetidos no console com a quantidade total de dados
	 * repetidos.
	 **/
	private static void escreveResultado(Map<String, Integer> map, String label, boolean imprimirTudo) {
		String listaProcessada = "";
		int quantidadeProcessada = 0;

		for (String key : map.keySet()) {
			Integer quantidadeEncontrada = map.get(key);
			if (imprimirTudo || quantidadeEncontrada >= 2) {
				listaProcessada += key + "  " + quantidadeEncontrada + "\n";
				quantidadeProcessada++;
			}
		}

		System.out.println(label + " " + quantidadeProcessada);
		System.out.println(listaProcessada);
	}

}
