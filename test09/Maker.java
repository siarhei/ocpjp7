import java.io.*;

public class Maker {
	public static void main(String[] args) {
		File dir = new File("dir3");
		//File file = new File("dir3", "file3");		//OK
		File file = new File(dir, "file3"); 		//OK
		try {
			dir.mkdir();
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//file.delete();
			//dir.delete();
		}
	}
}