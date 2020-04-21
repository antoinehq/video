package video.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Context {
	// encapsule et gere la connection
	private Connection connection = null;
	private static Context singleton = null;

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Context() {
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/video", "postgres", "postgres");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Connection getConnection() {
		return connection;
	}

	public static Context getInstance() {
		if (singleton == null) {
			singleton = new Context();
		}
		return singleton;
	}

	public static void destroy() {
		if (singleton != null) {
			try {
				singleton.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			singleton = null;
		}
	}

}
