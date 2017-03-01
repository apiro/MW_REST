package res.model.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import res.model.Link;

public class BoardGamesDataService {
	
	private BoardGames bGames = new BoardGames();

    private static BoardGamesDataService ourInstance = new BoardGamesDataService();

    public static BoardGamesDataService getInstance() {
        return ourInstance;
    }
    
    public String addBoardGame(BoardGame boardGame, byte[] image, String uri) {
    	System.out.println(">[BoardGamesDataService] Game added: " + boardGame.getName());
    	
        String newId = Integer.toString(bGames.getGames().size() + 1);
        boardGame.setId(newId);
        
        if(image.length!=0){
        	Link link_cover = new Link();
        	link_cover.setHref(uri + newId + "/cover");
        	link_cover.setRel("cover");
        	
        	boardGame.addLink(link_cover);
            bGames.getGameCovers().put(newId, image);
        }
        
        Link link_self = new Link();
		link_self.setHref(uri + boardGame.getId());
		link_self.setRel("self");
		
		boardGame.addLink(link_self);
        
        bGames.getGames().put(newId,boardGame);
        
        return newId;
    }

    public List<BoardGame> getGamesList() {
        return new ArrayList<BoardGame>(bGames.getGames().values());
    }


    public BoardGame getGameById(String id) {
        return bGames.getGames().get(id);
    }
    
    public int deleteGame(String id) {
        if(bGames.getGames().remove(id) == null)
        	return 0;
        return 1;
    }
    
    public List<BoardGame> filter(HashMap<String, String> params, final String orderAttribute, final Boolean descending) {
    	
    	if(orderAttribute == null) {
    		return bGames.filterGames(params);
    	}
    	
    	List<BoardGame> filteredGameList = bGames.filterGames(params);
    	
    	orderOneField(orderAttribute, descending, filteredGameList);
    	
        return filteredGameList;
    }
    
    private void orderOneField(final String orderAttribute, final boolean descending, List<BoardGame> filteredGameList) {
		
		Collections.sort(filteredGameList, new Comparator<BoardGame>() {
    	
			@Override
			public int compare(BoardGame g1, BoardGame g2) {
				
				int comparedResult;
				
				if (orderAttribute.equals("id")) {
					Integer int1 = Integer.decode(g1.getAttribute(orderAttribute));
					Integer int2 = Integer.decode(g2.getAttribute(orderAttribute));
					comparedResult = int1.compareTo(int2);
				} else {
					comparedResult = g1.getAttribute(orderAttribute).compareToIgnoreCase(g2.getAttribute(orderAttribute));
				}
				// comparedResult is negative if o1 attribute precedes o2 attribute
				
				if(descending == true) {
					return comparedResult;
				} else {
					return -comparedResult;
				}
			}
        });
	}
    
    public byte[] getImage(String id){
    	return bGames.getGameCovers().get(id);
    }
    
    public boolean findId(String id){
    	return bGames.getGames().containsKey(id);
    }
}
