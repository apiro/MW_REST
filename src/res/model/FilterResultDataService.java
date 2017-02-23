package res.model;

import java.util.HashMap;
import java.util.Map.Entry;

import res.model.user.UserFilterResult;

public class FilterResultDataService {

	private HashMap<String, UserFilterResult> activeResultsMap = new HashMap<String, UserFilterResult>();
	
    private static FilterResultDataService instance = new FilterResultDataService();
    
    private static int counter = 0;

    public static FilterResultDataService getInstance() {
        return instance;
    }

    public String addFilterResult(UserFilterResult filterResult) {
    	activeResultsMap.put(Integer.toString(counter), filterResult);
    	
    	System.out.println(activeResultsMap.get(0));
    	
    	counter++;
    	return Integer.toString(counter-1);
    }
    
    public UserFilterResult getFilterResult(String key) {
    	
    	System.out.println(activeResultsMap.size());
    	
    	return activeResultsMap.get(key);
    }
   
    public void clean(){
    	
    	for(Entry e: activeResultsMap.entrySet()) {}
    	
    }
}