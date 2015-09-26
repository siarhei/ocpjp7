public class RegularInner {
	static int INT_CONST = 0;
	private int x = ++INT_CONST;

	private class Inner {
		private int x = ++INT_CONST;
		//public static void main(String[] args) {} //error: Illegal static declaration in inner class RegularInner.Inner
		//static {System.out.println("static initialization block at Inner");} //error: Illegal static declaration in inner class RegularInner.Inner
		static final String CONSTANT = "STR"; //OK

		void action() {
			System.out.println("Inner#action");
			print(RegularInner.this.x);	//Inner#print(int): 1
			RegularInner.this.print(x);	//RegularInner#print(int): 2
		}

		void print(int v) {
			System.out.format("Inner#print(int): %d\n", v);
		}
	}

	void action() {
		System.out.println("RegularInner#action");
	}

	void print(int v) {
		System.out.format("RegularInner#print(int): %d\n", v);
	}

	public static void main(String[] args) {
		RegularInner ri = new RegularInner();
		ri.action();

		RegularInner.Inner rii = ri.new Inner();
		rii.action();

		//rii = Inner.this; //error: not an enclosing class: RegularInner.Inner
	}
}