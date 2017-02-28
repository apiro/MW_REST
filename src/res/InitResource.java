package res;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import res.model.game.BoardGamesDataService;
import res.model.play.PlayDataService;
import res.model.user.User;
import res.model.user.UsersDataService;

@Path("/init")
@Produces("application/json")
public class InitResource {

	UsersDataService users_service = UsersDataService.getInstance();
	BoardGamesDataService games_service = BoardGamesDataService.getInstance();
	PlayDataService play_service = PlayDataService.getInstance();
	
	public InitResource() {
	
	}
	
	@GET
	public Response initDataServices() {
		
		users_service.addUser(new User("albi", "rrr", "grgr"));
		users_service.addUser(new User("jago", "ggg", "ffffd"));
		users_service.addUser(new User("klar", "hhh", "gfgfdsg"));
		users_service.addUser(new User("enn", "ggg", "jjjjj"));
		users_service.addUser(new User("fudge", "qqq", "pppp"));
		users_service.addUser(new User("jimmy", "aaa", "pppp"));
		users_service.addUser(new User("devon", "xxx", "xxxx"));
		users_service.addUser(new User("trevor", "bbb", "zzzz"));
		users_service.addUser(new User("susy", "ooo", "pppp"));
		users_service.addUser(new User("tuly", "nnn", "cccc"));
		users_service.addUser(new User("fede", "yyy", "wwww"));
		users_service.addUser(new User("maryo", "ooo", "pppp"));
		users_service.addUser(new User("etta", "lll", "eeee"));
		users_service.addUser(new User("renz", "ooo", "pppp"));
		
		List<User> list = new ArrayList<User>(users_service.getUserMap().values());
		
		return Response.ok().entity(new GenericEntity<List<User>>(list) {}).build();
	}
}
