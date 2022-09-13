import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Driver {

	public static void main(String[] args) {

		try {
			// 1. get a connection to database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8889/MovieCrtique", "root", "root");
			// 2. create a statement
			Statement st = myConn.createStatement();
			// 3. execute SQL query
			ResultSet myRs = st.executeQuery("select * from FilmReview");
			// 4. process the result set
			while (myRs.next()) {
				ResultSet rs = st.executeQuery("select * from FilmReview");

				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();

				while (rs.next()) {
					for (int i = 1; i < columnsNumber; i++)
						System.out.print(rs.getString(i) + " ");
					System.out.println();

				}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}
}
