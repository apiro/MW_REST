package res;

import java.net.URISyntaxException;
import java.util.ArrayList;
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

import res.model.game.BoardGame;
import res.model.game.BoardGamesDataService;
import res.model.game.BoardGamesFilterResult;
import res.model.play.Play;
import res.model.play.PlayDataService;
import res.model.play.PlayFilterResult;

public class PlaysFilterResource {

	@Context UriInfo uriInfo;
	
	private PlayDataService dataService = PlayDataService.getInstance();
	
	private HashMap<String, String> params = new HashMap<String, String>();
	
	public PlaysFilterResource(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response filter(MultivaluedMap<String, String> formParams) throws URISyntaxException {
		
		System.out.println("> POST /plays/filter");
		
		setFilters(formParams);

		List<Play> results = dataService.filter(params);
		
		if(results.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).entity(new Play()).build();
		}
		
		Iterator<Play> it = results.iterator();
		while(it.hasNext()) {
			System.out.println("> entry selected: " + it.next());
		}
		
		PlayFilterResult res = new PlayFilterResult();
		res.setPlaysList(results);
		return Response.ok(res).build();
	}
	
	public void setFilters(MultivaluedMap<String, String> formParams){
		
		Iterator<String> it = formParams.keySet().iterator();
		
		while(it.hasNext()) {
			String theKey = (String)it.next();
			params.put(theKey, formParams.getFirst(theKey));
		}	
	}
}