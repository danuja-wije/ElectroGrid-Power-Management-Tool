package service;

public interface LoginService {
	public boolean isAuthenticated(String auth);
	public boolean authenticate(String userID,String password);
	public String getCurrentUser(String auth);
}
