package parameters;

public class Test {

	static void upperCase(String s) {
		s = s.toUpperCase();
	}

	static void upperCase(char[] v) {
		for (int i = 0; i < v.length; i++)
			v[i] = Character.toUpperCase(v[i]);
	}

	public static void main(String[] args) {
		String nome1 = "joaquim";
		char[] nome2 = nome1.toCharArray();
		upperCase(nome1);
		upperCase(nome2);
		System.out.println("NOME1:" + nome1);
		System.out.println("NOME2:" + new String(nome2));
	}
}
