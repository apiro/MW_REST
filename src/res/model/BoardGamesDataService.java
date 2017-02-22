package res.model;

import java.util.ArrayList;
import java.util.List;

public class BoardGamesDataService {
    private List<BoardGame> boardGamesList = new ArrayList<BoardGame>();

    private static BoardGamesDataService ourInstance = new BoardGamesDataService();

    public static BoardGamesDataService getInstance() {
        return ourInstance;
    }

    public String addBoardGame(BoardGame boardGame) {
        String newId = Integer.toString(boardGamesList.size() + 1);
        boardGame.setId(newId);
        boardGamesList.add(boardGame);
        return newId;
    }

    public List<BoardGame> getGamesList() {
        return boardGamesList;
    }


    public BoardGame getGameById(String id) {
        for (BoardGame game : boardGamesList) {
            if (game.getId().equals(id)) {
                return game;
            }
        }
        return null;
    }
    
    public int deleteGame(String id) {
        for (BoardGame game : boardGamesList) {
            if (game.getId().equals(id)) {
                boardGamesList.remove(game);
                return 1;
            }
        }
        return 0;
    }
}
