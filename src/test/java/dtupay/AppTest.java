package dtupay;
// maven junit test
//import junit.framework.Test; 
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
// ex 1.7 mvn test REST API server source code, junit(original package,org.junit.Test) test without maven junit.framework.* package
import org.junit.Test;                          //junit test 4.13.2 from junit
import static org.junit.Assert.assertNotEquals; //
//import org.springframework.boot.test.context.SpringBootTest; //@SpringBootApplication @SpringBootTest annotation to mark under test java class and test class
// look for entry point main function in java class
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
//@SpringBootTest
public class AppTest {
    private App newApp; // maven figure out the import of class package "magically"
    HttpClient newClient = HttpClient.newHttpClient();
    @Test
    public void pClassDependency() throws Exception {
        HttpRequest newRequest = HttpRequest.newBuilder().uri(new URI("http://localhost:8080/token?customerId=123&noToken=2")).GET().build();
        newClient.sendAsync(newRequest, HttpResponse.BodyHandlers.ofString());
    }
    @Test
    public void pPostToken(){
        
    }
}