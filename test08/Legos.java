public class Legos {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder(8);
		System.out.print(sb.length() + "" + sb + "");
		sb.insert(0, "abcdef");
		//sb.insert(1, "abcdef"); //java.lang.StringIndexOutOfBoundsException: String index out of range: 1
		sb.append("789");
		System.out.println(sb.length() + " " + sb);
	}
}