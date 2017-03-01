package res.auth;

public class Token {

	private String token;
	private Boolean expired;
	
	public Token(String token, Boolean expired) {
		setToken(token);
		setExpired(expired);
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getExpired() {
		return expired;
	}
	public void setExpired(Boolean expired) {
		this.expired = expired;
	}
	
	
}
