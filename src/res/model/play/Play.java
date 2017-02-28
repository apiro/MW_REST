package res.model.play;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import res.model.Link;
import res.model.Links;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="PlayType")
public class Play {

	@XmlAttribute(name="userId", required=true)
	private String userId;
	
	@XmlAttribute(name="gameId", required=true)
	private String gameId;
	
	@XmlAttribute(name="date", required=true)
	private Date date;
	
	@XmlAttribute(name="duration", required=true)
	private String time;
	@XmlAttribute(name="numPlayers", required=true)
	private int numPlayers;
	@XmlAttribute(name="winnerId", required=true)
	private String winnerId;
	
	@XmlElement(name="links", required=false)
	private Links links;
	
	public void setLinks(Links links) {
		this.links = links;
	}
	
	public void addLink(Link link) {
		links.addLink(link);
	}
	
	public Play() {
	}
	
	public Play(String userId, String gameId, Date date) {
		super();
		this.setLinks(new Links());
		this.userId = userId;
		this.gameId = gameId;
		this.date = date;
		this.time = null;
		this.numPlayers = 1;
		this.winnerId = null;
	}
	
	public Play(String userId, String gameId, Date date, int numPlayers) {
		super();
		this.setLinks(new Links());
		this.userId = userId;
		this.gameId = gameId;
		this.date = date;
		this.time = null;
		this.numPlayers = numPlayers;
		this.winnerId = null;
	}

	public String getUserId() {
		return userId;
	}

	public String getGameId() {
		return gameId;
	}

	public Date getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public String getWinnerId() {
		return winnerId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTime(String time){
		this.time = time;
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public void setWinnerId(String winnerId) {
		this.winnerId = winnerId;
	}

	public String getAttribute(String attr) {
		
		if (attr.equals("userId")) {
			return this.getUserId();
		} else if (attr.equals("gameId")) {
			return this.getGameId();
		} else if (attr.equals("date")) {
			DateFormat format = new SimpleDateFormat("dd/MM/yy");
			return format.format(this.getDate());
		} else if (attr.equals("duration")) {
			return this.getTime();
		} else if (attr.equals("numPlayers")) {
			return Integer.toString(this.getNumPlayers());
		} else if (attr.equals("winnerId")) {
			return this.getWinnerId();
		}
		return "null";
	}
}