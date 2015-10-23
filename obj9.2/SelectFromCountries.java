import java.sql.*;
import static java.lang.System.*;

public class SelectFromCountries {
	public static void main(String[] args) throws SQLException {
		Connection c = DriverManager.getConnection(DB.URL, null, null);
		Statement st = c.createStatement();
		boolean isSelect = st.execute(
			String.format("select * from %s where %s = 'Europe' order by %s desc", 
				DB.Tables.Countries.TNAME, 
				DB.Tables.Countries.REGION, 
				DB.Tables.Countries.COUNTRY_ISO_CODE));
		if (isSelect) {
			out.println("select query");
			ResultSet rs = st.getResultSet();

			while(rs.next()) {
				out.format("%1$-4s\t%2$-25s\t%3$-10s%n", 
					rs.getString(DB.Tables.Countries.COUNTRY_ISO_CODE), 
					rs.getString(DB.Tables.Countries.COUNTRY), 
					rs.getString(DB.Tables.Countries.REGION));
			}
		}
	}
}