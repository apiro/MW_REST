package res.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BoardGame {

	private ArrayList<Designer> designers = new ArrayList<Designer>();

	private String id;
	
	private String name;
	
	public ArrayList<Designer> getDesigners() {
		return designers;
	}

	public void setDesigners(ArrayList<Designer> designers) {
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