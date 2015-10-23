import static java.lang.System.*;
import java.sql.*;

public class ConnectTest {
	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection(DB.URL, null, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM " + DB.Tables.AIRLINES);
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

/*
siarhei$ unzip -l $DERBY_HOME/lib/derbyclient.jar | grep Driver
     7129  01-13-14 12:30   org/apache/derby/jdbc/ClientDriver.class
      856  01-13-14 12:30   org/apache/derby/jdbc/ClientDriver40.class
       35  01-13-14 12:31   META-INF/services/java.sql.Driver
*/

/*
siarhei$ jar -tf $DERBY_HOME/lib/derbyclient.jar | grep Driver
org/apache/derby/jdbc/ClientDriver.class
org/apache/derby/jdbc/ClientDriver40.class
META-INF/services/java.sql.Driver
*/