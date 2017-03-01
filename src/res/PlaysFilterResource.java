package res;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import res.model.play.Play;
import res.model.play.PlayDataService;
import res.model.play.PlayFilterResult;

public class PlaysFilterResource {

	@Context UriInfo uriInfo;
	
	private PlayDataService dataService = PlayDataService.getInstance();
	
	private HashMap<String, String> params = new HashMap<String, String>();
	
	private Boolean descending;
	
	private String orderAttribute;
	
	public PlaysFilterResource(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response filter(MultivaluedMap<String, String> formParams) throws URISyntaxException {
		
		System.out.println("> POST /plays/search");
		
		setFilters(formParams);

		List<Play> results = dataService.filter(params, orderAttribute, descending);
		
		if(results.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).entity(new Play()).build();
		}
		
		PlayFilterResult filterResults = new PlayFilterResult();
		filterResults.setPlaysList(results);
		return Response.ok(filterResults).build();
	}
	
	public void setFilters(MultivaluedMap<String, String> formParams){
		
		Iterator<String> it = formParams.keySet().iterator();
		
		while(it.hasNext()) {
			String theKey = (String)it.next();
			if(theKey.equals("orderAttribute")) {
				orderAttribute = formParams.getFirst("orderAttribute");
			} else if (theKey.equals("descending")) {
				descending = Boolean.valueOf(formParams.getFirst(theKey));
			}{
				params.put(theKey, formParams.getFirst(theKey));
			}
		}	
	}
}