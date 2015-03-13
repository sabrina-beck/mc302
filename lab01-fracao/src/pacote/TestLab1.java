package pacote;

public class TestLab1 {
	public static void main(String[] args) throws Exception {
		Fracao f1 = new Fracao(1, 2);
		Fracao f2 = new Fracao(1, 3);
		Fracao f3 = f1.add(f2);
		Fracao f4 = f1.sub(f2);
		Fracao f5 = f1.mult(f2);
		Fracao f6 = f1.div(f2);
		Fracao f7 = new Fracao(15, 9);
		System.out.println("f1: " + f1.toString());
		System.out.println("f2: " + f2.toString());
		System.out.println("f3: " + f3.toString());
		System.out.println("f4: " + f4.toString());
		System.out.println("f5: " + f5.toString());
		System.out.println("f6: " + f6.toString());
		System.out.println("f7: " + f7.toString());
		System.out.println("float(f3): " + f3.toFloat());
		System.out.println("f2 compareTo f3: " + f2.compareTo(f3));
		System.out.println("f2 compareTo f2: " + f2.compareTo(f2));
		System.out.println("f6 compareTo f1: " + f6.compareTo(f1));
	}
}
