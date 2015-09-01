import java.nio.file.attribute.*;
import java.nio.file.*;
import java.io.*;

public class WalkFileTree extends SimpleFileVisitor<Path> {
	public static void main(String[] args) throws IOException {
		Path p = Paths.get("./..");
		Files.walkFileTree(p, new WalkFileTree());
	}

	@Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if (dir.getFileName().endsWith(".git")) {
        	System.out.println("skipping .git subTree");
        	return FileVisitResult.SKIP_SUBTREE;
        }
        return FileVisitResult.CONTINUE;
    }

	@Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    	System.out.println(file);
        return FileVisitResult.CONTINUE;
    }
}