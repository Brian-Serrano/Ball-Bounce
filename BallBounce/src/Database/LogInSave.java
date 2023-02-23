package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Util.SaveResources;
import Util.ToastMessage;
import Util.Variables;

public class LogInSave extends Database {
	public String username, password;
	public SaveResources saveResources;
	
	public LogInSave() {
		this.saveResources = new SaveResources();
	}
	
	public boolean noInput() {
		return username.isEmpty() || password.isEmpty() ||
				username.equals("ENTER USERNAME") || password.equals("ENTER PASSWORD");
	}
	
	public boolean invalidLength() {
		return username.length() > 20 || username.length() < 7 ||
				password.length() > 20 || password.length() < 7;
	}
	
	public void logInSave(String username, String password) {
		this.username = username;
		this.password = password;
		
		if(noInput()) {
			ToastMessage.showToastMessage("No input", 2000);
			return;
		}
		
		if(invalidLength()) {
			ToastMessage.showToastMessage("Invalid length", 2000);
			return;
		}
		
		query();
	}
	
	public void query() {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT username FROM game WHERE username='" + username + "' AND password='" + password + "'");
			if(!rs.next()) {
				ToastMessage.showToastMessage("User not found", 2000);
				return;
			}
			rs.close();
			stmt.close();
			if (connect() != null) {
				Variables.isLoggedIn = true;
				Variables.username = username;
				saveResources.saveResources();
				Variables.selectedPanel = 2;
				ToastMessage.showToastMessage("Logged in successfully", 2000);
				connect().close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
