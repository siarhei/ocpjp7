public class Nested {
	private final static int X = 100;
	private int x;
	static {
		System.out.println("Nested static block");
	}

	static class Inner {
		private final static int X = -100;
		static {
			System.out.println("Inner static block");
		}

		public static void main(String[] args) { //could be invoked as well as Nested@main
			System.out.println("Inner@main");	
		}

		private void printNested() {
			System.out.println("Nested.X:" + Nested.X);
			System.out.println("X:" + X);
			//System.out.println("Nested.x:" + x);//error: non-static variable x cannot be referenced from a static context
		}

		public String toString() {
			printNested();
			return "Inner";
		}
	}

	public static void main(String[] args) {
		System.out.println("Nested@main");
		Nested.Inner ni = new Nested.Inner();
		ni.printNested();	
	}
}
/*
===
java Nested
---
Nested static block
Nested@main
Inner static block
Nested.X:100
X:-100
===
java Nested$Inner
---
Inner static block
Inner@main*/