import java.nio.file.attribute.*;
import java.nio.file.*;
import java.io.*;

public class BasicAttr {
	public static void main(String[] args) throws IOException {
		Path p = Paths.get("temp.txt");
		Files.createFile(p);
		BasicFileAttributes bfa = Files.readAttributes(p, BasicFileAttributes.class);

		print("creationTime", bfa.creationTime());
		print("lastModifiedTime", bfa.lastModifiedTime());
		print("lastAccessTime", bfa.lastAccessTime());

		FileTime now = FileTime.fromMillis(System.currentTimeMillis() - 3_600_000);
		BasicFileAttributeView bfav = Files.getFileAttributeView(p, BasicFileAttributeView.class);
		bfav.setTimes(now, now, now);

		bfa = Files.readAttributes(p, BasicFileAttributes.class);
		print("creationTime", bfa.creationTime());
		print("lastModifiedTime", bfa.lastModifiedTime());
		print("lastAccessTime", bfa.lastAccessTime());

		Files.deleteIfExists(p);
	}

	static void print(String desc, FileTime ft) {
		System.out.format("%s : %s\n", desc, ft.toString());
	}
}