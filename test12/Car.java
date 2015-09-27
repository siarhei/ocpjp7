public class Car {
	class Engine {
		// insert code here
		{Car.this.drive();}
		Engine() {Car.this.drive();}
	}
	public static void main(String[] args) {
		new Car().go();
	}
	void go() {
		new Engine();
	}
	void drive() { System.out.println("hi"); }
}