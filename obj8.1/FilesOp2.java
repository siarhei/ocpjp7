import java.nio.file.*;
import java.io.*;

public class FilesOp2 {
	public static void main(String[] args) {
		Path p = Paths.get("file.tmp");
		try {			
			Files.createFile(p);
		} catch (IOException e) {

		} finally {
			System.out.println("Does file exist? >> " + Files.exists(p));
			try {
				Files.deleteIfExists(p);
			} catch (IOException e) {

			} finally {
				System.out.println("Does file exist? >> " + Files.exists(p));
			}
		}
	}
}