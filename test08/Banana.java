public class Banana {
	public static void main(String[] args) {
		String in = "1 a2 b 3 c4d 5e";
		String[] chunks = in.split(args[0]);
		System.out.println("count " + chunks.length);
		int i = 1;
		for(String s : chunks)
			System.out.print("#" + i++ +">" + s + "< ");
		}
}

/*java Banana " "
count 6
#1>1< #2>a2< #3>b< #4>3< #5>c4d< #6>5e<*/

/*java Banana "\d"
count 6
#1>< #2> a< #3> b < #4> c< #5>d < #6>e<*/