import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class FilmReview extends Review {
	public Date date;
	public Connection myConn;

// Constructors	

	// Default Constructor

	public FilmReview() throws SQLException {
		this.date = calculateDate();
		this.myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8889/MovieCrtique", "kculbreth36",
				"Gerder11");
	}

	// Partial Constructor

	public FilmReview(Connection myConn) {
		this.date = calculateDate();
		this.myConn = myConn;
	}

	// Full Constructor

	public FilmReview(Connection myConn, Date date) {
		this.date = date;
		this.myConn = myConn;
	}

	// Method using the sql type of date to assign the current date to variable
	// "date"
	public Date calculateDate() {
		LocalDate date1 = java.time.LocalDate.now();
		Date date = Date.valueOf(date1);
		return date;
	}

}
