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
public class BoardGame {

	@XmlElement(name="Designer", required=false)
	private ArrayList<String> designers = new ArrayList<String>();
	
	@XmlAttribute(name="id", required=true)
	private String id;
	
	@XmlAttribute(name="name", required=true)
	private String name;
	
	//TODO JPG missing
	
	public BoardGame() {
	}
	
	public BoardGame(String name, String designers) {
		super();
		this.id = null;
		this.name = name;
		
		String[] d = designers.split(",");
		for(String s: d){
			this.designers.add(s.trim());
		}
	}
	
	public ArrayList<String> getDesigners() {
		return designers;
	}

	public void setDesigners(ArrayList<String> designers) {
		this.designers = designers;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String newId) {
		this.id = newId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}