package res;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import res.model.game.BoardGamesDataService;
import res.model.play.Play;
import res.model.play.PlayDataService;
import res.model.user.UsersDataService;

public class PlaysResource {

	private PlayDataService dataService = PlayDataService.getInstance();
	@Context UriInfo uriInfo;
	
	public PlaysResource(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}
	
	@GET
	@Path("/{userId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getPlays(@PathParam("userId") String userId) {
		
		System.out.println("GET /plays");
		
		List<Play> list = dataService.getPlaysByUser(userId);
	
        return Response.ok().entity(new GenericEntity<List<Play>>(list) {}).build();
	}
	
	@Path("/search/")
	public PlaysFilterResource getRes() {
		return new PlaysFilterResource(uriInfo);
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createPlay(
    		@FormParam("userId") String userId,
    		@FormParam("gameId") String gameId,
    		@FormParam("date") String date,
    		@FormParam("time") String time,
    		@FormParam("players") String players,
    		@FormParam("winnerId") String winnerId) {
		
		System.out.println("POST /play");
		
		DateFormat format = new SimpleDateFormat("dd/MM/yy");
		
		Date dateFormatted;
		try {
			dateFormatted = format.parse(date);
		} catch (ParseException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Wrong date format - use dd/MM/yy").build();
		}
		
		if(!UsersDataService.getInstance().findId(userId)){
			return Response.status(Response.Status.BAD_REQUEST).entity("User id not found!").build();
		}
		if(!BoardGamesDataService.getInstance().findId(gameId)){
			return Response.status(Response.Status.BAD_REQUEST).entity("Game id not found!").build();
		}
		
		Play play = new Play(userId,gameId,dateFormatted);
		
		if(time.trim().length() !=0){
			play.setTime(time);
		}
		if(players.trim().length() !=0){
			play.setNumPlayers(Integer.parseInt(players.trim()));
		}
		if(winnerId.trim().length() != 0){
			if(!UsersDataService.getInstance().findId(winnerId.trim())){
				return Response.status(Response.Status.BAD_REQUEST).entity("winner id not found!").build();
			}
			play.setWinnerId(winnerId.trim());
		}
		
		Link link_player = new Link();
		Link link_game = new Link();
		link_player.setHref("http://localhost:8080/BroadGamesREST/jaxrs/api/boardGames/" + play.getUserId());
		link_player.setRel("player");
		link_game.setHref("http://localhost:8080/BroadGamesREST/jaxrs/api/users/" + play.getGameId());
		link_game.setRel("game");
		
		play.addLink(link_game);
		play.addLink(link_player);

		dataService.addPlay(play);		

        return Response.ok("Play created").build();
    }
	
}
