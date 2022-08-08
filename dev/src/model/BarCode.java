package model;
import type.Token;

// For merchant to write to the database barcode table by product name. entry e.g. barcode : brocoli
public class BarCode extends Token {
    private String uniqueId;
    public BarCode(IUser user) {
        if (uid != "" && name != "" ) {
            uniqueId = uid;
            imageName = name;
        } else {
            System.exit(-1);
        }
    }
    @Override
    private void fGenerateBarCode(IUser user) { 
        // generates the barcode, image, to store image into database, requires the database supports it.
        // return data of barcode and image name to the client side
        // In the client side, make API request to fetch the barcode image
    }

}