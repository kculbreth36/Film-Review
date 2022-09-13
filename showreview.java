import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class showreview extends FilmReview implements ifilmdatabase {
	public Date date;
	public String showName;
	public int releaseDate;
	public String rating;
	public String review;
	private Scanner myObj;
	private String showName1;
	private int releaseDate1;
	private String rating1;
	private String review1;

// Constructors

	// Default Constructor

	public showreview() throws SQLException {
		this.showName = "Naruto";
		this.releaseDate = 2002;
		this.rating = "9/10";
		this.review = "This show is the pinnacle of what a shonen anime is. You grow with the characters through adolescence and feel the weight of increasingly heavy topics from the point of view of innocence. It masterfully shows off how characters can change and breaks down and throws out the black and white character tropes so commonly found throughout shows now.";
		this.date = calculateDate();
	}

	// Partial Constructor

	public showreview(String review) throws SQLException {
		super();
		this.showName = "Naruto";
		this.releaseDate = 2002;
		this.rating = "9/10";
		this.review = review;
		this.date = calculateDate();
	}

	// Full Constructor

	public showreview(String showName, int releaseDate, String rating, String review, Date date) throws SQLException {
		super();
		this.showName = showName;
		this.releaseDate = releaseDate;
		this.rating = rating;
		this.review = review;
		this.date = date;
	}

// Inherits the method from its parent class FilmReview
	@Override
	public Date calculateDate() {
		LocalDate date1 = java.time.LocalDate.now();
		Date date = Date.valueOf(date1);
		return date;
	}

	// Method to print the date to have a reference of when you are getting paid
	public void displayDate() {
		System.out.println("Todays Date: " + this.date);
		System.out.println();
	}

// Inherits the method from the interface

	@Override
	public void userinput() {
		myObj = new Scanner(System.in);
		System.out.println("Enter Show Title :");

		showName1 = myObj.nextLine();

		myObj = new Scanner(System.in);
		System.out.println("Enter Release Date :");

		releaseDate1 = myObj.nextInt();

		myObj = new Scanner(System.in);
		System.out.println("Enter Your Rating :");

		rating1 = myObj.nextLine();

		myObj = new Scanner(System.in);
		System.out.println("Enter Your Review :");

		review1 = myObj.nextLine();

	}

	public void addToDatabase() {

		// Try Catch statement to check the code for errors as it executes
		try {

			// Makes a connection to the database using the Driver and Login credentials for
			// the admin of the database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8889/MovieCrtique", "root", "root");

			// A prepared statement rather than a statement to execute input parameters
			// Determines the table in the database and selects the columns to input values
			// into
			PreparedStatement pstmt = myConn.prepareStatement(
					"INSERT INTO Shows (Date, ShowName, ReleaseDate, Rating, Review) VALUES (?,?,?,?,?)");
			pstmt.setDate(1, date);
			pstmt.setString(2, showName1);
			pstmt.setInt(3, releaseDate1);
			pstmt.setString(4, rating1);
			pstmt.setString(5, review1);
			pstmt.executeUpdate();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		System.out.println();
	}

	public void display() {
		System.out.println("Your show name is :" + showName1);
		System.out.println();
		System.out.println("The release date for that show was :" + releaseDate1);
		System.out.println();
		System.out.println("Your rating for this show is : " + rating1);
		System.out.println();
		System.out.println("Your review for this show is :" + review1);
		System.out.println();
		System.out.println("Your review has been placed into our database. Thank you for your insight.");
	}

// Getters and Setters

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public int getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(int releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

// Main
	public static void main(String[] args) throws SQLException {
		showreview s = new showreview();
		s.userinput();
		s.addToDatabase();
		s.display();

	}

}
