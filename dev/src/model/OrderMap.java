package model;
import java.util.*;
import contract.IToken;
import type.DataSets;
import model.BarCode;
public class OrderMap extends DataSets {
    // Interface Collection typed instance
    private static Map<String,ArrayList<IToken>> orderMapInstance = new HashMap<String,ArrayList<IToken>>(){{
        put("Wenhao", new ArrayList<IToken>(){{
            add(new BarCode("BarCode1","Image1"));
        }});
    }}; 
    @Override
    public Map<String,ArrayList<IToken>> fGetOrderData() {
        return this.orderMapInstance;
    }
    
    // e.g. new HashMap<String,AarrayList<IToken>>()
    // Hashmap typed instance
    // private static HashMap<String,ArrayList<IToken>> orderMapInstance = new HashMap<String,ArrayList<IToken>>(){{
    //     put("Wenhao", new ArrayList<IToken>(){{
    //         add(new BarCode("BarCode1","Image1"));
    //     }});
    // }};
    // @Override
    // public HashMap<String,ArrayList<IToken>> fGetOrderData() {
    //     return this.orderMapInstance;
    // }
    
    
    
}
