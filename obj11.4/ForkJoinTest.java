import java.util.concurrent.*;
import java.util.*;

public class ForkJoinTest {
	private static final String FJ = "forkJoin";
	private static final String SIMPLE = "simple";

	public static void main(String[] args) {
		int[] a = new int[200_001_000];

		long before = System.currentTimeMillis();
		switch (args[0]) {
			case FJ:
				doForkJoin(a);
				break;
			case SIMPLE:
				doSimpleWay(a);
				break;
			default:
				System.out.format("use one of the next params:%n%s%n%s%n", FJ, SIMPLE);		
		}
		long total = System.currentTimeMillis() - before;
		System.out.format("initialization took %d ms%n", total);
	}

	private static void doForkJoin(int[] a) {
		ForkJoinPool ex = new ForkJoinPool();
		ex.invoke(new Task(a, 0, a.length-1));
	}

	private static void doSimpleWay(int[] a) {
		new Task(a, 0, a.length-1).init();
	}

	private static class Task extends RecursiveAction {
		private static final int TRESHOLD = 1000;
		private final int[] array;
		private final int start;
		private final int end;

		Task(int[] array, int start, int end) {
			this.array = array;
			this.start = start;
			this.end = end;
		}

		@Override
		public void compute() {
			if (end - start < TRESHOLD) {
				init();
			} else {
				int med = (end - start) / 2 + start;
				Task left = new Task(array, start, med);
				left.fork();

				Task right = new Task(array, med+1, end);
				right.compute();
				left.join();
			}
		}

		public void init() {
			for (int i = start; i <= end; i++) {
				array[i] = ThreadLocalRandom.current().nextInt();
			}
		}
	}
}

//[0][1][2][3][4][5]