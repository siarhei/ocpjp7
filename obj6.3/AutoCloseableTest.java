import java.io.*;

public class AutoCloseableTest {

	public static void main(String[] args) {
		new AutoCloseableTest().readResource();
	}

	void readFile() {
		try (Reader reader = new BufferedReader(new FileReader(new File("file")))) {
			//read from file
		} catch (IOException e) {}
	}

	void readResource() {
		try (Resource res = new Resource()) {
			System.out.println("read from resource");
		} catch (Exception e) {

		} finally {
			System.out.println("finally");
		}

	}
}

class Resource implements java.lang.AutoCloseable {
	public void close() {
		System.out.println("close resource");
	}
}