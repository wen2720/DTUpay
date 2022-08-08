package contract;
import java.util.*;
import contract.IToken;
public interface IDataSets {
    public Map<String,ArrayList<IToken>> fGetOrderData();
    //public HashMap<String,ArrayList<IToken>> fGetOrderData();
}