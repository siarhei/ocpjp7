public class Anonymous {
	public static void main(String[] args) {
		Doer d = new Doer();
		d.doAction(new Action() {
			public void execute() {
				System.out.println("param anon inner class");
			}
		});

		//
		d = new Doer() {
			void doAction(Action a) {
				System.out.println("#doAction custom before");
				super.doAction(a);
				System.out.println("#doAction custom after");
			}
		};
		d.doAction(new DefAction());
	}
}

class Doer {
	void doAction(Action a) {
		System.out.println("#doAction default implementation");
		a.execute();
	}
}

interface Action {
	void execute();
}

class DefAction implements Action {
	public void execute() {
		System.out.println("DefAction implementation");
	}
}