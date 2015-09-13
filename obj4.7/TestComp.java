import java.util.Comparator;
import java.util.Arrays;

public class TestComp {
	//reverse to natural ordering
	private static final Comparator<String> CMP_STR = new Comparator<String>() {
		public int compare(String s1, String s2) {
			return -s1.compareTo(s2);
		}
	};

	//sort by Genre (natural order)
	private static final Comparator<Movie> CMP_MOV = new Comparator<Movie>() {
		public int compare(Movie m1, Movie m2) {
			return m1.getGenre().compareTo(m2.getGenre());
		}
	};

	public static void main(String[] args) {
		testStrArray();
		testMovieArray();
	}

	private static void testStrArray() {
		String[] array = {"One", "Two", "Three", "Four"};
		print(array);
		Arrays.sort(array);
		print(array);
		Arrays.sort(array, CMP_STR);
		print(array);
	}

	private static void testMovieArray() {
		Movie[] array = {
			new Movie("Star Wars", "blockbuster"),
			new Movie("Resident Evil", "horror"),
			new Movie("Matrix", "blockbuster"),
			new Movie("Interstellar", "science fiction")
		};
		print(array);
		Arrays.sort(array);
		print(array);
		Arrays.sort(array, CMP_MOV);
		print(array);
	}

	private static <T> void print(T[] args) {
		if (args != null) {
			for (T o : args) {
				System.out.print(o + " ");
			}
			System.out.print("\n");
		}
	}
}

class Movie implements Comparable<Movie> {
	private final String title;
	private final String genre;

	public Movie(String title, String genre) {
		this.title = title;
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	public int compareTo(Movie oth) {
		return this.title.compareTo(oth.title);
	}

	public String toString() {
		return "{" + title + " / " + genre + "}";
	}
}