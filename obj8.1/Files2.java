import java.nio.file.*;
import java.io.*;

public class Files2 {
	public static void main(String[] args) {
		Path p1 = Paths.get("file1.tmp");
		Path p2 = Paths.get("src/main/java");
		checkExistence(p1, p2);
		try {
			Files.createFile(p1);

			Files.createDirectories(p2);
		} catch (IOException e) {

		}
		checkExistence(p1, p2);
	}

	private static void checkExistence(Path... ps) {
		for (Path p : ps) {
			System.out.println(p + " " + (Files.exists(p) ? "exists" : "doesn't exist"));
		}
	}
}