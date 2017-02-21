package res;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import res.model.FilterResult;
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
    @Produces(MediaType.TEXT_PLAIN)
	public Response filter(MultivaluedMap formParams) throws URISyntaxException {
		
		System.out.println("> POST /users/filter");
		
		setFilters(formParams);

		HashMap<String, User> results = dataService.filter(params);
	
		FilterResult filterResults = new FilterResult();
		filterResults.setUserMap(results);
		
		Integer idResult = dataServiceResults.addFilterResult(filterResults);
		
		java.net.URI location = new java.net.URI(uriInfo.getAbsolutePath().toString() + "/" + idResult.toString());
		
		System.out.println("> location: " + location);
		
		for(Entry e: results.entrySet()) {
			System.out.println("> entry selected: " + e.getValue().toString());
		}
		
		return Response.temporaryRedirect(location).build();
	}
	
	public void setFilters(MultivaluedMap<String, String> formParams){
		
		for(Entry e:formParams.entrySet()) {
			params.put(e.getKey().toString(), e.getValue().toString());
		}
	}
}