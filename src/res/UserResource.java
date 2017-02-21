package res;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import res.model.UsersDataService;
import res.model.User;

public class UserResource {

	private String id;
	
	private UsersDataService dataService = UsersDataService.getInstance();

	public UserResource(String id){
		this.id = id;
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer() {
		
		System.out.println("GET {id}");

		User user = dataService.getUserById(id);
		
		if(user == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
		}
		
        return Response.ok(dataService.getUserById(id), MediaType.APPLICATION_JSON).build();
    }
	
	@DELETE
    public Response deleteCustomer() {
		
		System.out.println("DELETE {id}");
		
		int status = dataService.removeUser(id);
		
		if(status == 0) {
			return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
		}
		
        return Response.status(202).entity("User deleted successfully").build();
    }
	
	@PUT
    public Response updateCustomer(
    		@FormParam("name") String name,
    		@FormParam("address") String address,
    		@FormParam("phoneNumber") String phoneNumber) {
		
		System.out.println("PUT {id}");
		
		User user = new User(name, address, phoneNumber);
		user.setId(id);
		int status = dataService.updateUser(user);
		
		if(status == 0) {
			return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
		}
		
		return Response.status(202).entity("User updated successfully").build();
    }
}