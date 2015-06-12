package serializierung1;

public class Credentials {
	public final String login;
	public final String password;

	public Credentials(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return String.format("Login: '%s' | Password: '%s'", login, password);
	}
}
