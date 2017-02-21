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

    public List<BoardGame> getDiariesList() {
        return boardGamesList;
    }


    public BoardGame getCustomerById(String id) {
        for (BoardGame diary : boardGamesList) {
            if (diary.getId().equals(id)) {
                return diary;
            }
        }
        return null;
    }
}
