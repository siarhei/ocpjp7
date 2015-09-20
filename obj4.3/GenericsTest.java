import java.util.*;

public class GenericsTest {
	public static void main(String[] args) {
		//List<Object> listObj = new ArrayList<String>(); //error: incompatible types
		List<String> listStr = new ArrayList<String>();

		Animal[] animals = new Animal[3];
		fill(animals);						//compile/runtime OK
		Animal[] dogs = new Dog[3];
		//fill(dogs);						//compile - OK, runtime - java.lang.ArrayStoreException

		List<Animal> listAnimal = new ArrayList<Animal>();
		listAnimal.add(new Cat());
		listAnimal.add(new Dog());
		listAnimal.add(new Bird());
		print(listAnimal);					//compile/runtime - OK
		printW(listAnimal);					//compile/runtime - OK
		printWE(listAnimal);				//compile/runtime - OK
		printWS(listAnimal);				//compile/runtime - OK

		List<Cat> listCat = new ArrayList<Cat>();
		List<Dog> listDog = new ArrayList<>();
		//print(listCat);					//error: method print in class GenericsTest cannot be applied to given types;
		addDog(listCat);
		//addDogO(listCat);					//error: method addDogO in class GenericsTest cannot be applied to given types;
		//addDogA(listDog);					//error: method addDogA in class GenericsTest cannot be applied to given types;
		addDogW(listCat);
		addDogW(listAnimal);
		addDogW(listDog);
		addDogWE(listCat);
		addDogWE(listAnimal);
		addDogWE(listDog);
		//addDogWS(listCat);				//error: method addDogWS in class GenericsTest cannot be applied to given types;
		//addDogWS(listDog);				//error: method addDogWS in class GenericsTest cannot be applied to given types;
		addDogWS(listAnimal);
	}

	private static void addDog(List list) {
		list.add(new Dog()); //warning: [unchecked] unchecked call to add(E) as a member of the raw type List
	}

	private static void addDogO(List<Object> list) {
		list.add(new Dog());
	}

	private static void addDogA(List<Animal> list) {
		list.add(new Dog());
	}

	private static void addDogW(List<?> list) {
		//list.add(new Dog()); // error: no suitable method found for add(Dog)
	}

	private static void addDogWE(List<? extends Animal> list) {
		//list.add(new Dog()); //error: no suitable method found for add(Dog)
	}

	private static void addDogWS(List<? super Animal> list) {
		list.add(new Dog());
	}

	private static void fill(Animal[] animals) {
		animals[0] = new Dog();
		animals[1] = new Cat();
		animals[2] = new Bird();
	}

	private static void print(List<Animal> listAnimal) {
		for (Animal a : listAnimal) {
			System.out.println(a);
		}
	}

	private static void printW(List<?> listAnimal) {
		for (Object a : listAnimal) {
			System.out.println(a);
		}
	}

	private static void printWE(List<? extends Animal> listAnimal) {
		for (Animal a : listAnimal) {
			System.out.println(a);
		}
	}

	private static void printWS(List<? super Animal> listAnimal) {
		for (Object a : listAnimal) {
			System.out.println(a);
		}
	}
}

class Animal {}
class Cat extends Animal {
	public String toString() {
		return "Cat";
	}
}
class Dog extends Animal {
	public String toString() {
		return "Dog";
	}
}
class Bird extends Animal {
	public String toString() {
		return "Bird";
	}	
}