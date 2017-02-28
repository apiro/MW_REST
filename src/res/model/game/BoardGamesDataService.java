package res.model.game;

import java.util.ArrayList;
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
    
    public List<BoardGame> filter(HashMap<String, String> params) {
        return bGames.filterGames(params);
    }
    
    public byte[] getImage(String id){
    	return bGames.getGameCovers().get(id);
    }
    
    public boolean findId(String id){
    	return bGames.getGames().containsKey(id);
    }
}
