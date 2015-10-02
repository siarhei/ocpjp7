public class SyncStringBuffer {
	public static void main(String[] args) throws InterruptedException {
		final StringBuffer sb = new StringBuffer("A");
		for (int i = 5; i > 0; i--) {
			Thread t = new CustomThread(sb);
			t.start();
			t.join();
		}
		System.out.println(sb);
	}
}

class CustomThread extends Thread {
	private static final int N = 100;
	private final StringBuffer sb;

	CustomThread(StringBuffer sb) {
		this.sb = sb;
	}

	@Override
	public void run() {
		synchronized (sb) {
			int length = sb.length();
			boolean firstThread = length == 1;
			int delta = firstThread ? 0:1;
			//gets last char
			char c = firstThread ? sb.charAt(length-1):(char)(sb.charAt(length-1) + 1);
			for (int i = 0; i < N-1+delta; i++) {
				sb.append(c);
			}
		}
	}
}