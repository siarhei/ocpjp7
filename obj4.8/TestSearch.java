import java.util.*;

public class TestSearch {
	public static void main(String[] args) {
		assert args.length == 1 : "at least 1 parameter should be defined";

		switch (args[0]) {
			case "array":
				searchArray();
				break;
			case "list":
				searchList();
				break;
		}		
	}

	private static void search(int[] array, int key) {
		int position = Arrays.binarySearch(array, key);
		System.out.format("array %s. Key %d. Searched position: %d\n", Arrays.toString(array), key, position);
	}

	private static void searchArray() {
		int[] array = {10, -10, 5, -5, 0, 3, 4, 1};
		search(array, 5); //-9
		search(array, 6); //-9
		System.out.println("Sorting in natural order way");
		Arrays.sort(array);
		search(array, 5); //6
		search(array, 6); //-8
		search(array, 2); //-4-1 = -5
		search(array, -11); //0-1 = -1
	}

	private static void searchList() {
		List<String> list = new ArrayList<>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		search(list, "one"); //undefined
		search(list, "four"); //undefined
		System.out.println("Sorting in natural order way");
		Collections.sort(list);
		search(list, "one"); //1
		search(list, "five"); //-1
		System.out.println("Sorting in opposite to natural order way");
		Comparator<String> c = Collections.<String>reverseOrder();
		Collections.sort(list, c);
		search(list, "one"); //undefined
		search(list, "five"); //undefined
		search(list, "one", c); //2
		search(list, "five", c); //-5
		search(list, "six", c); //-3
	}

	private static <T> void search(List<? extends Comparable<? super T>> list, T key) {
		int position = Collections.binarySearch(list, key);
		System.out.format("list %s. Key %s. Searched position: %d\n", list, key, position);
	}

	private static <T> void search(List<? extends T> list, T key, Comparator<? super T> c) {
		int position = Collections.binarySearch(list, key, c);
		System.out.format("list %s. Key %s. Searched position: %d\n", list, key, position);
	}
}