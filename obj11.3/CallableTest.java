import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.*;

public class CallableTest {
	private static final int CORES = Runtime.getRuntime().availableProcessors();
	private static final int SIZE =  CORES * 2;

	public static void main(String[] args) {
		ExecutorService ex = Executors.newFixedThreadPool(SIZE);
		int[] rand = new int[SIZE];
		List<Future<Integer>> fs = new ArrayList<>(SIZE);

		for (int i = 0; i < SIZE; i++) {
			fs.add(i, ex.submit(new RandomIntTask()));
		}
		for (int i = 0; i < fs.size(); i++) {
			try {
				rand[i] = fs.get(i).get();
			} catch (InterruptedException | ExecutionException e) {
				System.out.println("Failed");
			}
		}
		ex.shutdown();
		try {
			//Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs, or the current thread is interrupted, whichever happens first.
			ex.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {

		} finally {
			//Returns true if all tasks have completed following shut down.
			if (!ex.isTerminated()) {
				//Attempts to stop all actively executing tasks, halts the processing of waiting tasks, and returns a list of the tasks that were awaiting execution.
				List<Runnable> terminated = ex.shutdownNow();
			}
		}
		
		System.out.format("array random values: %s%n", toString(rand));
	}

	private static String toString(int[] array) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]).append(' ');
		}
		return sb.substring(0, sb.length()-1);
	}

	private static class RandomIntTask implements Callable<Integer> {
		@Override
		public Integer call() {
			return ThreadLocalRandom.current().nextInt(1, 100);
		}
	}
}