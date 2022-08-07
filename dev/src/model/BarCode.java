package model;
import type.Token;

// For merchant to write to the database barcode table by product name. entry e.g. barcode : brocoli
public class BarCode extends Token {
    private String uniqueId;
    private String imageName;
    public BarCode(String uid, String name) {
        if (uid != "" && name != "" ) {
            uniqueId = uid;
            imageName = name;
        } else {
            System.exit(-1);
        }
        
    }
    @Override
    public String fGetUniqueId(){
        return uniqueId;
    }
    @Override
    public String fGetImageName() {
        return imageName;
    }

}