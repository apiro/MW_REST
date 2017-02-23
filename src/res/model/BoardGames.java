package res.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="BoardGameType")
public class BoardGames {

	@XmlElement(name="Game", required=false)
	private HashMap<String,BoardGame> boardGames = new HashMap<String,BoardGame>();
	private HashMap<String,byte[]> gameCovers = new HashMap<String,byte[]>();

	public HashMap<String,BoardGame> getGames() {
		return boardGames;
	}

	public void setGames(HashMap<String,BoardGame> games) {
		this.boardGames = games;
	}
	
	public HashMap<String,byte[]> getGameCovers() {
		return gameCovers;
	}

	public HashMap<String, BoardGame> filterGames(HashMap<String, String> params) {
		
		HashMap<String, BoardGame> games = new HashMap<String, BoardGame>();

		String name = params.get("name") != null ? params.get("name") : "";
		String id = params.get("id") != null ? params.get("id") : "";
		String designers = params.get("designers") != null ? params.get("designers") : "";
		
		List<String> des= new ArrayList<String>();
		for(String p : designers.split(","))
			des.add(p.trim());

		for (BoardGame g : boardGames.values()){
			if((name.trim().length() == 0 || name.equals(g.getName())) && 
				((id.trim().length() == 0 || id.equals(g.getId()))) && 
				((designers.trim().length() == 0 || g.getDesigners().containsAll(des)))){
				games.put(g.getId(), g);				
			}
		}
		
		return games;
	}

}

