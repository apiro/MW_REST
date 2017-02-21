package res.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BoardGames {

	private ArrayList<BoardGame> boardGames = new ArrayList<BoardGame>();

	public ArrayList<BoardGame> getDiaries() {
		return boardGames;
	}

	public void setDiaries(ArrayList<BoardGame> diaries) {
		this.boardGames = diaries;
	}
	
}

