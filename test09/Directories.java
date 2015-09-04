import java.io.*;

public class Directories {
	public static void main(String[] args) {
		File dir = new File("dir");
		File file = new File("dir", "file");
		File file2 = new File("dir", "file2");
		try {
			dir.mkdir();
			file.createNewFile();

			System.out.println("" + file.exists() + "/" + file.isFile()); 		//true/true
			System.out.println("" + file2.exists() + "/" + file2.isFile()); 	//false/false
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			file.delete();
			dir.delete();
		}
	}
}