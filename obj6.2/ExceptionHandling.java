import java.sql.SQLException;
import java.io.IOException;
import java.io.FileNotFoundException;

public class ExceptionHandling {
	public static void main(String[] args) {
		
	}

	static void connectToDb() throws SQLException, IOException {}
	static void openRes() throws FileNotFoundException, IOException {} 

	static void priorTo7() {
		try {
			connectToDb();
		} catch (SQLException e) {
			e = new SQLException(e); //OK
			processError(e);
		} catch (IOException e) {
			processError(e);
		}
	}

	static void in7() {
		try {
			connectToDb();
		} catch (SQLException | IOException e) {
			//e = new SQLException(e); // error: multi-catch parameter e may not be assigned
			processError(e);
		}
	}

	static void in7io() throws IOException {
		try {
			openRes();
		} 
		//catch(FileNotFoundException | IOException e) {} //error: Alternatives in a multi-catch statement cannot be related by subclassing
														 //Alternative FileNotFoundException is a subclass of alternative IOException
		finally {}
	}

	static void processError(Exception e) {}
}

class RethrowingException {
	static void rethrow() throws SQLException, IOException {
		try {
			ExceptionHandling.connectToDb();
		} 
		//catch (SQLException | IOException e) {throw e;} // OK
		/*catch (Exception e) {
			e = new IOException();
			throw e; //error: unreported exception Exception; must be caught or declared to be thrown
		}*/
		catch (Exception e) { throw e; } //OK
	}
}