import java.io.*;
import static java.lang.System.out;

public class Writer2 {
	public static void main(String[] args) {
		File file = new File ("_Writer2File.txt");
		out.println(file.exists());

		try {
			FileWriter fw = new FileWriter(file);
			fw.write("Hello,\nWorld!");
			fw.flush();
			fw.close();

			FileReader fr = new FileReader(file);
			char[] in = new char[20];
			int size = 0;
			size = fr.read(in);
			out.printf("%1$d symbols has been read\n", size);
			for (char c : in) {
				out.println(c);
			}
			fr.close();
		} catch (IOException e) {
			out.println("IOException occurred");
		}
	}
}