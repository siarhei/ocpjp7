import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import java.io.*;

public class WatchServiceTest {

private static final FileSystem FS = FileSystems.getDefault();

	public static void main(String[] args) {
		Path currentDir = Paths.get(".");
		Path other = currentDir.resolve(Paths.get("other"));
		try {
			Files.createDirectory(other);

			WatchService ws = FS.newWatchService();
			currentDir.register(ws, ENTRY_DELETE);

			while (true) {
				WatchKey key;

				try {
					key = ws.take();
				} catch (InterruptedException e) {
					System.out.println("Error on taking watchKey");
					return;					
				}

				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();
					System.out.println("kind.name: " + kind.name());
					System.out.println("kind.type: " + kind.type());

					String ctx = event.context().toString();
					System.out.println("event.context: " + ctx);
					if ("other".equals(ctx)) {
						System.out.format("Directory '%s' has been deleted", ctx);
						return;
					}
				}

				key.reset();
			}
		} catch (IOException e) {
			System.out.println("Something went wrong");
		} finally {
			try {
				Files.deleteIfExists(other);
			} catch (IOException e) {
				System.out.println("Error on deleting other folder");
			}
		}
	}
}