package anonymous;


public class AnonymousClassWithFor {

	private static final int SIZE = 1000;
	
	interface HelloWorld {
		String hello();
		int index();
	}
	
	public static void main(String[] args) {
		String name = "Sabrina";
	    HelloWorld[] array = new HelloWorld[SIZE];
		
	    for (int i = 0; i < SIZE; i++) {
	    	array[i] = new HelloWorld() {
				@Override
                public String hello() {
	                return "Hello World, " + name + "!";
                }

				@Override
                public int index() {
	                return 1;
                }
	    	};
	    }
		
	    System.out.println(array[0].getClass().getName());
	    System.out.println(array[1].getClass().getName());
	    System.out.println(array[1].getClass().equals(array[0].getClass()));
	    System.out.println(array[1].equals(array[0]));
    }
	
}
