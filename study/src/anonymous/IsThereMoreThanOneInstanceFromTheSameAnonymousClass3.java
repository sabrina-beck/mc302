package anonymous;

public class IsThereMoreThanOneInstanceFromTheSameAnonymousClass3 {

	private static final int SIZE = 1000;

	interface HelloWorld {

		String hello();

		int index();
	}

	public static void main(String[] args) {
		String name = "Sabrina";
		HelloWorld[] array = new HelloWorld[SIZE];

		array[0] = new HelloWorld() {

			@Override
			public String hello() {
				return "Hello World, " + name + "!";
			}

			@Override
			public int index() {
				return 1;
			}
		};

		array[1] = new HelloWorld() {

			@Override
			public String hello() {
				return name;
			}

			@Override
			public int index() {
				return 4;
			}
		};

		System.out.println(array[0].getClass().getName());
		System.out.println(array[1].getClass().getName());
		System.out.println(array[1].getClass().equals(array[0].getClass()));
		System.out.println(array[1].equals(array[0]));
	}
}
