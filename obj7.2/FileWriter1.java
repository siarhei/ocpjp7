import java.io.*;
import static java.lang.System.out;

public class FileWriter1 {
	private static final String[] FILES = {"FileWriter1.txt", "FileWriter2.txt", "FileWriter3.txt"};

	public static void main(String[] args) {
		testFileWriter();
		testPrintWriter1();
		testBufferedWriter();
	}

	private static void testFileWriter() {
		out.println("---Testing FileWriter");

		File file = new File(FILES[0]);
		out.println(file.exists() ? "file exists" : "file doesn't exist");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write("testing FileWriter");			
		} catch (IOException e) {
			out.println(e);
		} finally {
			if (fw != null) {
				try {
					fw.flush();
					fw.close();
				} catch (IOException e) {
					out.println("Caught IO error");
				} finally {
					out.println("file has been closed successfully");
				}
			}
		}
	}

	private static void testPrintWriter1() {
		out.println("---Testing PrintWriter");

		File file = new File(FILES[1]);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
			pw.format("testing format %1$s. It works %2$d%%\n", "method", 100);
			pw.println("testing println()");
			pw.print("testing print()");
		} catch (FileNotFoundException fnfe ) {

		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}
	}

	private static void testBufferedWriter() {
		out.println("---Testing BufferedWriter");

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(
				new FileWriter(
					new File(FILES[2])));

			bw.write("first string");
			bw.newLine();
			bw.write("second string");
		} catch (IOException e) {

		} finally {
			if (bw != null) {
				try {
					bw.flush();
					bw.close();
				} catch (IOException e) {

				} finally {
					out.println("Closing done");
				}
			}
		}
	}
}