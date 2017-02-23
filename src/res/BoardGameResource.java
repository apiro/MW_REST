package res;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import res.model.game.BoardGame;
import res.model.game.BoardGamesDataService;
import res.model.user.User;
import res.model.user.UsersDataService;

public class BoardGameResource {

	private String id;
	
	private BoardGamesDataService dataService = BoardGamesDataService.getInstance();

	public BoardGameResource(String id){
		this.id = id;
	}
	
	@GET
    public Response getCustomer() {
		
		System.out.println("GET {id}");

		BoardGame game = dataService.getGameById(id);
		
		if(game == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Game not found").build();
		}
		
        return Response.ok(game, MediaType.APPLICATION_JSON).build();
    }
	
	@DELETE
    public Response deleteCustomer() {
		
		System.out.println("DELETE {id}");
		
		int status = dataService.deleteGame(id);
		
		if(status == 0) {
			return Response.status(Response.Status.NOT_FOUND).entity("Game not found").build();
		}
		
        return Response.status(202).entity("Game deleted successfully").build();
    }
	
	@Path("/cover")
	@GET
	@Produces("image/jpeg")
	public Response getImage(){
		byte[] image = dataService.getImage(id);
		return Response.ok(image).build();
	}
	
	/*@PUT
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
    }*/
}