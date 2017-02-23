package res.model.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="FilterResult")
public class UserFilterResult {

	@XmlElement(name="User", required=false)
	private List<User>users;
	
	public UserFilterResult(){
		
	}
	
	public void setUserList(List<User> users) {
		//this.userMap = userMap;
		this.users = users;
	}
	
	public List<User> getUsers() {
		return users;
	}
}