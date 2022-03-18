package ynov.java.bank;

public class Auth {
	int toto;


	public static User login (String pseudo, String password, User[] users) {
		for (User user : users) {
			if(user.pseudo.equals(pseudo) && user.password.equals(password)) {
				return user;
			}
		}
		return null;
	}

}