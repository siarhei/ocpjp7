import java.util.*;

public class TreeSetTest {
	
	public static void main(String[] args) {
		NavigableSet<Integer> ns = new TreeSet<>();
		ns.add(10); ns.add(20); ns.add(15); ns.add(30);	
		print(ns);

		System.out.println("higher 15: " + ns.higher(15));		//20
		System.out.println("ceiling 15: " + ns.ceiling(15));	//15
		System.out.println("lower 15: " + ns.lower(15));		//10
		System.out.println("floor 15: " + ns.floor(15));		//15

		System.out.println("headSet(15): "); //headSet(toElement, false)
		print(ns.headSet(15));	//10
		System.out.println("headSet(15, true): ");
		print(ns.headSet(15, true));	//10, 15

		System.out.println("tailSet(15): "); //tailSet(fromElement, true)
		print(ns.tailSet(15));	//15, 20, 30

		System.out.println("tailSet(15, true): ");
		print(ns.tailSet(15, true));	//15, 20, 30

		System.out.println("tailSet(15, false): ");
		print(ns.tailSet(15, false));	//20, 30

		//BACKED COLLECTIONS
		System.out.println("subSet(15, 30): ");
		SortedSet<Integer> sub = ns.subSet(15, 30); //subSet(15, true, 20, false) -> [15, 20]
		print(sub);

		sub.add(17);
		print(ns);	//[10, 15, 17, 20, 30]
		print(sub);	//[15, 17, 20]

		ns.add(14);
		print(ns);	//[10, 14, 15, 17, 20, 30]
		print(sub);	//[15, 17, 20]

		//sub.add(12); //java.lang.IllegalArgumentException: key out of range
	}

	private static <T> void print(Collection<T> c) {
		System.out.println(c);
	}
}

