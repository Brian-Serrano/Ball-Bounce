package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import Util.SaveResources;
import Util.ToastMessage;
import Util.Variables;

public class SignUpSave extends Database {
	public String username, email, password;
	public SaveResources saveResources;
	
	public SignUpSave() {
		this.saveResources = new SaveResources();
	}
	
	public boolean noInput() {
		return username.isEmpty() || email.isEmpty() || password.isEmpty() ||
				username.equals("ENTER USERNAME") || email.equals("ENTER EMAIL") || password.equals("ENTER PASSWORD");
	}
	
	public boolean invalidLength() {
		return username.length() > 20 || username.length() < 7 ||
				email.length() > 40 || email.length() < 10 ||
				password.length() > 20 || password.length() < 7;
	}
	
	public boolean isValidEmail() {
	    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    Pattern pattern = Pattern.compile(emailRegex);
	    return pattern.matcher(email).matches();
	}
	
	public boolean takenCheck() {
		return checkUser();
	}
	
	public void signUpSave(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		
		if(noInput()) {
			ToastMessage.showToastMessage("No input", 2000);
			return;
		}
		
		if(invalidLength()) {
			ToastMessage.showToastMessage("Invalid length", 2000);
			return;
		}
		
		if(!isValidEmail()) {
			ToastMessage.showToastMessage("Invalid email", 2000);
			return;
		}
		
		if(takenCheck()) {
			ToastMessage.showToastMessage("Username is taken", 2000);
			return;
		}
		
		query();
	}
	
	public boolean checkUser() {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT username FROM game WHERE username='" + username + "' OR email='" + email + "';");
			return rs.next();
		} catch (ClassNotFoundException e) {
			ToastMessage.showToastMessage("Failed to connect to database", 2000);
			e.printStackTrace();
			return true;
		} catch (SQLException e) {
			ToastMessage.showToastMessage("Failed to connect to database", 2000);
			e.printStackTrace();
			return true;
		}
	}
	
	public void query() {
		try {
			Statement stmt = connect().createStatement();
			stmt.executeUpdate("INSERT INTO game (username, email, password, paddle, color, score) VALUES ('" + username + "', '" + email + "', '" + password + "', '" + Variables.selectedPaddle + "', '" + Variables.selectedColor + "', '" + Variables.highscore + "');");
			stmt.close();
			if (connect() != null) {
				Variables.isLoggedIn = true;
				Variables.username = username;
				saveResources.saveResources();
				Variables.selectedPanel = 2;
				ToastMessage.showToastMessage("Signed up successfully", 2000);
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
