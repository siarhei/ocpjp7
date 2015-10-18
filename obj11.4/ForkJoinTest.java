import java.util.concurrent.*;
import java.util.*;

public class ForkJoinTest {
	private static final String FJ = "forkJoin";
	private static final String SIMPLE = "simple";
	private static final ForkJoinPool FJPOOL = new ForkJoinPool();

	public static void main(String[] args) {
		int[] a = new int[200_001_013];

		long before = System.currentTimeMillis();
		switch (args[0]) {
			case FJ:
				doForkJoinFill(a);
				break;
			case SIMPLE:
				doSimpleWayFill(a);
				break;
			default:
				System.out.format("use one of the next params:%n%s%n%s%n", FJ, SIMPLE);		
		}
		long total = System.currentTimeMillis() - before;
		System.out.format("initialization took %dms%n", total);

		System.out.println("Finding max element...");
		before = System.currentTimeMillis();
		int maxPos = FJPOOL.invoke(new FindMaxPositionTask(a, 0, a.length));
		total = System.currentTimeMillis() - before;
		System.out.format("Finding took %dms. Max element array[%d]=%d%n", total, maxPos, a[maxPos]);
	}

	private static void doForkJoinFill(int[] a) {
		FJPOOL.invoke(new FillInAction(a, 0, a.length));
	}

	private static void doSimpleWayFill(int[] a) {
		new FillInAction(a, 0, a.length-1).init();
	}

	private static class FillInAction extends RecursiveAction {
		private static final int THRESHOLD = 1000;
		private final int[] array;
		private final int start;
		private final int end;

		FillInAction(int[] array, int start, int end) {
			this.array = array;
			this.start = start;
			this.end = end;
		}

		@Override
		protected void compute() {
			if (end - start < THRESHOLD) {
				init();
			} else {
				//int med = (end - start) / 2 + start;
				int med = (start + end) >>> 1;

				ForkJoinTask left = new FillInAction(array, start, med);
				FillInAction right = new FillInAction(array, med, end);

				left.fork();
				right.compute();
				left.join();
				//invokeAll(right, left);
			}
		}

		public void init() {
			for (int i = start; i < end; i++) {
				array[i] = ThreadLocalRandom.current().nextInt();
			}
		}
	}

	private static class FindMaxPositionTask extends RecursiveTask<Integer> {
		private static final int THRESHOLD = 1000;

		private final int[] data;
		private final int start;
		private final int end;

		FindMaxPositionTask(int[] data, int start, int end) {
			this.data = data;
			this.start = start;
			this.end = end;
		}

		@Override
		protected Integer compute() {
			if (end - start < THRESHOLD) {
				int pos = 0;
				for (int i = start; i < end; i++) {
					if (data[i] > pos) {
						pos = i;
					}
				}
				return pos;
			}
			int mid = (end + start) >>> 1;
			FindMaxPositionTask leftTask = new FindMaxPositionTask(data, start, mid);
			leftTask.fork();
			FindMaxPositionTask rightTask = new FindMaxPositionTask(data, mid, end);
			int rPos = rightTask.compute();
			int lPos = leftTask.join();

			if (data[lPos] == data[rPos]) {
				return lPos <= rPos ? lPos : rPos;
			} else {
				return data[lPos] > data[rPos] ? lPos : rPos;
			}
		}
	}
}

//[0][1][2][3][4][5]