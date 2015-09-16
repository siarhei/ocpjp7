import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class TestSort {
	public static void main(String[] args) {
		assert args.length == 1 : "at least 1 parameter should be defined";

		switch (args[0]) {
			case "array":
				sortArray();
				break;
			case "list":
				sortList();
				break;
		}
	}

	private static void sortArray() {
		int[] array = {-9, 9, 6, 0, 10, 5, -7};
		format("Array (int) to sort: %s", array);
		Arrays.sort(array);
		format("Sorted: (int) %s", array);
		//Arrays.sort(array, Collections.reverseOrder()); //error: no suitable method found for sort(int[],Comparator<Object>)
		//format("Reverse ordering: %s", array);
		Integer[] arrayInt = {-9, 9, 6, 0, 10, 5, -7};
		format("Array (Integer) to sort: %s", arrayInt);
		Arrays.sort(arrayInt);
		format("Sorted: (Integer) %s", arrayInt);
		Arrays.sort(arrayInt, Collections.reverseOrder());
		format("Reverse ordering (Integer): %s", arrayInt);
	}

	static void format(String msg, int[] array) {
		System.out.format(msg+"\n", Arrays.toString(array));		
	}

	static <T> void format(String msg, T[] array) {
		System.out.format(msg+"\n", Arrays.toString(array));		
	}

	private static void sortList() {
		List<String> list = new ArrayList<>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		format("List to sort: %s", list);
		Collections.sort(list);
		format("Sorted: %s", list);
		Collections.sort(list, Collections.reverseOrder());
		format("Reverse order: %s", list);
		Collections.sort(list, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		});
		format("Sorted by length: %s", list);
	}

	static <T> void format(String msg, List<T> list) {
		System.out.format(msg+"\n", list);
	}
}