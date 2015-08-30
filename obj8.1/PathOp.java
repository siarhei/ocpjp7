import java.nio.file.*;
import static java.lang.System.out;

public class PathOp {
	private static final String PATH = "F:\\dev\\projects\\certification\\ocpjp7";

	public static void main(String[] args) {
		Path p = Paths.get(PATH);

		out.println("p.toString(): " + p.toString());			//F:\dev\projects\certification\ocpjp7
		out.println("p.getFileName(): " + p.getFileName());		//ocpjp7
		out.println("p.getParent(): " + p.getParent());			//F:\dev\projects\certification
		out.println("p.getRoot(): " + p.getRoot());				//F:\
		out.println("p.getNameCount(): " + p.getNameCount());	//4
		out.println("p.getName(2): " + p.getName(2));			//certification
		out.println("p.subpath(0, 4): " + p.subpath(0, 4));		//dev\projects\certification\ocpjp7
		out.println("p.subpath(1, 5): " + p.subpath(1, 5));		//IllegalArgumentException
	}
}