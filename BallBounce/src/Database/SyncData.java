package Database;

import java.sql.SQLException;
import java.sql.Statement;

import Util.ToastMessage;
import Util.Variables;

public class SyncData extends Database {
	public void syncData() {
		query();
	}
	
	public void query() {
		try {
			Statement stmt = connect().createStatement();
			stmt.executeUpdate("UPDATE game SET paddle='" + Variables.selectedPaddle + "', color='" + Variables.selectedColor + "', score='" + Variables.highscore + "' WHERE username='" + Variables.username + "';");
			stmt.close();
			if (connect() != null) {
				ToastMessage.showToastMessage("Data synced successfully", 2000);
				connect().close();
			}
		} catch (ClassNotFoundException e) {
			ToastMessage.showToastMessage("Failed to connect to database", 2000);
			e.printStackTrace();
		} catch (SQLException e) {
			ToastMessage.showToastMessage("Failed to connect to database", 2000);
			e.printStackTrace();
		}
	}
}
