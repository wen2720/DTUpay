package dtupay;
// ex1.1
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;
// ex1.2
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// ex1.3
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

// ex1.4
import java.util.Random;
import java.nio.charset.*;

// ex1.5
import java.util.UUID;  

// REST API, spring framework, MVC controller
@RestController
@EnableAutoConfiguration
public class App {
	//Routings
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	// inherits @PATH
	// @GetMapping("/greeting"), 1st ed
	// Greeting hello(@RequestParam(value = "name", defaultValue = "World") String name) {
	// 	return new Greeting(counter.incrementAndGet(), String.format(template, name));
	// }

	@GetMapping("/greeting")
	Greeting hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	// ex.1.6
	@GetMapping("/token")
	RandomTokenId getOrder(@RequestParam(value="customerID") String customerId) {
		return new RandomTokenId(UUID.randomUUID().toString());
		// int numberOfToken = Integer.parseInt(tokenNO);
		// if (numberOfToken < 5 && numberOfToken >=1) {

		// } else {

		// }
	}

    public static void main(String[] args) throws Exception {
		// ex.1.1 spring helloword, ex1.2 spring greeting
		// Spring framework, serving rest apis
		// Test status: root passed, greeting 1ed passed, testing 2nd
		SpringApplication.run(App.class, args);
        // ex.1.3
		// Manual test of QRcode class, dataEncode = QRcode Example; path = "WINDOWSFILESYSTEM"
		// Test passed
		// QRCode newQRcode = new QRCode(
		// 	"QRcode Example", 
		// 	".\\photo\\qr\\example2.png",
		// 	"UTF-8",
		// 	new HashMap<EncodeHintType,ErrorCorrectionLevel>(){{
		// 		put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.L);
		// 	}},
		// 	100,
		// 	100
		// );
		// newQRcode.fCreateQR();
		// ex1.4 test of RandomString class 
		// RandomString newRandomString = new RandomString(210);
		// newRandomString.generateRandomString(10);
		// ex1.5 test of UUID Class
		// RandomTokenId newRandomString = new RandomTokenId(UUID.randomUUID());
		// System.out.println(newRandomString.fGetTokenId());
    }

}
// A class as type tests spring frame work; in spring framework, the return of the class instance is JSON object. Later could be seperated to other java file if manual test localhost:8080/greeting and localhost:8080/greeting?name=QUERYPARAM passes
// Test status: passed
class Greeting {

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
// RandomString Class seeded with given numbers of byte and generate numbers of characters as required
// Test status, working
class RandomTokenId {
	private final String tokenId;
//	private UUID uuid128Bit;	//immutable, cannot instantiate
	public RandomTokenId (String id) {
		tokenId = id;
	}

	public String getTokenId () {
		return tokenId;
	}
}