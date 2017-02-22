package res.model;

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
public class FilterResult {

	@XmlElement(name="User", required=false)
	private List<User>users;
	
	//private HashMap<String, User> userMap = new HashMap<String, User>();
	
	public FilterResult(){
		
	}
	
	/*public HashMap<String, User> getUserMap() {
		return userMap;
	}*/

	public void setUserMap(HashMap<String,User> userMap) {
		//this.userMap = userMap;
		this.users = new ArrayList<User>(userMap.values());
	}
	
	public List<User> getUsers() {
		return users;
	}
}