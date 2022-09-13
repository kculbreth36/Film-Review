import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class moviereview extends FilmReview implements ifilmdatabase {
	public Date date;
	public String movieName;
	public int releaseDate;
	public String rating;
	public String review;
	private static Scanner myObj;
	private String movieName1;
	private int releaseDate1;
	private String rating1;
	private String review1;

// Constructors

	// Default Constructor

	public moviereview() throws SQLException {
		this.movieName = "Drive";
		this.releaseDate = 2011;
		this.rating = "9/10";
		this.review = "Great soundtrack to compliment the performances of both Ryan Gosling and the other strong actors. The enigmatic aura the film has gives it a feeling unlike any other.";
		this.date = calculateDate();
	}

	// Partial Constructor

	public moviereview(String review) throws SQLException {
		super();
		this.movieName = "Drive";
		this.releaseDate = 2011;
		this.rating = "9/10";
		this.review = review;
		this.date = calculateDate();
	}

	// Full Constructor

	public moviereview(String movieName, int releaseDate, String rating, String review, Date date) throws SQLException {
		super();
		this.movieName = movieName;
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
		System.out.println("Enter Movie Title :");

		movieName1 = myObj.nextLine();

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
					"INSERT INTO Movies (Date, MovieName, ReleaseDate, Rating, Review) VALUES (?,?,?,?,?)");
			pstmt.setDate(1, date);
			pstmt.setString(2, movieName1);
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
		System.out.println("Your movie name is :" + movieName1);
		System.out.println();
		System.out.println("The release date for that movie was :" + releaseDate1);
		System.out.println();
		System.out.println("Your rating for this movie is : " + rating1);
		System.out.println();
		System.out.println("Your review for this movie is :" + review1);
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

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
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
		moviereview m = new moviereview();
		m.userinput();
		m.addToDatabase();
		m.display();
	}
}
