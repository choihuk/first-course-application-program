package view;
import java.io.FileNotFoundException;
import java.util.Scanner;

import controller.CLogin;
import entity.ELogin.InvalidUserException;

public class VLogin {
	//component
	private CLogin cLogin;
	
	public VLogin() {
		this.cLogin = new CLogin();
	}

	public String authenticate(String userIdText, String passwordText) {

		String userId = userIdText;
		String password = passwordText;
				
		try {
			this.cLogin.authenticate(userId, password);
			return userId;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InvalidUserException e) {
			e.printStackTrace();
			return null;
		}
	}
}
