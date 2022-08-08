package controller;
import type.Service;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
// java ee 7
// import java.io.IOException;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import org.json.JSONObject;

public class CustomerService extends HttpServlet implements IService{

    // REST GET TOKEN : request token
    @GET
	@Produces("text/plain")
		public String getClichedMessage() {
			return "Hello World";
	}

    // REST PUT Order : use token 
    // if User is NewCustomer then generate an customer uniqueID and add to the Order data set in the database.
    // if User is reigistered customer then use the customers name, id together with the id 
    // @Override
    // public void doGet(HttpServletRequest request, HttpServleteResponse response) throws IOException, ServletException {
    //     String requestUrl = request.getRequestURI(); //Returns the part of this request's URL from the protocol name up to the query string in the first line of the HTTP request.
    //     String customerId = request.substring("/customer/".length());
    // }
    // REST API DELETE ORDER : use token

}
