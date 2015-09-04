import java.nio.file.*;

public class GlobTest {
	public static void main(String[] args) {
		Path p = Paths.get("c:/temp/dir/a.txt");
		Path p2 = Paths.get("c:/temp/dir/subdir/b.txt");

		match(p, "glob:**.txt");		//true
		match(p2, "glob:**.txt");		//true
		match(p, "glob:/**.txt");		//false
		match(p2, "glob:/**.txt");		//false
	}

	private static void match(Path p, String glob) {
		System.out.format("%s and pattern (%s): %b\n", p, glob, FileSystems.getDefault().getPathMatcher(glob).matches(p));
	}
}