package res.model;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Users {
	
	HashMap<String, User> userMap = new HashMap<String, User>();

	public Users(){
		
	}
	
	public int updateUser(User user) {
		User c = userMap.get(user.getId());
		if(c == null) {
			return 0;
		}
		c.setAddress(user.getAddress());
		c.setName(user.getName());
		c.setPhoneNumber(user.getPhoneNumber());
		return 1;
		
	}
	
	public int removeUser(String id) {
		if(userMap.remove(id) == null) {
			return 0;
		}
		return 1;
	}
	
	public HashMap<String, User> getUserMap() {
		return userMap;
	}

	public void setUserMap(HashMap<String,User> userMap) {
		this.userMap = userMap;
	}
	
	public HashMap<String, User> filterUsers(HashMap<String, String> params) {
		
		HashMap<String, User> users = new HashMap<String, User>();

		String name = params.get("name") != null ? params.get("name") : "";
		String address = params.get("address") != null ? params.get("address") : "";
		String phoneNumber = params.get("phoneNumber") != null ? params.get("phoneNumber") : "";
		String id = params.get("id") != null ? params.get("id") : "";

		for(User u:userMap.values()){
			if((name.trim().length() == 0 || name.equals(u.getName())) && 
				(address.trim().length() == 0 || address.equals(u.getAddress())) && 
				(phoneNumber.trim().length() == 0 || phoneNumber.equals(u.getPhoneNumber())) && 
				(id.trim().length() == 0 || id.equals(u.getId()))) {
				users.put(u.getId(), u);
			}
		}
		return users;
	}
}