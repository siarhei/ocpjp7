import java.sql.*;
import static java.lang.System.*;

public class SelectFromCountries {
	public static void main(String[] args) {
		try (Connection c = DriverManager.getConnection(DB.URL, null, null)) {
			try (Statement st = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
				boolean isSelect = st.execute(
					String.format("select * from %s where %s = 'Europe' order by %s desc", 
						DB.Tables.Countries.TNAME, 
						DB.Tables.Countries.REGION, 
						DB.Tables.Countries.COUNTRY_ISO_CODE));
				if (isSelect) {
					out.println("select query");
					ResultSet rs = st.getResultSet();

					out.printf("selected rows: %d%n", getSelectedRows(rs));

					print(rs);
					//print2(rs);

					if (rs != null) {
						out.println("closing resultSet...");
						rs.close();
					}
				}

				SQLWarning warn = c.getWarnings();
				printError(warn);
			}//try-with-resources Statement
		} catch (SQLException e) {
			printError(e);	
		}//try-with-resources Connection
	}

	private static void printError(SQLException e) {
		while (e != null) {
			out.format("SQLState: %s%n", e.getSQLState());
			out.printf("Vendor error code: %d%n", e.getErrorCode());
			out.printf("Message: %s%n", e.getMessage());
			e = e.getNextException();
		}
	}

	private static int getSelectedRows(ResultSet rs) throws SQLException {
		int count = 0;
		//if (rs.last()) { //ok
		if (rs.absolute(-1)) {
			count = rs.getRow();
			rs.beforeFirst();
		}
		return count;
	}

	private static void print(ResultSet rs) throws SQLException {
		while(rs.next()) {
			out.format("%1$-4s\t%2$-25s\t%3$-10s%n", 
				rs.getString(DB.Tables.Countries.COUNTRY_ISO_CODE), 
				rs.getString(DB.Tables.Countries.COUNTRY), 
				rs.getString(DB.Tables.Countries.REGION));
		}
	}

	/**
	 * Prints using ResultSetMetaData
	 */
	private static void print2(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		boolean hasData = rs.next();
		int columnCount = rsmd.getColumnCount();

		//prints header
		for (int i = 1; i <= columnCount; i++) {
			out.format("%1$-"+(rsmd.getColumnDisplaySize(i) + 1) + "s\t" + (i == columnCount ? "\n":""), rsmd.getColumnName(i));
		}
		if (hasData) {
			String data;
			do {
				for (int i = 1; i <= columnCount; i++) {
					data = rs.getObject(i) == null ? "NULL" : rs.getObject(i).toString();
					out.format("%1$-"+(rsmd.getColumnDisplaySize(i) + 1) + "s\t", data);
				}
				out.println();
			} while (rs.next());
		}
	}
}