package dtupay;
// ex1.1 hello world
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;
// ex1.2 Greeting with @QueryParam
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// ex1.3 Java QR code example
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

// ex1.5 java uniquie ID example
import java.util.UUID;  

// ex1.6 more complicated POJO to JSON example, supported by Jackson
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
// REST API, spring framework, MVC controller
@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class App {
	@GetMapping("/")
    String home() {
        return "Hello World!";
    }
	@PostMapping("/simplePostFromServer") 
	String postWelcomMessage(@RequestBody String name){
		return "Welcome, " + name + " to the server.";
	}
	private static CustomerOrderMap customerOrderMap = new CustomerOrderMap(new HashMap<String,ArrayList<String>>());
	@GetMapping("/token")
	CustomerOrderResponse checkOrder(@RequestParam String newCustomerId) {
		CustomerOrderResponse newCustomerOrderResposnse = null;
		try {
			if (customerOrderMap.database.containsKey(newCustomerId)) {
				newCustomerOrderResposnse = new CustomerOrderResponse(newCustomerId,customerOrderMap.database.get(newCustomerId));
			} else {
				throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
			}
		} catch (Exception error) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return newCustomerOrderResposnse;
	}
	// e.g. /token?customerId={ID}&noToken={TOKEN}
	//@RequestMapping(value = "/token", method = RequestMethod.POST)
	@PostMapping("/token")
	// @RequestParam is for HTTP GET method
	CustomerOrderResponse makeOrder(@RequestBody CustomerOrder newCustomerOrder) {
		CustomerOrderResponse newCustomerOrderResposnse = null; 
		try {
			int numberOfToken = newCustomerOrder.getNoToken();
			String newCustomerId = newCustomerOrder.getCustomerId();
			// Default value test
			// CustomerOrder newCustomerOrder = new CustomerOrder(customerId, new ArrayList<String>() {{
			// 	add(UUID.randomUUID().toString());
			// }});
			// Test with logic			
			if (numberOfToken < 6 && numberOfToken >=1) {
				if ((customerOrderMap.database.containsKey(newCustomerId) && customerOrderMap.database.get(newCustomerId)==null) || !(customerOrderMap.database.containsKey(newCustomerId))) {
					ArrayList<String> tokenList = new ArrayList<String>() {{
						for (int i=0; i<numberOfToken; i++){  
							add(UUID.randomUUID().toString());
						}
					}};
					customerOrderMap.database.put(newCustomerId,tokenList);
					newCustomerOrderResposnse = new CustomerOrderResponse(newCustomerId,tokenList);
				} else {
					// spring HttpStatus https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html
					throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
				}
			} else {
				throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
			}
		} catch (Exception error) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return newCustomerOrderResposnse;
	}
	
    public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
    }

}

// Interface
interface IToken {
	public void fCreateQR()throws WriterException, IOException;
}
// Abstract class
abstract class Token implements IToken {
}
// QRCode class, later could be seperated to other java file if manual test passes.
// Test passed, can be used in client device for generating QRcode the data recieved from server.
class QRCode extends Token{
    private String dataEncode;
    private String pathPhoto;
    private String formatEncode;
    private Map<EncodeHintType,ErrorCorrectionLevel> mapEncode;
	private int heightQR;
	private int widthQR;

    public QRCode(String data, String path, String format, Map<EncodeHintType,ErrorCorrectionLevel> hashMap, int height, int width){
		dataEncode = data;	// Server can generate the data by customer id e.g cpr, if customer id is not registered in the database of customer table, then server predicate the user is new customer. New customer can have 5 tokens for the first request, then server register the new customer into the customer table.
		pathPhoto = path;
		formatEncode = format;
		mapEncode = hashMap;
		heightQR = height;	
		widthQR = width;	
    }
	public String getTokenId() {
		return dataEncode;
	}
	// Override super class and interface, Function to create QRcode and photo
	@Override
	public void fCreateQR() throws WriterException, IOException {
		try {
			BitMatrix bitMatrix = 
			new MultiFormatWriter().encode(
				new String(dataEncode.getBytes(formatEncode), formatEncode),
				BarcodeFormat.QR_CODE,
				heightQR,
				widthQR
				);
			MatrixToImageWriter.writeToFile(
				bitMatrix,
				pathPhoto.substring(pathPhoto.lastIndexOf('.')+1),
				new File(pathPhoto)
			);
			System.out.println("QRCode stored in the path.");
		} catch (Exception error){
			System.out.println("Fail to create QR photo");
		}
	}
}

// ex1.6 http POST /token?customerId={}&&no={} response class // client side class
class CustomerOrder {
	// <anonymous java.util.HashMap<java.lang.String,java.util.ArrayList<java.lang.String>>> cannot be converted to java.util.Map<java.lang.String,java.util.List<java.lang.String>>
	// List<String> string1 = new ArrayList<Sting>(), this example would compile 
	// and also constructor injection such as 
	// class RandomClass { private List<String> newList; public RandomClass(ArrayList<String> list) {newList = list;}}
	// RandomClass randomclass = new RandomClass(new ArrayList<String>(){{
	//   add("123");
    //   add("456");
	// }})
	// with plain java compiler, but in maven, it requires explicit type, strange
	private final String customerId;
	private final String noToken;
	public CustomerOrder (String id, String number) {
		customerId = id;
		noToken = number;
	}
	public String getCustomerId() {
		return customerId;
	}
	public int getNoToken() {
		return Integer.parseInt(noToken);
	}
}
// Serverside response data 
class CustomerOrderResponse {
	private final String customerId;
	private final ArrayList<String> tokens;
	CustomerOrderResponse (String id, ArrayList<String> list){
		customerId = id;
		tokens = list;
	}
	public String getCustomerId() {
		return customerId;
	}
	public ArrayList<String> getTokens() {
		return tokens;
	}
}
// Pre database, need to move to database
class CustomerOrderMap {
	public static HashMap<String,ArrayList<String>> database;
	// return value of entry
	public CustomerOrderMap(HashMap<String,ArrayList<String>> map) {
		this.database = map;
	}
}
