package res.model.user;

import java.util.HashMap;

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
    
    public HashMap<String, User> filter(HashMap<String, String> params) {
        return users.filterUsers(params);
    }
    
    public boolean findId(String id){
    	return users.getUserMap().containsKey(id);
    }
}