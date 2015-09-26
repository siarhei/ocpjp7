import java.util.Arrays;

public class ArgumentInner {
	/*
	//it's legal declaration. This class hides other Action
	static class Action {
		void execute() {
			System.out.println("Executing action2:" + this);
		}
	}*/ 

	public static void main(final String[] args) {
		new ArgumentInner().execute(new Action() {
			public String toString() {
				return "[argument inner action]" + Arrays.asList(args); // it's ok
			}
		});
	}

	private void execute(Action a) {
		a.execute();
	}
}

class Action {
	void execute() {
		System.out.println("Executing action:" + this);
	}
}