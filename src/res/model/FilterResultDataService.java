package res.model;

import java.util.HashMap;
import java.util.Map.Entry;

public class FilterResultDataService {

	private HashMap<Integer, FilterResult> activeResultsMap = new HashMap<Integer, FilterResult>();
	
    private static FilterResultDataService instance = new FilterResultDataService();
    
    private static int counter = 0;

    public static FilterResultDataService getInstance() {
        return instance;
    }

    public Integer addFilterResult(FilterResult filterResult) {
    	activeResultsMap.put(counter, filterResult);
    	counter++;
    	return counter-1;
    }
    
    public FilterResult getFilterResult(Integer key) {
    	return activeResultsMap.get(key);
    }
   
    public void clean(){
    	
    	for(Entry e: activeResultsMap.entrySet()) {}
    	
    }
}