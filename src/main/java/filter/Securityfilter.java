//package filter;
//
//import java.io.IOException;
//import java.util.List;
//
//import com.sun.jersey.spi.container.ContainerRequest;
//import com.sun.jersey.spi.container.ContainerRequestFilter;
//
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.Provider;
//
//import service.LoginService;
//import service.LoginServiceImpl;
//import javax.ws.rs.core.Response.Status;
//
//@Provider
//public class Securityfilter implements ContainerRequestFilter {
//	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
//	private static final String SECURED_URL_PREFIX = "Validate ";
//	LoginService loginService = new LoginServiceImpl();
//	
////	@Override
////	public void filter(ContainerRequestContext requestContext) throws IOException {
////
////		// TODO Auto-generated method stub
////		if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
////			List<String>authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
////			if (authHeader != null && authHeader.size()> 0) {
////				String authToken = authHeader.get(0);
////				if(loginService.isAuthenticated(authToken)) {
////					return;
////				}else {
////					Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access this resource").build();
////					requestContext.abortWith(unauthorizedStatus);
////				}
////			}
////			Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access this resource").build();
////			requestContext.abortWith(unauthorizedStatus);
////		}
////	}
//
//	@Override
//	public ContainerRequest filter(ContainerRequest containerRequest)throws WebApplicationException {
//		// TODO Auto-generated method stub
//			String authHeader = containerRequest.getHeaderValue(AUTHORIZATION_HEADER_KEY);
//	
//				String authToken = authHeader;
//				if(loginService.isAuthenticated(authToken)) {
//					return containerRequest;
//				}else {
//					throw new WebApplicationException(Status.UNAUTHORIZED);
//				}
//	}
//
//}
