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

import res.model.FilterResultDataService;
import res.model.user.User;
import res.model.user.UserFilterResult;
import res.model.user.UsersDataService;


public class UsersFilterResource {

	@Context UriInfo uriInfo;
	
	private UsersDataService dataService = UsersDataService.getInstance();
	
	private HashMap<String, String> params = new HashMap<String, String>();
	
	public UsersFilterResource(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response filter(MultivaluedMap<String, String> formParams) throws URISyntaxException {
		
		System.out.println("> POST /users/filter");
		
		setFilters(formParams);

		List<User> results = dataService.filter(params);
		
		if(results.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).entity(new User()).build();
		}
	
		UserFilterResult filterResults = new UserFilterResult();
		filterResults.setUserList(results);

		Iterator<User> it = results.iterator();
		
		while(it.hasNext()) {
			System.out.println("> entry selected: " + it.next());
		}
		
		return Response.ok(filterResults).build();
	}
	
	public void setFilters(MultivaluedMap<String, String> formParams){
		
		Iterator<String> it = formParams.keySet().iterator();
		
		while(it.hasNext()) {
			String theKey = (String)it.next();
			params.put(theKey, formParams.getFirst(theKey));
		}	
	}
}