package res;

import java.net.URI;
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

import res.model.BoardGame;
import res.model.BoardGamesDataService;

public class BoardGamesResource {
	
	private BoardGamesDataService dataService = BoardGamesDataService.getInstance();
	@Context UriInfo uriInfo;
	
	public BoardGamesResource(UriInfo uriInfo){
		this.uriInfo = uriInfo;
	}
	
	@Path("/{id}")
	public BoardGameResource getUserResource(@PathParam("id") String id) {
		return new BoardGameResource(id);
	}
	
	/*@Path("/search/")
	public UsersFilterResource getRes() {
		System.out.println("ciao");
		
		return new UsersFilterResource(uriInfo);
	}*/

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGames() {
		
		System.out.println("GET /boardGames");
		
		List<BoardGame> list = dataService.getGamesList();
	
        return Response.ok().entity(new GenericEntity<List<BoardGame>>(list) {}).build();
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createCustomer(@FormParam("name") String name){
		
		System.out.println("POST /users");
		
		BoardGame game = new BoardGame(name);
		dataService.addBoardGame(game);
		
		System.out.println(game.getName());
		String uriString = uriInfo.getAbsolutePath().toString() + game.getId();
		URI uri = URI.create(uriString);

        //return Response.created(uri).build();
		return Response.ok(uriString).build();
    }
}
