import java.io.*;
import static java.lang.System.out;

public class FilesAndDirectories {
	private static final String CRT_CMD = "create";
	private static final String REM_CMD = "remove";

	private static final String PREFIX = "fd";
	private static final String DIR_NAME_FMT = PREFIX + "_{level}_dir";
	private static final String FILE_NAME_FMT = PREFIX + "_{level}_{idx}_file";
	
	private static final int NESTING = 5;

	public static void main(String[] args) {
		assert args.length == 1 : "At least 1 argument must be defined";

		switch(args[0]) {
			case CRT_CMD : createStructure();
							break;
			case REM_CMD : removeStructure();
							break;
			default:
				out.printf("Enter one of two commands: %2$s or %1$s", CRT_CMD, REM_CMD);
		}
	}

	private static void createStructure() {
		out.println("Creating structure...");
		create(0, null);
	}

	/**
	* creates new dir with nested files on the defined level
	*/
	private static void create(int level, File parentDir) {
		if (level < NESTING) {
			out.format("Creating dir on %-3d\n", level);
			File dir;
			if (parentDir == null) {
				dir = new File(dirName(level));
			} else {
				dir = new File(parentDir, dirName(level));
			}
			dir.mkdir();

			for (int i = 0; i < NESTING - level; i++) {
				try {
					out.format("Creating %d file on %-3d level\n", i, level);
					new File(dir, String.format("%s_%d_%d_file", PREFIX, level, i)).createNewFile();
				} catch (IOException e) {
					out.println("I/O error");
				}
			}

			create(++level, dir);
		}
	}

	private static String dirName(int level) {
		return DIR_NAME_FMT.replace("{level}", "" + level);
	}

	private static String fileName(int level, int idx) {
		return FILE_NAME_FMT.replace("{level}", "" + level).replace("{idx}", "" + idx);
	}

	private static void removeStructure() {
		out.println("Removing structure...");

		remove(null);
	}

	private static void remove(File parentDir) {
		File dir = null;
		if (parentDir == null) {
			dir = new File(dirName(0));
		} else {
			dir = parentDir;
		}

		String[] names = dir.list();
		for (String fname : names) {
			File file = new File(dir, fname);
			if (file.isDirectory()) {
				remove(file);

				out.format("Removing directory: %s\n", file.getName());
			} else {
				out.format("Removing file: %s\n", file.getName());	
			}
			
			file.delete();
		}

		dir.delete();
	}
}