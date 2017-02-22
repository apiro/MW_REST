package res.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Designer extends User {

	public Designer(String name, String address, String phoneNumber) {
		super(name, address, phoneNumber);
	}
	
	public Designer() {
	}
	
}