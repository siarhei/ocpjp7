import java.nio.file.*;

public class PathRes {
	public static void main(String[] args) {
		print(Paths.get("x:\\a\\b\\c"), Paths.get("d\\e\\f"));			//x:\a\b\c\d\e\f
		print(Paths.get("d\\e\\f"), Paths.get("x:\\a\\b\\c"));			//x:\a\b\c
		print(Paths.get("d\\..\\f"), Paths.get("b\\c"));				//d\..\f\b\c
		print(Paths.get("d\\..\\f\\.\\e"), Paths.get("f\\e\\g"));		//d\..\f\.\e\f\e\g
		print(Paths.get("x:\\d\\f\\e"), Paths.get("x:\\d\\f\\e\\g"));	//x:\d\f\e\g
		print(Paths.get("x:\\d\\f\\e"), Paths.get("y:\\d\\f\\e\\g"));	//y:\d\f\e\g
	}

	/**
	* t - this path; g - given path
	*/
	static void print(Path t, Path g) {
		System.out.format("\nthis path: %1$s (%4$s);\ngiven path: %2$s (%5$s);\nresolved path: %3$s", t.toString(), g.toString(), t.resolve(g).toString(), isAbs(t), isAbs(g));
	}

	private static String isAbs(Path p) {
		return p.isAbsolute() ? "absolute" : "not absolute";
	}
}