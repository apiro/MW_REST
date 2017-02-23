package res.model.play;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PlayDataService {
	
	private List<Play> plays = new ArrayList<Play>();
	
    private static PlayDataService ourInstance = new PlayDataService();

    public static PlayDataService getInstance() {
        return ourInstance;
    }

    public void addPlay(Play play) {
    	plays.add(play);
    }

    public List<Play> getPlaysByUser(String userId) {
    	ArrayList<Play> out = new ArrayList<Play>();
    	for(Play p : plays){
    		if(p.getUserId().equals(userId))
    			out.add(p);
    	}
    	return out;
    }

    public List<Play> getPlaysByGame(String gameId) {
    	ArrayList<Play> out = new ArrayList<Play>();
    	for(Play p : plays){
    		if(p.getGameId().equals(gameId))
    			out.add(p);
    	}
    	return out;
    }

    public List<Play> getPlays() {
		return plays;
	}
    
    public List<Play> filter(HashMap<String, String> params) {
    	ArrayList<Play> out = new ArrayList<Play>();
    	
    	DateFormat format = new SimpleDateFormat("dd/MM/yy");
    	
    	String userId = params.get("userId") != null ? params.get("userId").trim() : "";
    	String gameId = params.get("gameId") != null ? params.get("gameId").trim() : "";
    	Date date = null;
    	try {
			date = params.get("date") != null ? format.parse(params.get("date")) : null;
		} catch (ParseException e) {
			date = null;
		}
    	
    	for(Play p:plays){
    		if((userId.length() == 0 || userId.equals(p.getUserId())) &&
    			(gameId.length() == 0 || gameId.equals(p.getGameId())) &&
    			(date == null || date.equals(p.getDate()))){
    				out.add(p);
    		}
    	}
    	return out;
    } 
    
}
