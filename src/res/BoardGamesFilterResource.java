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

public class BoardGamesFilterResource {

	@Context UriInfo uriInfo;
	
	private BoardGamesDataService dataService = BoardGamesDataService.getInstance();
	
	private HashMap<String, String> params = new HashMap<String, String>();
	
	public BoardGamesFilterResource(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response filter(MultivaluedMap<String, String> formParams) throws URISyntaxException {
		
		System.out.println("> POST /boardGames/filter");
		
		setFilters(formParams);

		List<BoardGame> results = dataService.filter(params);
		
		if(results.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).entity(new BoardGame()).build();
		}
		
		BoardGamesFilterResult res = new BoardGamesFilterResult();
		res.setGamesList(new ArrayList<BoardGame>(results));
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