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
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username, 
                                     @FormParam("password") String password) {

		System.out.println("POST /auth");
		
        try {

            // Authenticate the user using the credentials provided
            authenticate(username, password);
            
            // Issue a token for the user
            String token = issueToken(username);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }      
    }

    private void authenticate(String username, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
    	Boolean flag = authDataService.isRegistered(username, password);
    	if(flag.equals(Boolean.FALSE)) {
    		Exception e = new Exception();
    		throw e;
    	}
    }

    private String issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
    	return authDataService.addUserToken(username);
    }
}
