import java.nio.file.attribute.*;
import java.nio.file.*;
import java.io.*;

public class DosAttr {
	public static void main(String[] args) throws IOException {
		Path p = Paths.get("dosAttr.file");
		Files.createFile(p);

		printDosInfo(Files.readAttributes(p, DosFileAttributes.class));

		Files.setAttribute(p, "dos:hidden", true);
		Files.setAttribute(p, "dos:readonly", true);
		printDosInfo(Files.readAttributes(p, DosFileAttributes.class));

		//make file writable
		Files.setAttribute(p, "dos:readonly", false); //otherwise java.nio.file.AccessDeniedException
		Files.delete(p);
	}

	static void printDosInfo(DosFileAttributes dfa) {
		System.out.format("%s/%s\n", (dfa.isHidden() ? "hidden" : "not hidden"), (dfa.isReadOnly() ? "readOnly":"not readOnly"));
	}
}