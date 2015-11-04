import java.io.*;
import static java.lang.System.*;

public class SerializationTest {
	private static class Animal {
		protected String kind;

		Animal() {
			this("defaultKind");
		}

		Animal(String kind) {
			this.kind = kind;
			out.println("Animal constructor");
		}
	}

	private static class BioInfo {
		private int age;
		private int weight;

		BioInfo(int age, int weight) {
			this.age = age;
			this.weight = weight;
			out.println("BioInfo constructor");
		}

		int getAge() {
			return age;
		}

		int getWeight() {
			return weight;
		}
	}

	private static class Dog extends Animal implements Serializable {
		private String name;
		private transient BioInfo info;

		Dog(String name) {
			super("a Dog");
			this.name = name;
			info = new BioInfo(5, 25);
			out.println("Dog constructor");
		}

		int getAge() {
			return info != null ? info.getAge() : -1;
		}
		int getWeight() {
			return info != null ? info.getWeight() : -1;
		}
		String getName() {
			return name;
		}
		String getKind() {
			return kind;
		}
		void setInfo(BioInfo info) {
			this.info = info;
		}

		public String toString() {
			return String.format("kind=%s; name=%s; age=%d; weight=%d", getKind(), getName(), getAge(), getWeight());
		}

		//manual serialization
		private void writeObject(ObjectOutputStream oos) {
			try {
				out.println("Starting manual serialization");
				oos.defaultWriteObject();
				oos.writeInt(getAge());
				oos.writeInt(getWeight());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//manual deserialization
		private void readObject(ObjectInputStream ois) {
			try {
				out.println("Starting manual deserialization");
				ois.defaultReadObject();
				int age = ois.readInt();
				int weight = ois.readInt();
				BioInfo info = new BioInfo(age, weight);
				setInfo(info);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		out.println("Creating new object");
		Animal dog = new Dog("dogName");

		String serFile = "SerializationTest.ser";

		try {
			out.println("Serializing: " + dog);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serFile));
			//ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(dog);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


		try {
			out.println("Deserializing... ");
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serFile));
			Dog ddog = (Dog) ois.readObject();
			ois.close();
			out.println("Deserialized: " + ddog);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

/*
Creating new object
Animal constructor
BioInfo constructor
Dog constructor
Serializing: kind=a Dog; name=dogName; age=5; weight=25
Starting manual serialization
Deserializing... 
Animal constructor
Starting manual deserialization
BioInfo constructor
Deserialized: kind=defaultKind; name=dogName; age=5; weight=25
*/