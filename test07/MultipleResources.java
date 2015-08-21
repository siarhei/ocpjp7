public class MultipleResources {
	class Lamb implements AutoCloseable {
		public void close() throws Exception {
			System.out.print("l");
		} 
	}

	class Goat implements AutoCloseable {
		public void close() throws Exception {
			System.out.print("g");
		} 
	}

	public static void main(String[] args) throws Exception {
		new MultipleResources().run();
	}

	public void run() throws Exception {
		try (Lamb l = new Lamb();
			//System.out.print("t"); //error: <identifier> expected
			Goat g = new Goat();) {
			System.out.print("2");
		} finally {
			System.out.print("f");
		} 
	} 
}