package res;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
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

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import res.model.game.BoardGame;
import res.model.game.BoardGamesDataService;

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
	
	@Path("/search/")
	public BoardGamesFilterResource getRes() {
		return new BoardGamesFilterResource(uriInfo);
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGames() {
		
		System.out.println("GET /boardGames");
		
		List<BoardGame> list = dataService.getGamesList();
	
        return Response.ok().entity(new GenericEntity<List<BoardGame>>(list) {}).build();
	}
	
	@POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createBoardGame(
    		@FormDataParam("name") String name,
    		@FormDataParam("designers") String designers,
    		@FormDataParam("file") InputStream fileStream, 
			@FormDataParam("file") FormDataContentDisposition fileDisposition){
		
		System.out.println("POST /users");
		
		//Get image file from POST request
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		byte[] actualDataToStore = new byte[16384];
		int nBytesRead;
		
		try {
			while ((nBytesRead = fileStream.read(actualDataToStore, 0, actualDataToStore.length)) != -1) {
				buffer.write(actualDataToStore, 0, nBytesRead);
			}
			buffer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		BoardGame game = new BoardGame(name, designers);
		String id = dataService.addBoardGame(game, buffer.toByteArray(), uriInfo.getAbsolutePath().toString());
		
		System.out.println(game.getName());
		String uriString = uriInfo.getAbsolutePath().toString() + id;
		URI uri = URI.create(uriString);

        //return Response.created(uri).build();
		return Response.ok(uriString).build();
    }
}
