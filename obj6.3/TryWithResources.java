class One implements AutoCloseable {
	public void close() {
		System.out.println("Close - One");
	} 
}

class Two implements AutoCloseable {
	public void close() {
		System.out.println("Close - Two");
	} 
}

class TryWithResources {
	public static void main(String[] args) {
		try (One one = new One(); Two two = new Two()) {
			System.out.println("Try");
			throw new RuntimeException();
		} catch (Exception e) {
			System.out.println("Catch");
		} finally {
			System.out.println("Finally");
		} 
	} 
}

//Try
//Close - Two
//Close - One
//Catch
//Finally