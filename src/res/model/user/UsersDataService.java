package res.model.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class UsersDataService {
	
    private Users users = new Users();

    private static UsersDataService instance = new UsersDataService();

    public static UsersDataService getInstance() {
        return instance;
    }

    public int updateUser(User user) {
    	return users.updateUser(user);
    }
    
    public int removeUser(String id) {
    	return users.removeUser(id);
    }
    
    public String addUser(User user) {
        String newId = Integer.toString(users.getUserMap().size() + 1);
        user.setId(newId);
        users.getUserMap().put(newId, user);
        return newId;
    }

    public HashMap<String, User> getUserMap() {
        return users.getUserMap();
    }


    public User getUserById(String id) {
        return users.getUserMap().get(id);
    }
    
    public List<User> filter(HashMap<String, String> params, final String orderAttribute, final boolean descending) {
    	
    	List<User> filteredUsersList = users.filterUsers(params);
    	for(User u:filteredUsersList) {
    		System.out.println("> " + u.toString());
    	}
    	
    	Collections.sort(filteredUsersList, new Comparator<User>() {
    	
			@Override
			public int compare(User o1, User o2) {
				System.out.println(orderAttribute);
				System.out.println(descending);
				String value1 = o1.getAttribute(orderAttribute);
				String value2 = o2.getAttribute(orderAttribute);
				int value = value1.compareToIgnoreCase(value2);
				if(descending == true) {
					
				} else {
					
				}
				
				return 0;
			}

        });
    	
    	for(User u:filteredUsersList) {
    		System.out.println("> " + u.toString());
    	}	
    	
        return users.filterUsers(params);
    }
    
    public boolean findId(String id){
    	return users.getUserMap().containsKey(id);
    }
}