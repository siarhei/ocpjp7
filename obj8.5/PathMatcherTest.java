import java.nio.file.*;

public class PathMatcherTest {
	public static void main(String[] args) {
		match(Paths.get("./../pwd/Date.class"), "**/*.class");				//matches
		match(Paths.get("./../pwd/Date.class"), "*.class");					//not matches
		match(Paths.get("./../pwd/Date.class"), "**.class");				//matches
		match(Paths.get("./../pwd/Date.class"), "**/[dD]*.class");			//matches
		match(Paths.get("./../pwd/Date.class"), "**/Dat?.class");			//matches
		match(Paths.get("./../pwd/Date.class"), "**/Dat?.{class, java}");	//matches
		match(Paths.get("Date.class"), "*.class");							//matches
		match(Paths.get("./Date.class"), "*.class");						//not matches
		match(Paths.get("./Date.class").normalize(), "*.class");			//matches
	}

	private static void match(Path p, String glob) {
		System.out.format("path: %s\nglob: %s\n", p, glob);

		PathMatcher pm = FileSystems.getDefault().getPathMatcher(String.format("glob:%s", glob));
		System.out.println(pm.matches(p) ? "matches" : "not matches");
	}
}