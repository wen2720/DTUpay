package dtupay;

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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
@SpringBootApplication
public class App {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
    }
}

@RestController
class AppController {
	// private static Map<String,ArrayList<String>> customerTokenMap = new HashMap<>();
	//@Autowired
	// private CustomerTokenService customerTokenService;
    //public AppController(CustomerTokenService service) {
    //    customerTokenService = service;
    //}

	//private CustomerTokenService theRepository;
	@GetMapping("/") 
	public String home() {
        return "Hello World!";
    }

	// @PostMapping("/tokens")
	// void postTokens(@RequestBody CustomerOrder order) {
	// 	customerTokenService.saveTokens(order);
	// }

	// @GetMapping("/customer_token")
	// CustomerOrderResponse checkOrder(@RequestParam String newCustomerId) {
	// 	CustomerOrderResponse newCustomerOrderResposnse = null;
	// 	try {
	// 		if (customerTokenMap.containsKey(newCustomerId)) {
	// 			newCustomerOrderResposnse = new CustomerOrderResponse(newCustomerId,customerTokenMap.get(newCustomerId));
	// 		} else {
	// 			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
	// 		}
	// 	} catch (Exception error) {
	// 		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	// 	}
	// 	return newCustomerOrderResposnse;
	// }
	
	// e.g. /token?customerId={ID}&noToken={TOKEN}
	//@RequestMapping(value = "/token", method = RequestMethod.POST)
	// @PostMapping("/customer_token")
	// // @RequestParam is for HTTP GET method
	// CustomerOrderResponse makeOrder(@RequestBody CustomerOrder newCustomerOrder) {
	// 	CustomerOrderResponse newCustomerOrderResposnse = null; 
	// 	try {
	// 		int numberOfToken = newCustomerOrder.getNoToken();
	// 		String newCustomerId = newCustomerOrder.getCustomerId();		
	// 		if (numberOfToken < 6 && numberOfToken >=1) {
	// 			if ((customerTokenMap.containsKey(newCustomerId) && customerTokenMap.get(newCustomerId)==null) || !(customerTokenMap.containsKey(newCustomerId))) {
	// 				ArrayList<String> tokenList = new ArrayList<String>() {{
	// 					for (int i=0; i<numberOfToken; i++){  
	// 						add(UUID.randomUUID().toString());
	// 					}
	// 				}};
	// 				customerTokenMap.put(newCustomerId,tokenList);
	// 				newCustomerOrderResposnse = new CustomerOrderResponse(newCustomerId,tokenList);
	// 			} else {
	// 				// spring HttpStatus https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html
	// 				throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
	// 			}
	// 		} else {
	// 			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
	// 		}
	// 	} catch (Exception error) {
	// 		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	// 	}
	// 	return newCustomerOrderResposnse;
	// }
	
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



class CustomerOrderResponse {
	private Long customerId;
	private String tokens;
	public CustomerOrderResponse(Long id, String tokens){
		this.customerId = id;
		this.tokens = tokens;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public String getTokens() {
		return tokens;
	}
	public void setCustomerId(long id) {
		this.customerId = id;
	}
	public void setTokens(String tokens) {
		this.tokens = tokens;
	}
}

// Database, PostgreSQL implementation
class PostgreUrlAndCredential {
	private static String postgreUrl;
	private static Properties postgreCredentialProperties;

	public PostgreUrlAndCredential(Properties newProperties) throws Exception {
		postgreCredentialProperties = newProperties;
		setPostgreUrlAndProperties("Postgre_Credential");
	}
		
	public String getPostgreUrl() {
		return this.postgreUrl;
	}
	public Properties getPostgrePorperties() {
		return this.postgreCredentialProperties;
	}

	private void setPostgreUrlAndProperties(String credentialPath) throws Exception {
		// The following program is for connecting postgres with default conredential file, has been test in the main function
		String postgreCredentialPath = System.getenv(credentialPath);									// get windows system enviroment entry value by variable, my path
		CharacterIterator characterSequence = new StringCharacterIterator(postgreCredentialPath);	// string to collection of characters
		postgreCredentialPath = "";																// empty place holder 
		while(characterSequence.current() != CharacterIterator.DONE) {								// resolve windows path backslah with adding escape '\'
			if (characterSequence.current() == '\\'){												
				postgreCredentialPath +=  characterSequence.current() + "\\";					
			} else {
				postgreCredentialPath += characterSequence.current();
			}
			characterSequence.next();
		}
		Path postgreCredentialFilePath = Path.of(postgreCredentialPath + "\\pgpass.conf");					// pgpass.conf default postgre credential file name. construct path regarding the file system 
		String pgpassFileContent = Files.readString(postgreCredentialFilePath);								// readfirst line of the string, quick version should be improved.  Only the content [hostname:port:database:username:password] is allowed, # is the ignore symbol 
		String[] pgpassElements = pgpassFileContent.split(":",5);
		// // for (String element : pgpassElements) {
		// // 	System.out.println(element);
		// // }
		postgreUrl = "jdbc:postgresql://" + pgpassElements[0] + ":" + pgpassElements[1] + "/" + pgpassElements[2];															// constructing entries
		postgreCredentialProperties.setProperty("user", pgpassElements[3]);
		postgreCredentialProperties.setProperty("password", pgpassElements[4]);
	}
		
}
// database connection, with credential
class PostgreConnection {
	private static Connection newConnection;
	private PostgreUrlAndCredential defaultPostgreUrlAndProperties;
	//Postgre SQL database 

	public PostgreConnection(PostgreUrlAndCredential newProperties){
		defaultPostgreUrlAndProperties = newProperties;
	}

	public Connection getPostgreConnection() throws Exception {
		newConnection = DriverManager.getConnection(this.defaultPostgreUrlAndProperties.getPostgreUrl(),this.defaultPostgreUrlAndProperties.getPostgrePorperties());
		return newConnection;
	}
}

