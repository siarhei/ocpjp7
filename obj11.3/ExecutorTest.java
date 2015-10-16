import java.util.concurrent.*;
import java.util.*;

public class ExecutorTest {
	private static final String SAME_THREAD = "sameThread";
	private static final String CACHED_POOL = "cachedPool";
	private static final String FIXED_POOL = "fixedPool";
	private static final String SINGLE_THREAD = "singleThread";

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.format("use one of the next options:%n%s%n%s%n%s%n%s%n", SAME_THREAD, CACHED_POOL, FIXED_POOL, SINGLE_THREAD);
			return;
		}

		int processors = Runtime.getRuntime().availableProcessors();
		System.out.println("available processors: " + processors);

		Runnable[] tasks = new Runnable[processors*2];
		for (int i = 0; i < tasks.length; i++) {
			tasks[i] = new SimpleTask();
		}

		Executor executor = getExecutor(args[0]);
		for (Runnable task : tasks) {
			executor.execute(task);
		}
		if (executor instanceof ExecutorService) {
			ExecutorService.class.cast(executor).shutdown();
		}
	}

	private static Executor getExecutor(String execType) {
		Executor executor = null;
		switch(execType) {
			case SAME_THREAD:
				executor = new SameThreadExecutor();
				break;
			case CACHED_POOL:
				executor = Executors.newCachedThreadPool();
				break;
			case FIXED_POOL:
				executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);
				break;
			case SINGLE_THREAD:
				executor = Executors.newSingleThreadExecutor();
				break;
			default:
				throw new IllegalArgumentException("cannot define executor");
		}
		System.out.format("Executor of type (%s) will be using%n", executor.getClass().getCanonicalName());
		return executor;
	}

	private static class SimpleTask implements Runnable {
		private static int counter;
		private final int id = ++counter;
		private final Random r = new Random(53);

		@Override
		public void run() {
			try {
				System.out.format("executing task #%d by thread %s%n", id, Thread.currentThread().getName());
				Thread.sleep(r.nextInt(500));
			} catch (InterruptedException ie) {}
			System.out.format("finished task #%d%n", id);
		}

	}

	private static class SameThreadExecutor implements Executor {
		@Override
		public void execute(Runnable task) {
			task.run();
		}
	}
}