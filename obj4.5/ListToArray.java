import java.util.*;

public class ListToArray {
	public static void main(String[] args) {
		List<String> sl = new ArrayList<>();
		sl.add("one"); sl.add("two"); sl.add("three"); sl.add("four");
		print(sl);

		Object[] sa1 = sl.toArray();
		print(sa1);
		//not backing collection
		sa1[0] = "ONE";
		print(sa1);	//sa1[0] == "ONE"
		print(sl); //sl.get(0) == "one"

		//length < sl.size()
		String[] sa2 = sl.toArray(new String[0]);
		print(sa2); //[one, two, three, four]

		//length > sl.size()
		String[] sa3 = sl.toArray(new String[sl.size() + 1]);
		print(sa3); //[one, two, three, four, null]
	}

	private static <T> void print(T[] a) {
		System.out.println("array: " + Arrays.toString(a));
	}

	private static <T> void print(List<T> l) {
		System.out.println("list: " + l);
	}
}