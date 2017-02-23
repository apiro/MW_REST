package res.model.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="User")
public class User { 

	@XmlAttribute(name="id", required=true)
	private String id;
	@XmlAttribute(name="name", required=true)
    private String name; 
	@XmlAttribute(name="address", required=true)
    private String address; 
	@XmlAttribute(name="phoneNumber", required=true)
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
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}
	
}
