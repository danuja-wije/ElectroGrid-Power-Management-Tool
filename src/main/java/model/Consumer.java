package model;

public class Consumer {

	public String consumerId;
	public String name;
	public String address;
	public String nic;
	public String mobile;
	public String email;
	public String username;
	public String password;
	
	
	
	public Consumer(String consumerId, String name, String address, String nic, String mobile, String email,
			String username, String password) {
		super();
		this.consumerId = consumerId;
		this.name = name;
		this.address = address;
		this.nic = nic;
		this.mobile = mobile;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public String getConsumerId() {
		return consumerId;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getNic() {
		return nic;
	}
	public String getMobile() {
		return mobile;
	}
	public String getEmail() {
		return email;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}	
}
