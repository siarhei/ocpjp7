import static java.lang.System.*;

public interface DB {
	String URL = "jdbc:derby://localhost:1527/toursdb";
	String DRIVER = "org.apache.derby.jdbc.ClientDriver";

	public static interface Tables {
		String AIRLINES = "AIRLINES";
		String COUNTRIES = "COUNTRIES";
		String CITIES = "COUNTRIES";
		String FLIGHTS = "FLIGHTS";
		String FLIGHTAVAILABILITY = "FLIGHTAVAILABILITY";
		String MAPS = "MAPS";
		String FLIGHTS_HISTORY = "FLIGHTS_HISTORY";
	}

	/**
	 * It isn't necessary to load Derby driver in the given way (it's loaded from META-INF/services/java.sql.Driver [$DERBY_HOME/lib/derbyclient.jar])
	 */
	public static class DriverLoader {
		/*static {
			try {
				out.println("trying to load derby driver");
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				out.println("cannot load derby driver");
			} finally {
				out.println("finished loading");
			}
		}*/
	}
}