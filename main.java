import java.sql.SQLException;

public class main {

	public static void main(String[] args) throws SQLException {

		moviereview m = new moviereview();
		m.main(args);

		showreview s = new showreview();
		s.main(args);
	}

}
