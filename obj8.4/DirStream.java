import java.nio.file.*;
import java.io.*;

public class DirStream {
	public static void main(String[] args) throws IOException {
		Path p = Paths.get("./..");
		p = p.normalize();
		System.out.println("original directory: " + p.toString());

		try (DirectoryStream<Path> s = Files.newDirectoryStream(p, "obj*")) {
			for (Path pt : s) {
				System.out.println(pt);
			}
		}
	}
}