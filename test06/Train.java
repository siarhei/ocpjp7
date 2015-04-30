class Train {
	class RanOutOfTrack extends Exception { }
	class AnotherTrainComing extends Exception { }
	public static void main(String[] args) throws RanOutOfTrack, AnotherTrainComing {
		Train a = new Train();
		try {
			a.drive();
			System.out.println("honk! honk!");
		} 
		//catch(AnotherTrainComing | RanOutOfTrack e) { //OK
		//catch(Exception e) { //OK
		catch(RanOutOfTrack e) { //OK
			System.out.println("error driving");
			throw e;
		}
	}

	void drive() throws RanOutOfTrack, AnotherTrainComing {
		throw new RanOutOfTrack();
	} 
}