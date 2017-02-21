package res;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import res.model.FilterResultDataService;
import res.model.User;

@Path("/api/users/filter/")
public class ResultResource {

	
	private static FilterResultDataService dataServiceResults = FilterResultDataService.getInstance();
	
	@GET
	@Path("{resultId}")
	public Response getResult(@PathParam("resultId")String resultId){
		
		List<User> list = new ArrayList<User>(dataServiceResults.getFilterResult(Integer.parseInt(resultId)).getUserMap().values());
		
		return Response.ok().entity(new GenericEntity<List<User>>(list) {}).build();
	}
	
}