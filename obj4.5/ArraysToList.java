import java.util.*;

public class ArraysToList {
	public static void main(String[] args) {
		String[] sa = {"one", "two", "three", "four"};
		List<String> sl = Arrays.asList(sa);
		print(sa);
		print(sl);
		sa[1] = "TWO";
		print(sa);
		print(sl);
		//sl.add("3"); //java.lang.UnsupportedOperationException
		//sl.add(2, "3"); //java.lang.UnsupportedOperationException

		//primitives
		int[] ia = {0, 1, 2, 3};
		List il = Arrays.asList(ia); //creates a list which contains array (il.get(0).getClass() == int[].class)
		print(ia);
		print(il);
	}

	private static <T> void print(T[] a) {
		System.out.println("array: " + Arrays.toString(a));
	}

	private static void print(int[] a) {
		System.out.println("array(int): " + Arrays.toString(a));
	}

	private static <T> void print(List<T> l) {
		if (l.get(0).getClass() == int[].class) {
			System.out.println("list(0): " + Arrays.toString((int[])l.get(0)));	
		}
		System.out.println("list: " + l);
	}
}