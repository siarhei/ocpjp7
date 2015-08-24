import java.io.*;

public class BufferedReader1 {
	public static void main(String[] args) {
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			br = new BufferedReader(
				new FileReader(
					new File("FileWriter3.txt")));

			pw = new PrintWriter(System.out);
			pw.format("The first read string is: %1$s\n", br.readLine());
			pw.printf("%1$015d#\n", 7);
			pw.format("and the 2nd line is: %1$s", br.readLine());
		} catch (IOException e) {
			System.out.println("Something went wrong");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("I/O error");
				} finally {
					System.out.println("Reader has been successfully closed");
				}
			}
			if (pw != null) {
				pw.close();
			}
		}
	}
}