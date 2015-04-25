//disabled by default so -ea should be provided for enabling

public class Assertions {
	public static void main(String[] args) {
		//checkNegativeNums();
		//checkEvenDigit();

		assert 10%2 == 1 : new AssertionDesc("Unexpected division result");
	}

	static void checkNegativeNums() {
		int[] nums = {-1, -5, 5};
		for (int arg : nums) {
			assert isNegative(arg) : "The number " + arg + " must be negative";
		}
	}

	static void assertPositive(int arg) {
		assert arg > 0;
	}

	//checks if arg value is negative, if so, then returns true; false otherwise
	static boolean isNegative(int arg) {
		return arg < 0;
	}

	static void checkEvenDigit() {
		byte[] nums = new byte[]{0, 4, 6, 7};
		for (byte num : nums) {
			switch (num) {
				case 0:
				case 2:
				case 4:
				case 6:
				case 8: System.out.println("even number"); break;
				default: assert false : "The number " + num + " is not even";
			}
		}
	}
}

class AssertionDesc {
	final String description;
	AssertionDesc(String desc) {
		description = desc;
	}

	@Override
	public String toString() {
		return description;
	}
}