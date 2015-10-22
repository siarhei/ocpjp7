import static java.lang.System.*;
import java.sql.*;

public class ConnectTest {
	static String connectionURL = "jdbc:derby://localhost:1527/firstdb";
	static String driver = "org.apache.derby.jdbc.ClientDriver";

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			out.println("cannot load derby driver");
		} finally {
			out.println("finished loading");
		}
	}

	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection(connectionURL, null, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Airlines");
			ResultSetMetaData rsmd = rs.getMetaData();

			rs.next();

			int cc = rsmd.getColumnCount();
			out.println("Column Count: " + cc);
		    for (int i = 1; i <= cc; i++) {
                out.println("Table Name:   " + rsmd.getTableName(i));
                out.println("Column Name:  " + rsmd.getColumnName(i));
                out.println("Column Size:  " + rsmd.getColumnDisplaySize(i));
			}

		} catch (SQLException e) {
			out.println(e);
		}
	}
}

//java -cp $DERBY_HOME/lib/derbyclient.jar:. ConnectTest