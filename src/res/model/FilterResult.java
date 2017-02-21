package res.model;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FilterResult {

	private HashMap<String, User> userMap = new HashMap<String, User>();
	
	public FilterResult(){
		
	}
	
	public HashMap<String, User> getUserMap() {
		return userMap;
	}

	public void setUserMap(HashMap<String,User> userMap) {
		this.userMap = userMap;
	}
}