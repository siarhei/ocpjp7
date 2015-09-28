public class SleepThread {
	public static void main(String[] args) {
		impWay();
		//extWay();
	}

	private static void extWay() {
		SleepThread st = new SleepThread();
		st.new Doer2("First").start();
		st.new Doer2("Second").start();
	}

	private static void impWay() {
		SleepThread st = new SleepThread();
		new Thread(st.new Doer(), "First").start();
		new Thread(st.new Doer(), "Second").start();
	}

	private void job() {
		for (int i = 1; i <= 100; i++) {
			if (i%10 == 0) {
				System.out.format("[%s]:\t\t\t%d\n", Thread.currentThread().getName(), i);
				try {
					Thread.sleep(1000);
				} catch(InterruptedException e) {
					//ignore it
				}
				if (i == 50 && Thread.currentThread().getName().startsWith("F")) throw new IllegalArgumentException();
			}
		}
	}

	class Doer implements Runnable {
		@Override
		public void run() {
			job();
		}
	}

	class Doer2 extends Thread {
		Doer2(String name) {
			super(name);
		}

		@Override
		public void run() {
			SleepThread.this.job();
		}
	}
}