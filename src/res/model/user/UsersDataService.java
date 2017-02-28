package res.model.user;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import res.model.Link;

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
        
        Link link_self = new Link();
		link_self.setHref("http://localhost:8080/BroadGamesREST/jaxrs/api/users/" + user.getId());
		link_self.setRel("self");
		
		Link link_plays = new Link();
		link_plays.setHref("http://localhost:8080/BroadGamesREST/jaxrs/api/plays/" + user.getId());
		link_plays.setRel("plays");
		
		user.addLink(link_self);
		user.addLink(link_plays);
        
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

    	if(orderAttribute == null) {
    		//no ordering is needed
    		return users.filterUsers(params);
    	}
    	
    	List<User> filteredUsersList = users.filterUsers(params);
    	
    	orderOneField(orderAttribute, descending, filteredUsersList);
    	
        return filteredUsersList;
    }

	private void orderOneField(final String orderAttribute, final boolean descending, List<User> filteredUsersList) {
		
		Collections.sort(filteredUsersList, new Comparator<User>() {
    	
			@Override
			public int compare(User o1, User o2) {
				
				int comparedResult;
				if (orderAttribute.equals("id")) {
					Integer int1 = Integer.decode(o1.getAttribute(orderAttribute));
					Integer int2 = Integer.decode(o2.getAttribute(orderAttribute));
					comparedResult = int1.compareTo(int2);
				} else {
					comparedResult = o1.getAttribute(orderAttribute).compareToIgnoreCase(o2.getAttribute(orderAttribute));
				}
				
				// comparedResult is negative if o1 attribute precedes o2 attribute
				
				if(descending) {
					return -comparedResult;
				} else {
					return comparedResult;
				}
			}
        });
	}
    
    public boolean findId(String id){
    	return users.getUserMap().containsKey(id);
    }
}