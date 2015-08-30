import java.nio.file.*;

public class PathNorm {
	public static void main(String[] args) {
		print(Paths.get("w:\\a\\..\\..\\..\\b"));	//w:\b
		print(Paths.get("x:\\.\\.\\..\\a"));		//x:\a
		print(Paths.get("x:\\a\\b\\.\\c\\.."));		//x:\a\b
		print(Paths.get("\\a\\..\\c\\.\\d"));		//c\d
	}

	static void print(Path p) {
		System.out.println("original: " + p);
		System.out.println("p.normalize(): " + p.normalize());		
	}
}