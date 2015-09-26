public class MethodInner {
	private void doAction(int x, final int y) {
		class Action {
			void execute() {
				//System.out.println("Execute action (local var): " + x); //error: local variable x is accessed from within inner class; needs to be declared final
				System.out.println("Execute action (local var): " + y);
			}

			void execute(int x) {
				System.out.println("Execute action: " + x);
			}
		}

		new Action().execute();
		new Action().execute(x);
		new Action().execute(y);
	}

	public static void main(String[] args) {
		new MethodInner().doAction(5, 10);
	}
}