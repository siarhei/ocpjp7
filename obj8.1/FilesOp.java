import java.nio.file.*;
import java.io.*;

public class FilesOp {
	public static void main(String[] args) {
		Path p = Paths.get("FilesOp.java");
		Path target = Paths.get("FilesOp2.java");

		try {
			checkExistence(p, target);
			System.out.println("copy p->target");
			Files.copy(p, target);
			checkExistence(p, target);

			System.out.println("delete p");
			Files.delete(p);
			checkExistence(p, target);

			System.out.println("move target -> p");
			Files.move(target, p);
			checkExistence(p, target);
		} catch (IOException e) {
			System.out.println("I/O error");
		}
	}

	private static void checkExistence(Path... ps) {
		for (Path p : ps) {
			System.out.println(p + " " + (Files.exists(p) ? "exists" : "doesn't exist"));
		}
	}
}