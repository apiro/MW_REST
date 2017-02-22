/*package res;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import res.model.FilterResult;
import res.model.FilterResultDataService;

@Path("/api/query/filterResult/")
public class ResultResource {

	
	private static FilterResultDataService dataServiceResults = FilterResultDataService.getInstance();
	
	@GET
	@Path("{resultId}")
	public Response getResult(@PathParam("resultId")String resultId){
		
		System.out.println(dataServiceResults.getFilterResult(resultId));
		
		//List<User> list = new ArrayList<User>(dataServiceResults.getFilterResult(resultId).getUserMap().values());
		FilterResult res = dataServiceResults.getFilterResult(resultId);
		return Response.ok(res).build();
	}
	
}*/