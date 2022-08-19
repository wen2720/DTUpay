package dtupay;
// ex1.1 hello world, spring boot beginner package
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;
// ex1.2 @QueryParam, GetMapping and MVC controller
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

// ex1.6 more complicated POJO to JSON example, supported by Jackson, http status and PostMapping(POST)
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;

// ex.1.8 Database Postgre sql
import java.text.StringCharacterIterator;
import java.text.CharacterIterator;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.stream.Stream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
// REST API, spring framework, MVC controller
@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class App {
	//private static CustomerOrderMap customerOrderMap = new CustomerOrderMap(new HashMap<String,ArrayList<String>>());
	private static IDatabase customerOrderMap = new CustomerOrderMap();

	@GetMapping("/")
    String home() {
        return "Hello World!";
    }
	@PostMapping("/simplePostFromServer") 
	String postWelcomMessage(@RequestBody String name){
		return "Welcome, " + name + " to the server.";
	}
	@GetMapping("/token")
	CustomerOrderResponse checkOrder(@RequestParam String newCustomerId) {
		CustomerOrderResponse newCustomerOrderResposnse = null;
		try {
			if (customerOrderMap.getDataBase().containsKey(newCustomerId)) {
				newCustomerOrderResposnse = new CustomerOrderResponse(newCustomerId,customerOrderMap.getDataBase().get(newCustomerId));
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
			if (numberOfToken < 6 && numberOfToken >=1) {
				if ((customerOrderMap.getDataBase().containsKey(newCustomerId) && customerOrderMap.getDataBase().get(newCustomerId)==null) || !(customerOrderMap.getDataBase().containsKey(newCustomerId))) {
					ArrayList<String> tokenList = new ArrayList<String>() {{
						for (int i=0; i<numberOfToken; i++){  
							add(UUID.randomUUID().toString());
						}
					}};
					customerOrderMap.getDataBase().put(newCustomerId,tokenList);
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
	
	// REST constoller entry point
    public static void main(String[] args) throws Exception {
		//Postgre SQL database 
		String postgreCredentialPath = System.getenv("Postgre_Credential");									// get windows system enviroment entry value by variable
		CharacterIterator iteratorPostgreCredential = new StringCharacterIterator(postgreCredentialPath);	// string to collection of characters
		String newPostgreCredentialPath = "";																// empty place holder 
		while(iteratorPostgreCredential.current() != CharacterIterator.DONE) {								// resolve windows path backslah with adding escape '\'
			if (iteratorPostgreCredential.current() == '\\'){												
				newPostgreCredentialPath +=  iteratorPostgreCredential.current() + "\\";					
			} else {
				newPostgreCredentialPath += iteratorPostgreCredential.current();
			}
			iteratorPostgreCredential.next();
		}
		// System.out.println("resolved windows path for java : "  + newPostgreCredentialPath);
		String postgreCredentialFile = newPostgreCredentialPath + "\\pgpass.conf";							// postgre credential file
		Path postgreCredentialFilePath = Path.of(postgreCredentialFile);									// construct path regarding the file system 
		String pgpassFileContent = Files.readString(postgreCredentialFilePath);								// readfirst line of the string, quick version should be improved.  Only the content [hostname:port:database:username:password] is allowed, # is the ignore symbol 
		String[] pgpassElements = pgpassFileContent.split(":",5);
		// for (String element : pgpassElements) {
		// 	System.out.println(element);
		// }
		String postgreUrl = "jdbc:postgresql://" + pgpassElements[0] + ":" + pgpassElements[1] + "/" + pgpassElements[2];
		Properties props = new Properties();																// constructing entries
		props.setProperty("user", pgpassElements[3]);
		props.setProperty("password", pgpassElements[4]);
		// data base connection
		Connection newConnection = DriverManager.getConnection(postgreUrl, props);

		Statement newStatement = newConnection.createStatement();
		// Query		
		// ResultSet newResultSet = newStatement.executeQuery("SElECT * FROM ordertoken");
		// while (newResultSet.next()){
		// 	System.out.print("a row was returned.");
		// }
		// newResultSet.close();
		// Create Table
		newStatement.execute("CREATE TABLE IF NOT EXISTS customer_tokens (customer_id VARCHAR(50) PRIMARY KEY, token0 VARCHAR(100) NOT NULL, token1 VARCHAR(100) NOT NULL, token2 VARCHAR(100) NOT NULL, token3 VARCHAR(100) NOT NULL, token4 VARCHAR(100) NOT NULL)");
		newStatement.close();
				
		// App newApp = new App(new CustomerOrderMap());  // Parameter 0 of constructor in dtupay.App required a bean of type 'dtupay.IDatabase' that could not be found.
		SpringApplication.run(App.class, args);
    }

}

// QRCode class, later could be seperated to other java file if manual test passes.
// Test passed, can be used in client device for generating QRcode the data recieved from server.
class QRCode {
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

// Data model too form HTTP POST Request body
// ex1.6 http POST /token?customerId={}&&no={}  // client side class
class CustomerOrder {
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

// Response class for HTTP POST
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

// DataSet classes
interface IDatabase {
	public HashMap<String,ArrayList<String>> getDataBase();
}
abstract class DataBase implements IDatabase {
}

// Database, hashmap implementation
class CustomerOrderMap extends DataBase {
	private static HashMap<String,ArrayList<String>> database = new HashMap<String,ArrayList<String>>();
	// return value of entry
	// public CustomerOrderMap(HashMap<String,ArrayList<String>> map) {
	// 	this.database = map;
	// }
	@Override
	public HashMap<String,ArrayList<String>> getDataBase() {
		return database;
	}
}

// database connection, with credential

// Database, PostgreSQL implementation
