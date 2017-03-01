package res;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import res.auth.AuthenticationDataService;
import res.model.game.BoardGame;
import res.model.game.BoardGamesDataService;
import res.model.play.Play;
import res.model.play.PlayDataService;
import res.model.user.User;
import res.model.user.UsersDataService;

@Path("/init")
@Produces("application/json")
public class InitResource {

	private UsersDataService users_service = UsersDataService.getInstance();
	private BoardGamesDataService games_service = BoardGamesDataService.getInstance();
	private PlayDataService play_service = PlayDataService.getInstance();
	private AuthenticationDataService auth_service = AuthenticationDataService.getInstance();
	
	private Random random = new Random();
	
	public InitResource() {
	
	}
	
	@GET
	public Response initDataServices() throws ParseException {
		
		users_service.addUser(new User("albi", "rrr", "3409090800"));
		users_service.addUser(new User("jago", "ggg", "3489678967"));
		users_service.addUser(new User("klar", "hhh", "3409090800"));
		users_service.addUser(new User("enn", "ggg", "3409090800"));
		users_service.addUser(new User("fudge", "qqq", "3334532190"));
		users_service.addUser(new User("jimmy", "aaa", "33195432123"));
		users_service.addUser(new User("devon", "xxx", "3563298765"));
		users_service.addUser(new User("trevor", "bbb", "3447865324"));
		users_service.addUser(new User("susy", "ooo", "3116754999"));
		users_service.addUser(new User("tuly", "nnn", "3432128888"));
		users_service.addUser(new User("fede", "yyy", "3558976763"));
		users_service.addUser(new User("maryo", "ooo", "3909988777"));
		users_service.addUser(new User("etta", "lll", "3337788999"));
		users_service.addUser(new User("renz", "ooo", "3773212666"));
		
		auth_service.addUserPassword("albi", "123");
		auth_service.addUserPassword("jago", "123");
		auth_service.addUserPassword("klar", "123");
		auth_service.addUserPassword("enn", "123");
		auth_service.addUserPassword("fudge", "123");
		auth_service.addUserPassword("jimmy", "123");
		auth_service.addUserPassword("trevor", "123");
		auth_service.addUserPassword("susy", "123");
		auth_service.addUserPassword("tuly", "123");
		auth_service.addUserPassword("fede", "123");
		auth_service.addUserPassword("maryo", "123");
		auth_service.addUserPassword("etta", "123");
		auth_service.addUserPassword("renz", "123");
		
		DateFormat format = new SimpleDateFormat("dd/MM/yy");
		
		play_service.addPlay(new Play("4", "1", format.parse(format.format(new Date(random.nextLong()))), 2));
		play_service.addPlay(new Play("1", "1", format.parse(format.format(new Date(random.nextLong()))), 3));
		play_service.addPlay(new Play("2", "1", format.parse(format.format(new Date(random.nextLong()))), 4));
		play_service.addPlay(new Play("2", "1", format.parse(format.format(new Date(random.nextLong()))), 1));
		play_service.addPlay(new Play("5", "1", format.parse(format.format(new Date(random.nextLong()))), 3));
		play_service.addPlay(new Play("3", "1", format.parse(format.format(new Date(random.nextLong()))), 5));
		play_service.addPlay(new Play("4", "2", format.parse(format.format(new Date(random.nextLong()))), 2));
		play_service.addPlay(new Play("4", "2", format.parse(format.format(new Date(random.nextLong()))), 3));
		play_service.addPlay(new Play("1", "2", format.parse(format.format(new Date(random.nextLong()))), 3));
		play_service.addPlay(new Play("3", "2", format.parse(format.format(new Date(random.nextLong()))), 4));
		play_service.addPlay(new Play("5", "2", format.parse(format.format(new Date(random.nextLong()))), 4));
		play_service.addPlay(new Play("6", "2", format.parse(format.format(new Date(random.nextLong()))), 5));
		play_service.addPlay(new Play("7", "2", format.parse(format.format(new Date(random.nextLong()))), 8));
		play_service.addPlay(new Play("3", "2", format.parse(format.format(new Date(random.nextLong()))), 14));
		play_service.addPlay(new Play("8", "3", format.parse(format.format(new Date(random.nextLong()))), 2));
		play_service.addPlay(new Play("2", "3", format.parse(format.format(new Date(random.nextLong()))), 3));
		play_service.addPlay(new Play("9", "3", format.parse(format.format(new Date(random.nextLong()))), 4));
		play_service.addPlay(new Play("2", "3", format.parse(format.format(new Date(random.nextLong()))), 2));
		play_service.addPlay(new Play("6", "3", format.parse(format.format(new Date(random.nextLong()))), 1));
		play_service.addPlay(new Play("1", "3", format.parse(format.format(new Date(random.nextLong()))), 4));
		
		//TODO path that starts from the project root
		File imgPath1 = new File("/Users/albertomariopirovano/Documents/Programming/workspace/BroadGamesREST/resources/frank.png");
		File imgPath2 = new File("/Users/albertomariopirovano/Documents/Programming/workspace/BroadGamesREST/resources/orange.png");
		File imgPath3 = new File("/Users/albertomariopirovano/Documents/Programming/workspace/BroadGamesREST/resources/red.png");

		byte[] fileContent1 = null;
		byte[] fileContent2 = null;
		byte[] fileContent3 = null;
		
		String uri = "http://localhost:8080/BroadGamesREST/jaxrs/api/boardGames/";
		
		try {
			fileContent1 = Files.readAllBytes(imgPath1.toPath());
			fileContent2 = Files.readAllBytes(imgPath2.toPath());
			fileContent3 = Files.readAllBytes(imgPath3.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		games_service.addBoardGame(new BoardGame("San Siro", "atalanta,fiorentina,cesena"), fileContent1, uri);
		games_service.addBoardGame(new BoardGame("San Paolo", "chievo,hellas,udinese"), fileContent2, uri);
		games_service.addBoardGame(new BoardGame("Bentegodi", "inter,milan,roma"), fileContent3, uri);
		
		return Response.ok("Init data creation success").build();
	}
}
