package res.model.game;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="FilterResult")
public class BoardGamesFilterResult {

	@XmlElement(name="Games", required=false)
	private List<BoardGame>games;
	
	public BoardGamesFilterResult(){
	}
	
	public void setGamesList(List<BoardGame> games) {
		this.games = games;
	}
	
	public List<BoardGame> getGames() {
		return games;
	}
}