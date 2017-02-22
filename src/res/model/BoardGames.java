package res.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="BoardGameType")
public class BoardGames {

	@XmlElement(name="Game", required=false)
	private ArrayList<BoardGame> boardGames = new ArrayList<BoardGame>();

	public ArrayList<BoardGame> getDiaries() {
		return boardGames;
	}

	public void setDiaries(ArrayList<BoardGame> diaries) {
		this.boardGames = diaries;
	}
	
}

