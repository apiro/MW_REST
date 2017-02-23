//NOT USED
package res;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import res.model.FilterResultDataService;
import res.model.user.UserFilterResult;

@Path("/api/query/filterResult/")
public class ResultResource {

	
	private static FilterResultDataService dataServiceResults = FilterResultDataService.getInstance();
	
	@GET
	@Path("{resultId}")
	public Response getResult(@PathParam("resultId")String resultId){
		
		System.out.println(dataServiceResults.getFilterResult(resultId));
		
		//List<User> list = new ArrayList<User>(dataServiceResults.getFilterResult(resultId).getUserMap().values());
		UserFilterResult res = dataServiceResults.getFilterResult(resultId);
		return Response.ok(res).build();
	}
	
}