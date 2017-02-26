package res.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class Links {

	@XmlElement(name="link")
	private ArrayList<Link> links = new ArrayList<Link>();
	
	public Links(){}
	
	public void addLink(Link linkToBeAdded) {
		links.add(linkToBeAdded);
	}
	
}
