package res.auth;

import java.util.HashMap;

public class AuthenticationDataService {
	// passwords should not be exchanged in plain text, we should use an hash of them. But for now I build the skeleton of
	// the application with plain text passwords, later we should change it.

	private HashMap<String, Token> powerUserToken = new HashMap<String, Token>();
	
	private HashMap<String, String> powerUserPassword = new HashMap<String, String>();
	
	
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	private static AuthenticationDataService ourInstance = new AuthenticationDataService();

    public static AuthenticationDataService getInstance() {
        return ourInstance;
    }
	
	public String addUserToken(String username) {
		
		if(powerUserToken.get(username) == null) {
		
			String token = randomAlphaNumeric(32);
			System.out.println(">[AuthenticationDataService] Login: " + username + " | " + token);
			
			this.powerUserToken.put(username, new Token(token, Boolean.FALSE));
			
			return token;
		} else {
			
			return this.powerUserToken.get(username).getToken();
			
		}
	}
	
	public static String randomAlphaNumeric(int count) {
		
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	
	public void removeUserToken(String username) {
		this.powerUserToken.remove(username);
	}
	
	public void addUserPassword(String username, String password) {
		System.out.println(">[AuthenticationDataService] Registration: " + username + " | " + password);
		
		this.powerUserPassword.put(username, password);
	}
	
	public void removeUserPassword(String username) {
		this.powerUserPassword.remove(username);
	}
	
	public Boolean isRegistered(String username, String password) {
		return powerUserPassword.containsKey(username);
	}
	
	public Boolean isTokenValid(String token) {
		if(powerUserToken.containsKey(token) && Boolean.FALSE.equals(powerUserToken.get(token).getExpired())) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
}
