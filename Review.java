import java.sql.Connection;
import java.sql.Date;

public abstract class Review {
	public Date date;
	public Connection myConn;

	public Date calculateDate() {
		return date;
	}

}
