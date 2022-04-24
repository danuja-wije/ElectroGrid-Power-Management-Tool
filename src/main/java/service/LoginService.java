package service;

public interface LoginService {
	public boolean isAuthenticated(String auth);
	public boolean authenticate(String email,String password);
}
