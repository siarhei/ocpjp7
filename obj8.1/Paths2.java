import java.nio.file.*;
import java.net.URI;
import java.io.File;

public class Paths2 {
	public static void main(String[] args) {
		print(Paths.get("F:\\dev")); //F:\dev
		print(Paths.get("F:\\dev", "projects\\tmp")); //F:\dev\projects\tmp
		print(Paths.get(URI.create("file:///F:/dev/projects/H.java"))); //F:\dev\projects\H.java

		Path p = Paths.get("F:\\dev");
		File f = p.toFile();
		System.out.println(f + "\t" + (f.exists() ? "exists":"doesn't exist"));
	}

	private static void print(Path p) {
		System.out.println(p);
	}
}