package res.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User { 
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}

	private String id; 
    private String name; 
    private String address; 
    private String phoneNumber; 
 
    public User() { 
    	
    } 
	
	public User(String name, String address, String phoneNumber) {
		setName(name);
		setAddress(address);
		setPhoneNumber(phoneNumber);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
