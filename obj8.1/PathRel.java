import java.nio.file.*;

public class PathRel {
	public static void main(String[] args) {
		print(Paths.get("x:\\a\\b"), Paths.get("x:\\a\\b\\c")); 	//c
		print(Paths.get("x:\\a\\b"), Paths.get("x:\\a\\b")); 		//
		print(Paths.get("\\a\\b"), Paths.get("\\c\\d")); 			//..\..\c\d

		//Only one Path has a Root component. In this case IllegalArgumentException is occurred
		//print(Paths.get("x:\\a\\b"), Paths.get("y:\\c\\d")); 		//java.lang.IllegalArgumentException: 'other' has different root
		//print(Paths.get("x:\\a\\b"), Paths.get("\\a\\b\\c\\d")); 	//java.lang.IllegalArgumentException: 'other' is different type of Path
	}

	/**
	* t - this path; g - given path
	*/
	static void print(Path t, Path g) {
		System.out.format("\nthis path: %1$s (%4$s);\ngiven path: %2$s (%5$s);\nrelative path: %3$s", t.toString(), g.toString(), t.relativize(g).toString(), isAbs(t), isAbs(g));
	}

	private static String isAbs(Path p) {
		return p.isAbsolute() ? "absolute" : "relative";
	}
}