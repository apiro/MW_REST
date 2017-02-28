package res.model.play;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import res.model.Link;

public class PlayDataService {
	
	private List<Play> plays = new ArrayList<Play>();
	
    private static PlayDataService ourInstance = new PlayDataService();

    public static PlayDataService getInstance() {
        return ourInstance;
    }

    public void addPlay(Play play) {
    	
    	Link link_player = new Link();
		Link link_game = new Link();
		link_player.setHref("http://localhost:8080/BroadGamesREST/jaxrs/api/boardGames/" + play.getUserId());
		link_player.setRel("player");
		link_game.setHref("http://localhost:8080/BroadGamesREST/jaxrs/api/users/" + play.getGameId());
		link_game.setRel("game");
		
		play.addLink(link_game);
		play.addLink(link_player);
    	
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
    
    public List<Play> filter(HashMap<String, String> params, String orderAttribute, Boolean descending) {
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
    	
    	if(orderAttribute == null) {
    		return out;
    	}
    	
    	orderOneField(orderAttribute, descending, out);
    	
    	return out;
    }
    
    private void orderOneField(final String orderAttribute, final boolean descending, List<Play> filteredUsersList) {
		
		Collections.sort(filteredUsersList, new Comparator<Play>() {
    	
			@Override
			public int compare(Play p1, Play p2) {
				
				int comparedResult;
				
				if(orderAttribute.equals("date")) {
					DateFormat format = new SimpleDateFormat("dd/MM/yy");
					Date date1;
					Date date2;
					try {
						date1 = format.parse(p1.getAttribute(orderAttribute));
						date2 = format.parse(p2.getAttribute(orderAttribute));
					} catch (ParseException e) {
						date1 = null;
						date2 = null;
					}
					comparedResult = date1.compareTo(date2);
				} else if (orderAttribute.equals("numPlayers")) {
					Integer int1 = Integer.decode(p1.getAttribute(orderAttribute));
					Integer int2 = Integer.decode(p2.getAttribute(orderAttribute));
					comparedResult = int1.compareTo(int2);
				} else {
					comparedResult = p1.getAttribute(orderAttribute).compareToIgnoreCase(p2.getAttribute(orderAttribute));
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
    
}
