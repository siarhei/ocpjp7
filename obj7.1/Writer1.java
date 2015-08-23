import java.io.*;
import static java.lang.System.out;

public class Writer1 {
	public static void main(String[] args) {
		File file = new File ("_Writer1File.txt");
		out.println(file.exists());
		try {
			out.println(file.createNewFile() ? "Created new file" : "File already exists");
		} catch (IOException e) {
			out.println("Caught IO exception");
		}
	}
}