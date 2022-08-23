package dtupay;
// maven junit test belonging packages
// import junit.framework.Test; 
// import junit.framework.TestCase;
// import junit.framework.TestSuite;
// ex 1.7 mvn test server REST API controller class constructor
// junit test 4.13.2 from junit
// import org.junit.Test;                          
import static org.junit.Assert.assertNotEquals; //
// try to use junit assert instead of assertj
import static org.junit.Assert.assertEquals;

// spring-boot-starter-test in pom
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


//java class
import java.net.URI;
import org.springframework.http.HttpHeaders;    //spring HttpHeaders
import org.springframework.boot.test.web.client.TestRestTemplate;   //spring HttpClient
import org.springframework.http.HttpEntity; // spring HttpEntity
import org.springframework.http.MediaType; // spring MediaType extends org.springframework.util.MimeType

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppTest {
    
    @LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

    // spring function getForObject
    @Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("Hello World");
	}
    

    // @Test
	// public void pPostTokenCorectness() throws Exception {
    //     // spring function postForObject
    //     // URI
    //     URI theUri = URI.create("http://localhost:" + port);
    //     // HttpHeaders class
    //     HttpHeaders headers = new HttpHeaders();
	//     headers.setContentType(MediaType.APPLICATION_JSON);
    //     // URI class
    //     // <HomeMade> class
    //     CustomerOrder newCustomerOrder = new CustomerOrder("123","2");
    //     // HttpEntity 
    //     HttpEntity<CustomerOrder> entityCustomerOrder = new HttpEntity<>(newCustomerOrder, headers);
    //     // postForObject(<URI>,<HttpEntity>,<TheReturnClass>)
    //     CustomerOrderResponse newCustomerOrderResponse = this.restTemplate.postForObject((theUri+"/customer_token"),entityCustomerOrder, CustomerOrderResponse.class);
    //     // getForObject(<URI>,<TheReturnClass>)
    //     CustomerOrderResponse oldCustomerOrderResponse = this.restTemplate.getForObject((theUri+"/customer_token?newCustomerId=123"),CustomerOrderResponse.class);
    //     assertEquals(newCustomerOrderResponse.getCustomerId(),oldCustomerOrderResponse.getCustomerId());
    //     assertEquals(newCustomerOrderResponse.getTokens(),oldCustomerOrderResponse.getTokens());
	// }
}