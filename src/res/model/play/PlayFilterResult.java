package res.model.play;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="FilterResult")
public class PlayFilterResult {

	@XmlElement(name="Plays", required=false)
	private List<Play>plays;
	
	public PlayFilterResult(){
	}
	
	public void setPlaysList(List<Play> plays) {
		this.plays = plays;
	}
	
	public List<Play> getPlays() {
		return plays;
	}
}