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
//@SpringBootApplication
public class App {
	@GetMapping("/")
    String home() {
        return "Hello World!";
    }
	private static CustomerOrderMap customerOrderMap = new CustomerOrderMap(new HashMap<String,ArrayList<String>>());
	// e.g. /token?customerId={ID}&noToken={TOKEN}
	//@RequestMapping(value = "/token", method = RequestMethod.POST)
	@RequestMapping("/token")
	@ResponseBody
	CustomerOrder makeOrder(@RequestParam String customerId, @RequestParam String noToken) {
		CustomerOrder newCustomerOrder = null;
		try {
			int numberOfToken = Integer.parseInt(noToken);
			// Default value test
			// CustomerOrder newCustomerOrder = new CustomerOrder(customerId, new ArrayList<String>() {{
			// 	add(UUID.randomUUID().toString());
			// }});
			// Test with logic			
			if (numberOfToken < 6 && numberOfToken >=1) {
				if ((customerOrderMap.database.containsKey(customerId) && customerOrderMap.database.get(customerId)==null) || !(customerOrderMap.database.containsKey(customerId))) {
					ArrayList<String> tokenList = new ArrayList<String>() {{
						for (int i=0; i<numberOfToken; i++){  
							add(UUID.randomUUID().toString());
						}
					}};
					customerOrderMap.database.put(customerId,tokenList);
					newCustomerOrder = new CustomerOrder(customerId,tokenList);
				} else {
					throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
				}
			} else {
				throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
			}
		} catch (Exception error) {
			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
		}
		return newCustomerOrder;
	}
	
    public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
    }

}
// In spring framework, the return of the class instance is JSON object. Later could be seperated to other java file if manual test localhost:8080/greeting and localhost:8080/greeting?name=QUERYPARAM passes
// Test status: passed
class Greeting {
	// final fields can assigned in the constructor, statci field can be used to repsent state
	private final long id;
	private final String content;

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}
	// The following function is for unit testing 
	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
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

// ex1.6 http POST /token?customerId={}&&no={} response class
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
	private final ArrayList<String> tokens;
	public CustomerOrder (String id, ArrayList<String> strings) {
		customerId = id;
		tokens = strings;
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
