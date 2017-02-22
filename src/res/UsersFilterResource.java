package res;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import res.model.UserFilterResult;
import res.model.FilterResultDataService;
import res.model.User;
import res.model.UsersDataService;


public class UsersFilterResource {

	@Context UriInfo uriInfo;
	
	private UsersDataService dataService = UsersDataService.getInstance();
	
	private HashMap<String, String> params = new HashMap<String, String>();
	
	private static FilterResultDataService dataServiceResults = new FilterResultDataService();
	
	public UsersFilterResource(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response filter(MultivaluedMap<String, String> formParams) throws URISyntaxException {
		
		System.out.println("> POST /users/filter");
		
		setFilters(formParams);

		HashMap<String, User> results = dataService.filter(params);
		
		if(results.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).entity(new User()).build();
		}
	
		UserFilterResult filterResults = new UserFilterResult();
		filterResults.setUserList(new ArrayList<User>(results.values()));

		String idResult = dataServiceResults.addFilterResult(filterResults);
		
		//java.net.URI location = new java.net.URI("http://localhost:8080/BroadGamesREST/jaxrs/api/query/filterResult/" + idResult.toString());
		//System.out.println("> location: " + location);
		
		Iterator<User> it = dataServiceResults.getFilterResult(idResult).getUsers().iterator();
		
		while(it.hasNext()) {
			System.out.println("> entry selected: " + it.next());
		}
		
		//List<User> list = new ArrayList<User>(dataServiceResults.getFilterResult(idResult).getUserMap().values());
		UserFilterResult res = dataServiceResults.getFilterResult(idResult);
		return Response.ok(res).build();
		//return Response.ok().entity(new GenericEntity<List<User>>(list) {}).build();
				//temporaryRedirect(location).build();
	}
	
	public void setFilters(MultivaluedMap<String, String> formParams){
		
		Iterator<String> it = formParams.keySet().iterator();
		
		while(it.hasNext()) {
			String theKey = (String)it.next();
			params.put(theKey, formParams.getFirst(theKey));
		}	
	}
}