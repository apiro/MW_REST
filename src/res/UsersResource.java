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

import res.auth.AuthenticationDataService;
import res.model.user.User;
import res.model.user.UsersDataService;

public class UsersResource {
	
	private UsersDataService dataService = UsersDataService.getInstance();
	private AuthenticationDataService authDataService = AuthenticationDataService.getInstance();
	
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
    public Response createUser(
    		@FormParam("name") String name,
    		@FormParam("address") String address,
    		@FormParam("phoneNumber") String phoneNumber,
    		@FormParam("password") String password) {
		
		System.out.println("POST /users");
		
		User user = new User(name, address, phoneNumber);
		String returnVal = dataService.addUser(user);
		
		if(returnVal.equals("error")) {
			return Response.status(Response.Status.CONFLICT).build();
		}
	
		authDataService.addUserPassword(name, password);
		
		System.out.println(user.toString());
		String uriString = uriInfo.getAbsolutePath().toString() + "/" + user.getId();
		
		URI uri = URI.create(uriString);

        return Response.seeOther(uri).build();
	}
}
