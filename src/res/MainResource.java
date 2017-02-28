package res;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/api")
@Produces("application/json")
public class MainResource {

	@Context UriInfo uriInfo;
	
	@Path("/users/")
	public UsersResource getResource(){
		return new UsersResource(uriInfo);
	}
	
	@Path("/boardGames/")
	public BoardGamesResource getCustomers(){
		return new BoardGamesResource(uriInfo);
	}
	
	@Path("/plays/")
	public PlaysResource getDiaries() {
		return new PlaysResource(uriInfo);
	}
	
	@GET
	@Produces("application/xml")
	public String sayHello() {
		return "<hello>Ciao</hello>";
	}
}