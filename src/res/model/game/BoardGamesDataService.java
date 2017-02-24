package res.model.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        	boardGame.setCoverArt(uri + newId + "/cover");
            bGames.getGameCovers().put(newId, image);
        }
        
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
