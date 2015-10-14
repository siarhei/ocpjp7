
public class InitializationOrder {
	public static void main(String[] args) {
		//1
		//new Child(5);

		//2
		//Child.doAction();

		//3
		//Parent.doAction();

		//4
		//String s = Child.CHILD_STATIC;

		//5
		//String s = Child.CHILD_STATIC_FINAL;

		//6
		//String s = Parent.PARENT_STATIC;

		//7
		//String s = Child.CHILD_STATIC_FINAL2;

		//8
		String s = Child.PARENT_STATIC;		
	}
}

class Parent {
	static String PARENT_STATIC = "PS";
	static void doAction() {};

	{System.out.println("Parent init");}
	static {System.out.println("Parent static init");}
	Parent(int i) {
		this();
		System.out.println("Parent(int) after this()");
	}
	Parent() {
		super();
		System.out.println("Parent() after super()");
	}
}

class Child extends Parent {
	static final String CHILD_STATIC_FINAL = "";
	static final String CHILD_STATIC_FINAL2 = empty();
	static String CHILD_STATIC = "";
	static void doAction() {}
	static String empty() {return "";}

	Child(int i) {
		this();
		System.out.println("Child(int) after this()");	
	}
	Child() {
		System.out.println("Child() after implicit super()");	
	}
	{System.out.println("Child init");}
	static {System.out.println("Child static init");}
}

//OUTPUT

/*
1.
Parent static init
Child static init
Parent init
Parent() after super()
Child init
Child() after implicit super()
Child(int) after this()
*/

/*
2.
Parent static init
Child static init
*/

/*
3.
Parent static init
*/

/*
4.
Parent static init
Child static init
*/

/*
5.
*/

/*
6.
Parent static init
*/

/*
7.
Parent static init
Child static init
*/

/*
8.
Parent static init
*/