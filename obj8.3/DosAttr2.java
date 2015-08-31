import java.nio.file.attribute.*;
import java.nio.file.*;
import java.io.*;

public class DosAttr2 {
	public static void main(String[] args) throws IOException {
		Path p = Paths.get("dosAttr2.file");
		Files.createFile(p);

		DosFileAttributeView view = Files.getFileAttributeView(p, DosFileAttributeView.class);
		printDosInfo(view.readAttributes());

		view.setReadOnly(true);
		view.setHidden(true);
		view.setSystem(true);
		view.setArchive(true);

		printDosInfo(view.readAttributes());
		view.setReadOnly(false);
		Files.delete(p);
	}

	static void printDosInfo(DosFileAttributes dfa) {
		System.out.format("%s/%s/%s/%s\n", 
			(dfa.isHidden() ? "hidden" : "not hidden"), 
			(dfa.isReadOnly() ? "readOnly":"not readOnly"),
			(dfa.isSystem() ? "system":"not system"),
			(dfa.isArchive() ? "archive":"not archive"));
	}
}