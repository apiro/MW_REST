package res;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import res.model.Link;
import res.model.user.User;
import res.model.user.UsersDataService;

public class UsersResource {
	
	private UsersDataService dataService = UsersDataService.getInstance();
	@Context UriInfo uriInfo;
	
	public UsersResource(UriInfo uriInfo){
		this.uriInfo = uriInfo;
	}
	
	@Path("/{id}")
	public UserResource getUserResource(@PathParam("id") String id) {
		return new UserResource(id);
	}
	
	@Path("/search/")
	public UsersFilterResource getRes() {
		System.out.println("ciao");
		
		return new UsersFilterResource(uriInfo);
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUsers() {
		
		System.out.println("GET /users");
		
		List<User> list = new ArrayList<User>(dataService.getUserMap().values());
	
        return Response.ok().entity(new GenericEntity<List<User>>(list) {}).build();
	}
	
	@POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createCustomer(
    		@FormParam("name") String name,
    		@FormParam("address") String address,
    		@FormParam("phoneNumber") String phoneNumber) {
		
		System.out.println("POST /users");
		
		User user = new User(name, address, phoneNumber);
		dataService.addUser(user);
		
		System.out.println(user.toString());
		String uriString = uriInfo.getAbsolutePath().toString() + "/" + user.getId();
		
		Link link_self = new Link();
		link_self.setHref(uriString);
		link_self.setRel("self");
		
		Link link_plays = new Link();
		link_plays.setHref("http://localhost:8080/BroadGamesREST/jaxrs/api/plays/" + user.getId());
		link_plays.setRel("plays");
		
		user.addLink(link_self);
		user.addLink(link_plays);
		URI uri = URI.create(uriString);

        return Response.seeOther(uri).build();
	}
}
