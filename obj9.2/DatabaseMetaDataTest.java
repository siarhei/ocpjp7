import java.sql.*;
import static java.lang.System.out;
import static java.sql.ResultSet.*;

public class DatabaseMetaDataTest {
	public static void main(String[] args) throws SQLException {
		Connection c = DriverManager.getConnection(DB.URL);
		DatabaseMetaData dbmd = c.getMetaData();
		int[] cursotTypes = {TYPE_FORWARD_ONLY, TYPE_SCROLL_INSENSITIVE, TYPE_SCROLL_SENSITIVE};
		for (int cursorType : cursotTypes) {
			isSupport(cursorType, dbmd);
		}
	}

	private static void isSupport(int cursorType, DatabaseMetaData dbmd) throws SQLException {
		if (dbmd.supportsResultSetType(cursorType)) {
			out.format("supports %s", decodeCursorType(cursorType));
			if (dbmd.supportsResultSetConcurrency(cursorType, CONCUR_UPDATABLE)) {
				out.println(" and CONCUR_UPDATABLE");
			}
		}
	}

	private static String decodeCursorType(int cursorType) {
		switch (cursorType) {
			case TYPE_FORWARD_ONLY: return "TYPE_FORWARD_ONLY";
			case TYPE_SCROLL_INSENSITIVE: return "TYPE_SCROLL_INSENSITIVE";
			case TYPE_SCROLL_SENSITIVE: return "TYPE_SCROLL_SENSITIVE";
			default:
				throw new IllegalArgumentException("unknown cursor type");
		}
	}
}
//Java DB v.10.8.3.2
/*
supports TYPE_FORWARD_ONLY and CONCUR_UPDATABLE
supports TYPE_SCROLL_INSENSITIVE and CONCUR_UPDATABLE
*/