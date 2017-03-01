package res;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import res.auth.AuthenticationDataService;

@Path("/auth")
public class AuthenticationResource {
	
	private AuthenticationDataService authDataService = AuthenticationDataService.getInstance();

	@POST
	@Produces({MediaType.TEXT_HTML})
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username, 
                                     @FormParam("password") String password) {

		System.out.println("POST /auth");
		
		if(Boolean.TRUE.equals(authDataService.isRegistered(username, password))) {
			// Issue a token for the user
            String token = authDataService.addUserToken(username);

            String htmlToBeReturned = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
            		+ "<title>Power User Page</title>"
            		+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">"
            		+ "</head><body><h1 class=\"text-center\">Power User Personal Page</h1>"
            		+ "<div class=\"container\" style=\"border:1px solid #cecece;\"><br><h3>Your Token</h3><p class=\"text-success\">" + token + "</p>"
            		+ "<div class=\"container btn-toolbar\"><a class=\"btn btn-primary btn-sm\" href=\"http://localhost:8080/BroadGamesREST/game.html\" >Games</a><a class=\"btn btn-primary btn-sm\" href=\"http://localhost:8080/BroadGamesREST/play.html\" >Plays</a></div><br></div>"
            		+ "</body></html>";
            
            return Response.ok(htmlToBeReturned).build();
            
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
    }
}
